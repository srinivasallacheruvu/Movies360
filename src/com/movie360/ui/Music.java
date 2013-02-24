package com.movie360.ui;



import java.util.ArrayList;
import java.util.List;

import com.movie360.dto.MusicDTO;
import com.movie360.service.Movie360Service;
import com.movie360.service.impl.Movie360ServiceImpl;
import com.movie360.ui.AboutMovie.Adapter_Demo.ListViewHolder;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Music extends Activity {
	
	ListView musicListView;
	LayoutInflater musicinflater; 
	Context context=this;
	Movie360Service service;
	List<MusicDTO> sections;
	Dialog dialog;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.music);
		musicListView=(ListView) findViewById(R.id.musicListView);
		musicinflater = LayoutInflater.from(getApplicationContext());
		service= new Movie360ServiceImpl(context);
		sections= new ArrayList<MusicDTO>();
	
		MusicTask mTask = new MusicTask();
		mTask.execute();
	}
	
	class MusicTask extends AsyncTask<Void, Void, Void>{
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
			}
			@Override
			protected Void doInBackground(Void... params) {
				sections=service.getMusicDetails().getSubSections();
				return null;
			}
			@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				
				musicListView.setAdapter(new Adapter_Demo());
				musicListView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,	int arg2, long arg3) {
						
						 dialog= new Dialog(Music.this);
						dialog.setContentView(R.layout.dialoglist);
						ListView lld=	(ListView)dialog.findViewById(R.id.dialogList);
						
						
						String str[]={"package!","package2","package3","package4","package5","package6","package7"};
						
						lld.setAdapter(new ArrayAdapter<String>(Music.this, android.R.layout.simple_list_item_1,str));
						dialog.setTitle("Select A Package");
						dialog.show();
						lld.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> arg0,
									View arg1, int arg2, long arg3) {
								dialog.dismiss();	
								Toast.makeText(getApplicationContext(), ""+arg2, 500).show();
								
							}
						});
					}
					
				});
			}
			
	}
	private class Adapter_Demo extends BaseAdapter{
		@Override
		public int getCount() {
		//	return sections.size();
			return sections.size();
		}
		@Override
		public Object getItem(int arg0) {
			return arg0;
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}
		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			ListViewHolder holder=null;
			
			if (arg1 == null) {
				arg1 = musicinflater.inflate(R.layout.listinflate_livetv, null);
				holder = new ListViewHolder();
				holder.textName = (TextView) arg1.findViewById(R.id.text_name);
				holder.icon = (ImageView) arg1.findViewById(R.id.icon);
				arg1.setTag(holder);
			} else {
				holder = (ListViewHolder) arg1.getTag();
			}
			holder.textName.setText(sections.get(arg0).getTitle());
			
		//	holder.textName.setGravity(Gravity.CENTER_VERTICAL);
			//holder.icon.setImageResource(R.drawable.game);
			holder.icon.setImageResource(R.drawable.music);
			return arg1;
		}
		public class ListViewHolder {
		TextView textName;
		ImageView icon;
	}
	}
}
