package com.sxit.dreamiya.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.view.Gravity;
import android.widget.TextView;

public class passwordInterface extends Activity {
	public ProgressDialog processDialog;
	protected boolean loadingFlag = true;

	/**
	 * ��ʾ��һ���ַ�ĶԻ��� ����message����Ҫ��ʾ���ַ�
	 * */
	public void showResult(final String message) {

		passwordInterface.this.runOnUiThread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				// show
				Builder builder = new Builder(passwordInterface.this);
				TextView tv = new TextView(passwordInterface.this);
				tv.setTextSize(20);
				tv.setGravity(Gravity.CENTER_HORIZONTAL);
				tv.setText(message);
				builder.setView(tv);
				builder.setPositiveButton("ȷ��", null);
				builder.create().show();
			}
		});
	}

	/**
	 * ��ʾ��һ���ַ��Ҵ����ĶԻ���
	 * 
	 * @param title
	 *            ����
	 * @param message
	 *            ��Ҫ��ʾ���ַ�
	 * */
	public void showResult(final String title, final String message) {

		passwordInterface.this.runOnUiThread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				// show
				Builder builder = new Builder(passwordInterface.this);
				TextView tv = new TextView(passwordInterface.this);
				tv.setTextSize(20);
				tv.setGravity(Gravity.CENTER_HORIZONTAL);
				tv.setText(message);
				builder.setTitle(title);
				builder.setView(tv);
				builder.setPositiveButton("ȷ��", null);
				builder.create().show();
			}
		});
	}

	/**
	 * ��ȴ�ͼ��ĵȴ�� ����message����Ҫ��ʾ���û����ַ�
	 * */
	public void showWait(final String message) {
		passwordInterface.this.runOnUiThread(new Runnable() {

			public void run() {
				processDialog = new ProgressDialog(passwordInterface.this);
				processDialog.setMessage(message);
				processDialog.setIndeterminate(true);
				processDialog.setCancelable(true);
				processDialog.setCanceledOnTouchOutside(false);
				processDialog.show();
			}
		});

	}

	/**
	 * �رյȴ��
	 * */
	public void waitClose() {
		passwordInterface.this.runOnUiThread(new Runnable() {

			public void run() {
				if (processDialog != null && processDialog.isShowing()) {
					processDialog.dismiss();
				}
			}
		});
		loadingFlag = false;
	}

}
