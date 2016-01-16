package com.example.idml;
import java.io.*;
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
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GraphActivity extends Activity{
	private ImageView iv_image;
	private final static String TAG = "GraphActivity";
	private String mStrMsg="";
	private BufferedReader mBufferedReader=null;
	private final String DEBUG_TAG="Activity01";
	String picPath = null;
	private Button button_id = null;
	private TextView mTextView = null;
	Socket socket=null;
	final BitmapFactory.Options options = new BitmapFactory.Options();
	
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.graph);
		picPath=getPhotopath();
		iv_image = (ImageView) findViewById(R.id.iv_image);
		button_id=(Button) findViewById(R.id.button_id);
		mTextView=(TextView) findViewById(R.id.tishi);
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(picPath, options);
		options.inSampleSize = BitmapUtil.calculateInSampleSize(options,
				1280, 720);
		options.inJustDecodeBounds = false;
		Bitmap bitmap = BitmapFactory.decodeFile(picPath, options);
		iv_image.setImageBitmap(bitmap);
		button_id.setOnClickListener(listener);
	}
	
	OnClickListener listener = new OnClickListener() {
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.button_id:
				new Thread(networkTask).start();
				break;
			}
		}
	};
	
	
	Runnable networkTask = new Runnable() {  
	    @Override  
	    public void run() {  
	        // TODO  
	        // 在这里进行 http request.网络请求相关操作  
	    	
			Log.e(TAG, "landscape");
			try {
				socket=new Socket("192.168.191.1",40000);
				DataOutputStream out=new DataOutputStream(socket.getOutputStream());
				mBufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
				Bitmap bitmap = BitmapFactory.decodeFile(picPath, options);
				ByteArrayOutputStream baos=new ByteArrayOutputStream();
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
				byte[] bytes=baos.toByteArray();
				out.write(bytes);
				socket.shutdownOutput();
				new Thread(mRunnable).start();
				
			} catch (Exception e) {
				 e.printStackTrace();
			} 
	    }  
	};  
	
	private Runnable mRunnable=new Runnable(){
		public void run(){
			while(true){
				try{
					
					if((mStrMsg=mBufferedReader.readLine())!=null){
						mHandler.sendMessage(mHandler.obtainMessage());
						
						
					}
				}catch(Exception e){
					Log.e(DEBUG_TAG,e.toString());
				}
			}
		}
	};
	
	Handler mHandler = new Handler() {  
	    @Override  
	    public void handleMessage(Message msg) {  
	        super.handleMessage(msg);  
	        try {
	        	mTextView.setText(mStrMsg);
			} catch (Exception e) {
				// TODO: handle exception
				Log.e(DEBUG_TAG,e.toString());
			}  
	        // TODO  
	        // UI界面的更新等相关操作  
	    }  
	};
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
}
