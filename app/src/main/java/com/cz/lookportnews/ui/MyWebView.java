package com.cz.lookportnews.ui;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cz.lookportnews.activity.DetailActivity;
import com.cz.lookportnews.util.Util;

/**
 * Created by 14221 on 2018/3/4.
 */

    public class MyWebView extends WebView {

    WebSettings webSettings;

    NightWebViewClient nightClient;

    WhiteWebViewClient whiteClient;

    public MyWebView(Context context) {
        super(context);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        webSettings = this.getSettings();
        //把html中的内容放大webview等宽的一列中
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //支持js
        webSettings.setJavaScriptEnabled(true);
        // 显示放大缩小
        webSettings.setBuiltInZoomControls(true);
        // 可以缩放
        webSettings.setSupportZoom(true);
        this.setBackgroundColor(Color.WHITE);
        nightClient = new NightWebViewClient();
        whiteClient = new WhiteWebViewClient();

    }


    public void setChangeFontNight(boolean isNight) {
        if (isNight) {
            this.setWebViewClient(nightClient);
        } else {
            this.setWebViewClient(whiteClient);
        }

    }

    /**
     * 对图片进行重置大小，宽度就是手机屏幕宽度，高度根据宽度比便自动缩放
     **/
    private void imgReset() {

        this.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName('body'); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "var img = objs[i];   " +
                "    img.style.maxWidth = '100%'; img.style.height = 'auto';  " +
                "}" +
                "})()");
    }

    private void changeFontNight() {
        this.loadUrl("");
    }

    private void changeFontWhite() {
        this.loadUrl("");
    }

    private class WhiteWebViewClient extends WebViewClient {


        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            imgReset();//重置webview中img标签的图片大小
            changeFontWhite();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }


    }


    private class NightWebViewClient extends WebViewClient {


        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            imgReset();//重置webview中img标签的图片大小
            changeFontNight();
            // html加载完成之后，添加监听图片的点击js函数
            //addImageClickListner();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }


    }

}
