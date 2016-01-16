package com.example.idml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import com.hanvon.utils.BitmapUtil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	private ImageButton btnRecFromCamera;
	String picPath = null;
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_page);
		picPath = getPhotopath();
		btnRecFromCamera = (ImageButton) findViewById(R.id.btn_rec_from_camera);
		btnRecFromCamera.setOnClickListener(this);
	}
	public void onClick(View v) {
		if (v == btnRecFromCamera) {
			Intent intentPhote = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			File out = new File(picPath);
			Uri uri = Uri.fromFile(out);
			intentPhote.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
			intentPhote.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intentPhote, 1);
            
            
		}
	}
	
	private String getPhotopath() {
		// 照片全路径
		String fileName;
		// 文件夹路径
		String pathUrl = Environment.getExternalStorageDirectory() + "/IDMLimage/";
		String imageName = "imageOne.jpg";
		File file = new File(pathUrl);
		file.mkdirs();// 创建文件夹
		fileName = pathUrl + imageName;
		return fileName;
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 super.onActivityResult(requestCode, resultCode, data);
		 Intent intent = new Intent(this, GraphActivity.class);
		 startActivity(intent);
	}
}
