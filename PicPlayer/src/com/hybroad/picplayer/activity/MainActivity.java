package com.hybroad.picplayer.activity;

import java.io.File;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.hybroad.picplayer.R;
import com.hybroad.picplayer.adapter.GridAdapter;
import com.hybroad.picplayer.util.Utils;

public class MainActivity extends Activity {
	
	private GridView grid;
	private List<Map<String, Object>> list = null;
	private GridAdapter adapter;
	public static String lastpath = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		
		lastpath = Utils.SD_PATH;
		list = Utils.getData(Utils.SD_PATH);
		adapter = new GridAdapter(MainActivity.this,list);
		grid.setAdapter(adapter);
		
		grid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, 
					int position,long id) {
				String path = (String) list.get(position).get("path");
				File file = new File(path);
				if(file.isDirectory()){
					list = Utils.getData(path);
					adapter = new GridAdapter(MainActivity.this, list);
					grid.setAdapter(adapter);
				}else if(Utils.isPicture(file)){
					Intent intent = new Intent(MainActivity.this,ShowActivity.class);
					intent.putExtra("path", path);
					startActivity(intent);
				}
			}
		});
	}
	
	public void init(){
		grid = (GridView) findViewById(R.id.grid);
	}

}
