package com.hybroad.picplayer.adapter;

import java.util.List;
import java.util.Map;

import com.hybroad.picplayer.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {
	
	private Context context;
	private List<Map<String, Object>> list;
	
	public GridAdapter(Context context, List<Map<String, Object>> list){
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, null);
			holder.image = (ImageView) convertView.findViewById(R.id.item_image);
			holder.name = (TextView) convertView.findViewById(R.id.item_name);
			convertView.setTag(holder);
			
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		Map<String, Object> map = list.get(position);
		holder.image.setImageBitmap((Bitmap) map.get("image"));
		holder.name.setText(map.get("name").toString());
		
		convertView.setTag(holder);
		return convertView;
	}
	
	class ViewHolder{
		public ImageView image;
		public TextView name;
	}

}
