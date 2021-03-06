/*
   Copyright 2011 Future Platforms

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/


package com.futureplatforms.kirin.demo.hellokirin.activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.futureplatforms.kirin.C;
import com.futureplatforms.kirin.Kirin;
import com.futureplatforms.kirin.demo.hellokirin.TheApplication;
import com.futureplatforms.kirin.demo.hellokirin.R;
import com.futureplatforms.kirin.ui.JSListAdapter;
import com.futureplatforms.kirin.ui.KirinRowRenderer;

public class DumbListActivity extends ListActivity {

    protected Kirin mKirin;

    protected String mScreenName;

    protected KirinRowRenderer<JSONObject> mItemRenderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mKirin = ((TheApplication) getApplication()).getKirin();

        // we won't worry about arguments to this activity, though we know
        // how to do it.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dumb_list_activity);
        mScreenName = "DumbListScreen";

        mItemRenderer = new ObjectItemRenderer(mKirin, "native2jsScreenProxy.onListItemClick({0}, ''{1}'')", "key");
        setTitle("Alphabet");
    }

    public void populateList_(JSONArray jsonArray) {
        setListAdapter(new JSListAdapter(this, jsonArray, R.layout.dumb_row, mItemRenderer));
    }

    public void showToast_(String key) {
        Toast.makeText(this, "You clicked on: " + key, Toast.LENGTH_SHORT).show();
    }

    /************************************************
     * Stuff that could easily be genericized.
     ***********************************************/

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mItemRenderer.onItemClicked(v, position, (JSONObject) getListAdapter().getItem(position));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mKirin.setCurrentScreen(mScreenName, this);
        mKirin.fireEventIntoJS("native2jsScreenProxy.onResume()");
    }

    public static class ObjectItemRenderer implements KirinRowRenderer<JSONObject> {

        private final String mEventName;
        private final Kirin mJs;

        private final String mPropertyName;

        public class ViewHolder {
            TextView mText;
        }

        public ObjectItemRenderer(Kirin js, String onClickEvent, String propertyName) {
            mEventName = onClickEvent;
            mJs = js;
            mPropertyName = propertyName;
        }

        @Override
        public void configureView(View view) {
            ViewHolder vh = new ViewHolder();
            vh.mText = (TextView) view.findViewById(R.id.text);
            view.setTag(vh);
        }

        @Override
        public void onItemClicked(View view, int index, JSONObject item) {
            try {
                mJs.fireEventIntoJS(mEventName, index, item.get(mPropertyName));
            } catch (JSONException e) {
                Log.e(C.TAG, "Can't get " + mPropertyName + " from item #" + index);
            }
        }

        @Override
        public void renderItem(View view, JSONObject item) {
            ((ViewHolder) view.getTag()).mText.setText(item.optString("key", "unavailable"));
        }

    }

}
