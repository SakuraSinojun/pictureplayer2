package com.hybroad.picplayer.activity;

import com.hybroad.picplayer.R;
import com.hybroad.picplayer.util.Utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ShowActivity extends Activity {
	
	private String path;
	private ImageView image;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_pic);
		
		init();
		image.setImageBitmap(Utils.getPicByPath(path));
		String name = path.substring(path.lastIndexOf("/")+1);
		Utils.showMessage(this, name);
		
		image.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String name = path.substring(path.lastIndexOf("/")+1);
				Utils.showMessage(ShowActivity.this, name);
			}
		});
	}
	
	public void init(){
		image = (ImageView) findViewById(R.id.show_image);
		intent = getIntent();
		path = intent.getExtras().getString("path");
	}
}
