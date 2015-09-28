package com.sxit.dreamiya.utils.settingimg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sxit.dreamiya.R;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SelectPicActivity extends Activity implements OnClickListener {

	// 使用照相机拍照获取图�?
	public static final int SELECT_PIC_BY_TACK_PHOTO = 1;
	// 使用相册中的图片
	public static final int SELECT_PIC_BY_PICK_PHOTO = 2;
	// 从Intent获取图片路径的KEY
	public static final String KEY_PHOTO_PATH = "photo_path";
	private static final String TAG = "SelectPicActivity";
	private static final int PHOTO_RESOULT = 0x03;
	private LinearLayout dialogLayout;
	private Button takePhotoBtn, pickPhotoBtn, cancelBtn;

	/** 获取到的图片路径 */
	private String picPath;
	private Intent lastIntent;
	private Uri photoUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_pic);

		dialogLayout = (LinearLayout) findViewById(R.id.dialog_layout);
		dialogLayout.setOnClickListener(this);
		takePhotoBtn = (Button) findViewById(R.id.btn_take_photo);
		takePhotoBtn.setOnClickListener(this);
		pickPhotoBtn = (Button) findViewById(R.id.btn_pick_photo);
		pickPhotoBtn.setOnClickListener(this);
		cancelBtn = (Button) findViewById(R.id.btn_cancel);
		cancelBtn.setOnClickListener(this);
		lastIntent = getIntent();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dialog_layout:
			finish();
			break;
		case R.id.btn_take_photo:
			takePhoto();
			break;
		case R.id.btn_pick_photo:
			pickPhoto();
			break;
		default:
			finish();
			break;
		}
	}

	/**
	 * 拍照获取图片
	 */
	private void takePhoto() {
		// 执行拍照前，应该先判断SD卡是否存�?
		String SDState = Environment.getExternalStorageState();
		if (SDState.equals(Environment.MEDIA_MOUNTED)) {

			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// "android.media.action.IMAGE_CAPTURE"
			/***
			 * �?��说明�?��，以下操作使用照相机拍照，拍照后的图片会存放在相册中�?这里使用的这种方式有�?��好处就是获取的图片是拍照后的原图
			 * 如果不实用ContentValues存放照片路径的话，拍照后获取的图片为缩略图不清晰
			 */
			ContentValues values = new ContentValues();
			photoUri = this.getContentResolver().insert(
					MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
			intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, photoUri);

			/*
			 * //收缩裁剪选择的图�?intent.setDataAndType(photoUri, "image/*");
			 * intent.putExtra("crop", "true"); // aspectX aspectY 是宽高的比例
			 * intent.putExtra("aspectX", 2); intent.putExtra("aspectY", 1); //
			 * outputX outputY 是裁剪图片宽�?intent.putExtra("outputX", 140);
			 * intent.putExtra("outputY", 70); intent.putExtra("return-data",
			 * true);
			 */

			/** ----------------- */
			startActivityForResult(intent, SELECT_PIC_BY_TACK_PHOTO);
		} else {
			Toast.makeText(this, "内存卡不存在", Toast.LENGTH_LONG).show();
		}
	}

	/***
	 * 从相册中取图�?
	 */
	private void pickPhoto() {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(intent, SELECT_PIC_BY_PICK_PHOTO);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return super.onTouchEvent(event);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			doPhoto(requestCode, data);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * 选择图片后，获取图片的路�?
	 * 
	 * @param requestCode
	 * @param data
	 */
	private void doPhoto(int requestCode, Intent data) {
		if (requestCode == SELECT_PIC_BY_PICK_PHOTO) // 从相册取图片，有些手机有异常情况，请注意
		{
			if (data == null) {
				Toast.makeText(this, "选择图片文件出错", Toast.LENGTH_LONG).show();
				return;
			}
			photoUri = data.getData();
			if (photoUri == null) {
				Toast.makeText(this, "选择图片文件出错", Toast.LENGTH_LONG).show();
				return;
			}
			cropImage(photoUri);
		}
		if(requestCode==SELECT_PIC_BY_TACK_PHOTO){
			//photoUri = data.getData();
			if (photoUri == null) {
				Toast.makeText(this, "选择图片文件出错", Toast.LENGTH_LONG).show();
				return;
			}
			cropImage(photoUri);
		}
		if (requestCode == PHOTO_RESOULT) {
			//photoUri = data.getData();
			/*String[] pojo = { MediaStore.Images.Media.DATA };
			Cursor cursor = managedQuery(photoUri, pojo, null, null, null);
			if (cursor != null) {
				int columnIndex = cursor.getColumnIndexOrThrow(pojo[0]);
				cursor.moveToFirst();
				picPath = cursor.getString(columnIndex);
				cursor.close();
			}
			Log.i(TAG, "imagePath = " + picPath);*/
			/*
			 * if(picPath != null && ( picPath.endsWith(".png") ||
			 * picPath.endsWith(".PNG") ||picPath.endsWith(".jpg")
			 * ||picPath.endsWith(".JPG") ))
			 */
			/*if (picPath != null) {
				lastIntent.putExtra(KEY_PHOTO_PATH, picPath);
				setResult(Activity.RESULT_OK, lastIntent);
				finish();
			} else {
				Toast.makeText(this, "选择文件不正�?", Toast.LENGTH_LONG).show();

			}*/
			Bundle d=data.getExtras();
			if(d!=null){
				Bitmap bm=d.getParcelable("data");
				SimpleDateFormat sdf = new SimpleDateFormat("MMddhhmmss");
				Date dt = new Date();
				String picN = "cy"+sdf.format(dt)+".jpg";
				File temp=new File(this.getCacheDir(),picN);
				if(temp.exists())temp.delete();
				try {
					temp.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					bm.compress(CompressFormat.JPEG, 100, new FileOutputStream(temp));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				bm.recycle();
				bm=null;
				lastIntent.putExtra(KEY_PHOTO_PATH, temp.getAbsolutePath());
				setResult(Activity.RESULT_OK, lastIntent);
				finish();
			}
		}

	}

	private void cropImage(Uri uri) {
		System.out.println("cropImages");
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽�?
		intent.putExtra("outputX", 500);
		intent.putExtra("outputY", 250);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, PHOTO_RESOULT);

	}
}
