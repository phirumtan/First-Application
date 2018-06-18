package com.example.tanphirum.firstapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebviewActivity extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /// Let's display the progress in the activity title bar, like the
        // browser app does.
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_webview);

        webview = findViewById(R.id.webview);

        /*WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(true);

        mWebView.loadUrl("http://www.video4khmer33.net/watch/khmer-chinese-drama/yutasel-sorya-chantrea-part-47-video-440977.html#playertop");*/

        webview.getSettings().setJavaScriptEnabled(true);
        JavaScriptInterface JSInterface = new JavaScriptInterface(this);
        webview.addJavascriptInterface(JSInterface, "JSInterface");

        final Activity activity = this;
        webview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                // Activities and WebViews measure progress with different scales.
                // The progress meter will automatically disappear when we reach 100%
                activity.setProgress(progress * 1000);
            }
        });
        webview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }
        });

        //webview.loadUrl("https://developer.android.com/");
        webview.loadData(htmlTest, "text/html", null);


    }

    public class JavaScriptInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        JavaScriptInterface(Context c) {
            mContext = c;
        }

        @android.webkit.JavascriptInterface
        public void changeActivity()
        {
            Toast.makeText(mContext, "on click", Toast.LENGTH_SHORT).show();
        }
    }

    private String htmlTest = "<html>\n" +
            "<head>\n" +
            "<script type=\"text/javascript\">\n" +
            "function displaymessage()\n" +
            "{\n" +
            "JSInterface.changeActivity();\n" +
            "}\n" +
            "</script>\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "<form>\n" +
            "<input type=\"button\" value=\"Click me!\" onclick=\"displaymessage()\" />\n" +
            "</form>\n" +
            "</body>\n" +
            "</html>";
}
