package com.sxit.dreamiya.activity.kinder;

import com.sxit.dreamiya.R;
import com.sxit.dreamiya.base.component.BaseActivity;
import com.sxit.dreamiya.common.Instance;
import com.sxit.dreamiya.entity.news.FinNewsList;
import com.sxit.dreamiya.http.RdaResultPack;
import com.sxit.dreamiya.utils.SOAP_UTILS;
import com.sxit.dreamiya.webservice.SoapRes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 新闻详情
 * 
 * @author huanyu 类名称：NewsInfo_Activity 创建时间:2014-10-29 下午3:18:47
 */
@SuppressLint("NewApi")
public class NewsInfo_Activity extends BaseActivity {
    private WebView webView;
    private TextView title;
    private TextView time;
    private ImageView image;
    private FinNewsList news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_newsinfo);
        super.onCreate(savedInstanceState);
        intent = getIntent();
        news = (FinNewsList) intent.getSerializableExtra("finnewslist");
        initView();
        setUI();
        // String[] property_va = new String[] { newsList.getId() };
        // soapService.getNewsContent(property_va);
    }

    private void initView() {
        webView = (WebView) findViewById(R.id.webView);
        title = (TextView) findViewById(R.id.title);
        time = (TextView) findViewById(R.id.time);
        image = (ImageView) findViewById(R.id.image);
    }

    public void setUI() {
        title.setText(news.getTitle());
        time.setText(news.getCrtime());
        Instance.imageLoader.displayImage(SOAP_UTILS.PIC_FILE + news.getPicture(), image, Instance.img_options);
        
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(PluginState.ON);
        webView.getSettings().setPluginsEnabled(true);// 可以使用插件
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setDefaultTextEncodingName("UTF-8");
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        webView.setVisibility(View.VISIBLE);

        // String url = SOAP_UTILS.IP + "/" + "showNews.aspx?id=" +
        // newsList.getId();
        // webView.loadUrl(url);
        webView.loadDataWithBaseURL(null, news.getContent(), "text/html",
                "utf-8", null);
    }

    /**
     * 返回
     * 
     * @param view
     */
    public void back(View view) {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        super.onPause();
        // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        // webView.onPause(); // 暂停网页中正在播放的视频
        // }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onEvent(SoapRes obj) {
        super.onEvent(obj);
        // if (obj.getCode().equals(SOAP_UTILS.METHOD.GETNEWSCONTENT)) {
        // newsInfo = (FinNewsInfo) obj.getObj();
        // if(newsInfo != null){
        // setUI();
        // }
        // }
    }

    @Override
    public void onEventMainThread(String method) {
        // if (method.equals(SOAP_UTILS.METHOD.GETNEWSCONTENT)) {
        // String[] property_va = new String[] { newsList.getId() };
        // soapService.getNewsContent(property_va);
        // }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onEventMainThread(RdaResultPack http) {
        // TODO Auto-generated method stub

    }

}
