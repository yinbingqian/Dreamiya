package com.sxit.dreamiya.dialog;

import com.sxit.dreamiya.R;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 预加载进度dialog
 * 
 * @author huanyu 类名称：SXProgressDialog 创建时间:2014-11-25 上午9:05:33
 */
public class SXProgressDialog extends Dialog {
	private Context context;
	private static SXProgressDialog customProgressDialog;
	private static Resources resource;

	public SXProgressDialog(Context context) {
		super(context);
		this.context = context;
	}

	public SXProgressDialog(Context context, int theme) {
		super(context, theme);
	}

	public static SXProgressDialog createDialog(Context context) {
		if(customProgressDialog == null){
			customProgressDialog = new SXProgressDialog(context, R.style.CustomProgressDialog);
			resource = (Resources) context.getResources();
			customProgressDialog.setContentView(R.layout.custom_progressdialog);
			customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
		}
		return customProgressDialog;
	}

	public void onWindowFocusChanged(boolean hasFocus) {
		if (customProgressDialog == null) {
			return;
		}
		ImageView imageView = (ImageView) customProgressDialog.findViewById(R.id.loadingImageView);
		AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
		animationDrawable.start();
	}

	/**
	 * dialog 内容
	 * @param strMessage
	 * @return
	 */
	public SXProgressDialog setMessage(String strMessage) {
		TextView tvMsg = (TextView) customProgressDialog.findViewById(R.id.id_tv_loadingmsg);
		if (tvMsg != null) {
			tvMsg.setText(strMessage);
		}
		return customProgressDialog;
	}
	/**
	 * 关闭按钮
	 * @param b 
	 * @return
	 */
	public SXProgressDialog setCloseable(boolean b) {
		LinearLayout progress_close_layout = (LinearLayout) customProgressDialog.findViewById(R.id.progress_close_layout);
		ImageView id_progress_xian = (ImageView) customProgressDialog.findViewById(R.id.id_progress_xian);
		ImageButton progress_close_btn = (ImageButton) customProgressDialog.findViewById(R.id.progress_close_btn);
		progress_close_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				customProgressDialog.dismiss();

			}
		});
		if (b == true) {
			progress_close_layout.setVisibility(View.VISIBLE);
			id_progress_xian.setVisibility(View.VISIBLE);
		} else {
			progress_close_layout.setVisibility(View.GONE);
			id_progress_xian.setVisibility(View.INVISIBLE);
		}
		return customProgressDialog;
	}
	/**
	 * dialog样式
	 * @param style
	 * @return
	 */
	public SXProgressDialog setDialogStyle() {
		ImageView loadingImageView = (ImageView) customProgressDialog.findViewById(R.id.loadingImageView);
		TextView tvMsg = (TextView) customProgressDialog.findViewById(R.id.id_tv_loadingmsg);
		ColorStateList grey = (ColorStateList) resource.getColorStateList(R.color.progress_msg_text_dark_grey);
		loadingImageView.setBackgroundResource(R.anim.progress_grey_round);
		tvMsg.setTextColor(grey);
		return customProgressDialog;

	}
}
