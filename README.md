Kirin
=====
This library is actually a set of frameworks designed to help writing cross platform mobile apps.

It hasn't been out very long, and so has very little documentation.

However, you may want to download it and try building it with XCode or Eclipse+ADT.

Helicopter view
---------------
We've been looking around at the alternatives out there: in particular, [HTML5](http://caniuse.com) + [PhoneGap](http://www.phonegap.com/) and [Titanium](https://www.appcelerator.com/products/titanium-mobile-application-development/).

The biggest problem for each of these cross-platform solutions is the UI. For a framework, the problem of delivering great UIs that are appropriate for the platform boils down to how to provide a set of tools to the developer that is not only as good as the platform vendor provides i.e. Google and Apple – but also able to translate between the two platforms.

We think this is intractable.

Kirin steps away from this problem, giving the developer the freedom to construct as elaborate a user-interface as the native SDK and tooling can provide.

The non-UI parts of your app – the business logic – is written in Javascript, using APIs provided by Kirin to access the rest of the device. The Javascript runs in an invisible WebView (or UIWebView) using whatever Javascript engine is available to that webview.

For the UI, you build in whatever tools the platform provides, to produce responsive, platform-appropriate and respectful UIs that are driven by a common business logic.

i.e. *Javascript logic, native UI*

Current Status
--------------
Kirin provides the tools to: 

 * modularize your Javascript with Common JS Modules.
 * test, minify and package the Javascript, using the Google Closure Compiler.
 * facilitate bi-directional communication between native (Android & Objective-C) and Javascript.
 * access the device APIs. 

Kirin is currently considered stable, but incomplete. 


Sightings
---------
The only outing it has in the market place was for the Glastonbury 2011 app – a single app for sure, but it's winning awards, and was featured in [Apple's AppStore](http://itunes.apple.com/us/app/glastonbury-2011/id377852148?mt=8&ls=1), the [Android Market](https://market.android.com/details?id=com.orange.glastonbury), and the Ovi market place.

Once we had written the common application logic, each app took only 50% of the time it took to write the comparative fully native app.

LICENSE
=======

 * Copyright 2011 Future Platforms.
 * Released under the Apache v2 License.

