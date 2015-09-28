package com.sxit.dreamiya.customview;

import com.sxit.dreamiya.R;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * loading
 * 
 * @author huanyu 类名称：Loading 创建时间:2014-11-6 上午12:35:19
 */
public class LoadingPage extends LinearLayout implements OnClickListener {
	public TextView tv_loading;
	private Context context;
	private ILoadingDo loadingDo;//
	public LinearLayout ll_bg;
	public int state;// 0成功 1失败
	public String methodName;
	public ImageView img_loading;
	public AnimationDrawable animDrawable1, animDrawable2;

	public LoadingPage(Context context, ILoadingDo loadingDo) {
		super(context);
		this.context = context;
		this.loadingDo = loadingDo;
		LayoutInflater mInflater = LayoutInflater.from(context);
		View myView = mInflater.inflate(R.layout.activity_loading, null);
		myView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		tv_loading = (TextView) myView.findViewById(R.id.tv_loading);
		img_loading = (ImageView) myView.findViewById(R.id.img_loading);
		img_loading.setBackgroundResource(R.drawable.loading_anim);
		animDrawable1 = (AnimationDrawable) img_loading.getBackground();
		animDrawable1.start();
		tv_loading.setOnClickListener(this);
		ll_bg = (LinearLayout) myView.findViewById(R.id.ll_bg);
		ll_bg.setOnClickListener(null);
		addView(myView);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_loading:
			if (state == 1) {
				img_loading.setBackgroundResource(R.drawable.loading_anim);
				animDrawable1.start();
				tv_loading.setText("努力加载中...");
				loadingDo.soapFail(methodName);
			}
			break;
		default:
			break;
		}
	}

	public ILoadingDo getLoadingDo() {
		return loadingDo;
	}

	public void setLoadingDo(ILoadingDo loadingDo) {
		this.loadingDo = loadingDo;
	}

	public void setState(int state, String methodName) {
		this.methodName = methodName;
		this.state = state;
		// chang textView
		tv_loading.setText("网络异常,请求失败！");
		animDrawable1.stop();
		img_loading.setBackgroundResource(R.drawable.error_anim);
		// animDrawable1 = (AnimationDrawable) img_loading.getBackground();
	}

	public interface ILoadingDo {
		/**
		 * 请求失败
		 */
		void soapFail(String methodName);
	}
}
