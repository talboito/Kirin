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

import java.text.MessageFormat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.TextView;

import com.futureplatforms.kirin.C;
import com.futureplatforms.kirin.Kirin;
import com.futureplatforms.kirin.demo.hellokirin.TheApplication;
import com.futureplatforms.kirin.demo.hellokirin.R;
import com.futureplatforms.kirin.ui.JSOnClickListener;

public class DumbButtonActivity extends Activity {

    private Button mButton;
    private TextView mLabel;

    Kirin mKirin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mKirin = ((TheApplication) getApplication()).getKirin();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dumb_button_activity);

        mButton = (Button) findViewById(R.id.dumb_button);
        mButton.setOnClickListener(new JSOnClickListener(mKirin, "native2jsScreenProxy.onDumbButtonClick()"));

        findViewById(R.id.fp_logo_imageview).setOnClickListener(
                new JSOnClickListener(mKirin, "native2jsScreenProxy.onNextScreenButtonClick()"));

        mLabel = (TextView) findViewById(R.id.dumb_label);
        setTitle("How big?");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mKirin.setCurrentScreen("DumbButtonScreen", this);
    }
    
    public void updateLabelSize_andText_(int size, String text) {
        Log.i(C.TAG, MessageFormat.format("updateLabel_ invoked with {0} and {1}", size, text));
        mLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        mLabel.setText(text);
        mLabel.invalidate();
    }

    public void changeScreen_(String arg) {
        Intent intent = new Intent(this, DumbListActivity.class);
        intent.putExtra("string", arg);
        startActivity(intent);
    }
}