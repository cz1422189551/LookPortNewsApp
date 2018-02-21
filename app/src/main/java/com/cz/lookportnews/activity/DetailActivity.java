package com.cz.lookportnews.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.cz.lookportnews.R;
import com.cz.lookportnews.adapter.DetailAadapter;
import com.cz.lookportnews.adapter.MultipleLayoutAadapter;
import com.cz.lookportnews.adapter.MyAdapter;
import com.cz.lookportnews.entity.News;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 14221 on 2018/2/21.
 */

public class DetailActivity extends BasActivity {

//    @BindView(R.id.wv_detail)
    WebView webView ;
    @BindView(R.id.lv_detail)
    ListView listView ;


//    @BindView(R.id.rv_detail)
//    RecyclerView recyclerView;

    @Override
    public int getLayout() {
        return R.layout.activity_detail;
    }

    @Override
    public AppCompatActivity getActivity() {
        return this;
    }

    @Override
    public void initViews() {

        String url = "http://www.sohu.com/a/222358897_99984250";
        String detailHtml = "file:///android_asset/detail.html";
        View view  = LayoutInflater.from(this).inflate(R.layout.detail_head,null);
        webView = view.findViewById(R.id.wv_detail);

        WebSettings webSettings = webView.getSettings();//获取webview设置属性
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//把html中的内容放大webview等宽的一列中
        webSettings.setJavaScriptEnabled(true);//支持js
        webSettings.setBuiltInZoomControls(true); // 显示放大缩小
        webSettings.setSupportZoom(true); // 可以缩放



        webView.loadUrl(detailHtml);
        webView.setWebViewClient(new MyWebViewClient());

//        View v  = LayoutInflater.from(this).inflate(R.layout.detail_head,null);
//        ImageView iv = v.findViewById(R.id.iv_1);
//        iv.setImageResource(R.drawable.ic_launcher_background);


        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            stringList.add("数据"+1);
        }
        MyAdapter myAdapter = new MyAdapter(stringList,this);
        listView.addHeaderView(webView);
        listView.setAdapter(myAdapter);


    }

    @Override
    public void loadData() {

    }


    /**
     * 对图片进行重置大小，宽度就是手机屏幕宽度，高度根据宽度比便自动缩放
     **/
    private void imgReset() {
        webView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName('img'); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "var img = objs[i];   " +
                "    img.style.maxWidth = '100%'; img.style.height = 'auto';  " +
                "}" +
                "})()");
    }


    private class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            imgReset();//重置webview中img标签的图片大小
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
