package com.hybroad.picplayer.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

public class Utils {
	
	public static final String SD_PATH = "/mnt/sdcard";
	public static final String FOLDER_PATH = SD_PATH + "/picPlayer/folder.png";
	public static final String LASTDIR_PATH = SD_PATH + "/picPlayer/lastdir.png";
	public static String lastPath = null;//shang ji mu lu
	
	public static List<Map<String, Object>> getData(String path){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = null;
		
		lastPath = path;
		Log.i("utils", "last1:" + lastPath);
		if(lastPath != null && !SD_PATH.equals(lastPath)){
			String last = lastPath.substring(0,lastPath.lastIndexOf("/"));
			Log.i("utils", "last2:" + last);
			map = new HashMap<String, Object>();
			Bitmap bm = BitmapFactory.decodeFile(LASTDIR_PATH);
			map.put("image", bm);
			try {
				map.put("name", new String("∑µªÿ…œº∂".getBytes(),"utf-8"));
			} catch (UnsupportedEncodingException e) {
				map.put("name", "return");
				e.printStackTrace();
			}
			map.put("path", last);
			list.add(map);
		}
		File file = new File(path);
		if(file.exists()){
			File[] fs = file.listFiles();
			for (File f : fs) {
				map = new HashMap<String, Object>();
				Bitmap bm = null;
				if(f.isDirectory()){
					bm = BitmapFactory.decodeFile(FOLDER_PATH);
				}else if(isPicture(f)){
					bm = BitmapFactory.decodeFile(f.getAbsolutePath());
				}
				map.put("image", bm);
				map.put("name", f.getName());
				map.put("path", f.getAbsolutePath());
				list.add(map);
			}
		}
		
		return list;
	}
	
	public static boolean isPicture(File file){
		boolean flag = false;
		if(file != null){
			String name = file.getName();
			if(name.endsWith(".jpeg") || name.endsWith(".png") || name.endsWith(".gif")){
				flag = true;
			}else{
				flag = false;
			}
		}else{
			flag = false;
		}
		return flag;
	}

	public static void showMessage(Context context, String text){
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}
}
