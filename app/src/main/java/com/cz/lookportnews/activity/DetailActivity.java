package com.cz.lookportnews.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.coorchice.library.SuperTextView;
import com.cz.lookportnews.R;
import com.cz.lookportnews.adapter.CommentAdapter;
import com.cz.lookportnews.adapter.DetailAadapter;
import com.cz.lookportnews.adapter.MultipleLayoutAadapter;
import com.cz.lookportnews.adapter.MyAdapter;
import com.cz.lookportnews.db.DBUtil;
import com.cz.lookportnews.entity.Comment;
import com.cz.lookportnews.entity.News;
import com.cz.lookportnews.entity.User;
import com.cz.lookportnews.fragment.CommentDialogFragment;
import com.cz.lookportnews.ui.CommentDialog;
import com.cz.lookportnews.ui.CommentOnSendListener;
import com.cz.lookportnews.ui.MyPopupWindow;
import com.cz.lookportnews.ui.MyWebView;
import com.cz.lookportnews.util.JsonUtil;
import com.cz.lookportnews.util.UIUtils;
import com.cz.lookportnews.util.Util;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;

import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import skin.support.SkinCompatManager;

/**
 * Created by 14221 on 2018/2/21.
 */

public class DetailActivity extends BasActivity {


    private static final String TAG = "DetailActivity";

    //    @BindView(R.id.wv_detail)
    WebView webView;
    @BindView(R.id.lv_detail)
    ListView listView;

    DBUtil dbUtil;

    CommentDialog commentDialog;

    @BindView(R.id.stv_search)
    SuperTextView tvComment;

    @BindView(R.id.ib_returns)
    ImageView back;

    @BindView(R.id.iv_bottom_comment)
    ImageView bottomComment;
    @BindView(R.id.iv_more)
    ImageView moreView;

    @BindView(R.id. iv_collection)
    ImageView collection;

    News news;


    MyPopupWindow popupWindow;

    @BindView(R.id.ll_detail_bottom)
    LinearLayout bottomLinearLayout;

    boolean isTop = true;

    @Override
    public int getLayout() {
        return R.layout.activity_detail;
    }

    @Override
    public AppCompatActivity getActivity() {
        return this;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void initViews() {
        news = (News) getIntent().getSerializableExtra("news");
        dbUtil= DBUtil.getInstance(this);
        //添加至阅读记录
        dbUtil.saveReaded(news);

        tvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commentDialog.show();
            }
        });
        collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //收藏
                dbUtil.saveNews(news);
                Toast.makeText(DetailActivity.this,"已成功收藏本条新闻",Toast.LENGTH_SHORT).show();
            }
        });
        //返回按钮
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, HomeActivity.class);

                startActivity(intent);
            }
        });
        //底部按钮 点击跳转
        bottomComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.post(new Runnable() {
                    @Override
                    public void run() {
                        if (isTop) {
                            listView.smoothScrollToPosition(1);
                            isTop = false;
                        } else {
                            listView.smoothScrollToPosition(0);
                            isTop = true;
                        }

                    }
                });

            }
        });


        String detailHtml = "file:///android_asset/detail.html";


        View view = LayoutInflater.from(this).inflate(R.layout.detail_head, null);
        webView = (WebView) view.findViewById(R.id.wv_detail);
        webView.loadUrl(detailHtml);
        //获取webview设置属性
        WebSettings webSettings = webView.getSettings();
        //把html中的内容放大webview等宽的一列中
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //支持js
        webSettings.setJavaScriptEnabled(true);
        // 显示放大缩小
        webSettings.setBuiltInZoomControls(true);
        // 可以缩放
        webSettings.setSupportZoom(true);

        webView.setBackgroundColor(Color.WHITE);

        webView.loadUrl(detailHtml);
        webView.setWebViewClient(new MyWebViewClient());


        View view2 = getContentView(this);
        popupWindow = new MyPopupWindow(this, getScreenWidth(this), getScreenHeight(this) * 2 / 10, bottomLinearLayout);

        List<Comment> commentList = initCommentData();
        commentDialog = new CommentDialog(this);

        CommentAdapter commentAdapter = new CommentAdapter(commentList, this, commentDialog, popupWindow, view2, bottomLinearLayout);


        User user = new User();
        user.setUsername("新用户");
        CommentOnSendListener onSendListener = new CommentOnSendListener(user, this, commentList, commentAdapter);
        commentDialog.setOnSendListener(onSendListener);
        listView.addHeaderView(webView);
        if (commentList != null && commentList.size() > 0) {

        } else {
            View view1 = LayoutInflater.from(this).inflate(R.layout.detail_item_nocomment, null);
            listView.addHeaderView(view1);
        }

        listView.setAdapter(commentAdapter);

    }

    @Override
    public void loadData() {

    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onResume() {

        if (webView != null) {
            boolean isNight = Util.isNightTheme();
            if (isNight) {
                webView.setBackgroundColor(R.color.colorPrimary);
            } else {
                webView.setBackgroundColor(Color.WHITE);
            }
        }
        super.onResume();
    }

    /**
     * 对图片进行重置大小，宽度就是手机屏幕宽度，高度根据宽度比便自动缩放
     **/
    private void imgReset() {

        //webView.loadUrl("javascript:(function(){document.getElementsByTagName('body').style.color='#ffffff'})()");
//        webView.loadUrl("javascript:(function(){  var objs = document.getElementsByTagName('body');" +
//                "    objs.style.color='#ffffff'})()");

//        webView.loadUrl("javascript:(function(){" +
//                "var objs = document.getElementsByTagName('body'); " +
//                "for(var i=0;i<objs.length;i++)  " +
//                "{"
//                + "var img = objs[i];   " +
//                "    img.style.maxWidth = '100%'; img.style.height = 'auto';  " +
//                "}" +
//                "})()");

    }

    private List<Comment> initCommentData() {
        Comment comment = new Comment();
        User user = new User();
        user.setUsername("狮子吃海鱼");
        comment.setUser(user);
        comment.setCommentContent("我和我最后的倔强，握紧双手绝不能放。" +
                "下一站是不是天堂，就算失望不能绝望。我和我骄傲的绝望，" +
                "我在风中大声的唱，这一次为自己疯狂，就这一次，我和我的倔强。");
        comment.setTime("4天前");

        Comment subComment = new Comment();
        User subUser = new User();
        subUser.setUsername("齐天大圣");
        subComment.setUser(subUser);
        subComment.setCommentContent("超级喜欢五月天。");
        subComment.setTime("4天前");
        subComment.setUser(subUser);
        List<Comment> subComments = new ArrayList<>();
        subComments.add(subComment);

        comment.setSubComment(subComments);


        Comment comment2 = new Comment();
        User user2 = new User();
        user2.setUsername("HelloWorld83");
        comment2.setUser(user2);
        comment2.setCommentContent("天天想你，天天问自己。");
        comment2.setTime("4天前");


        Comment subComment2 = new Comment();
        User subUser2 = new User();
        subUser2.setUsername("最佳损友");
        subComment2.setUser(subUser2);
        subComment2.setCommentContent("而我知道那真爱不一定能白头到老，而我知道有一天你可能就这么走掉。");
        subComment2.setTime("4天前");
        List<Comment> subComments2 = new ArrayList<>();
        subComments2.add(subComment2);
        subComments2.add(subComment);
        comment2.setSubComment(subComments2);


        Comment comment3 = new Comment();
        User user3 = new User();
        user3.setUsername("今天吃火锅");
        comment3.setUser(user3);
        comment3.setCommentContent("若选择了远方，便只顾风雨兼程。");
        comment3.setTime("3天前");

        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        comments.add(comment2);
        comments.add(comment3);
        return comments;
    }


    private class MyWebViewClient extends WebViewClient {


        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            imgReset();//重置webview中img标签的图片大小
            changePage();
            // html加载完成之后，添加监听图片的点击js函数
            //addImageClickListner();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }


    }

    public View getContentView(Activity activity) {
        ViewGroup view = (ViewGroup) activity.getWindow().getDecorView();
        FrameLayout content = view.findViewById(android.R.id.content);
        return content.getChildAt(0);

    }

    //在java中调用js代码
    public void changePage() {

//        Log.d(TAG, "changePage: "+Util.processStr(news.getContent()));

        if (TextUtils.isEmpty(news.getEditor())) {
            news.setEditor("佚名");
        }
        String method = "javascript:changePage('" + news.getTitle() + "','" + news.getOrigin() + "','" + news.getTime() + "','" + Util.processStr(news.getContent()) + "','" + news.getPageSource() + "','"+news.getEditor()+"')";


        webView.loadUrl(method);
        webView.loadUrl("javascript:changeFont()");
        webView.loadUrl("javascript:imgreset()");


    }


}
