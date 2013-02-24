package com.movie360.ui;



import java.util.ArrayList;
import java.util.List;

import com.movie360.dto.VideosDTO;
import com.movie360.service.Movie360Service;
import com.movie360.service.impl.Movie360ServiceImpl;



import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore.Video;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Videos  extends Activity implements OnItemClickListener{
	ListView videoListView;
	LayoutInflater videoinflater; 
	Context context=this;
	Movie360Service service;
	List<VideosDTO> sections;
	private ProgressDialog dialog;
	Dialog dDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.videos);
		
		videoinflater = LayoutInflater.from(getApplicationContext());
		videoListView=(ListView) findViewById(R.id.videoListView);
		service= new Movie360ServiceImpl(context);
		sections= new ArrayList<VideosDTO>();
		initializeProgressDialog();
		
		VideoTask vTask = new VideoTask();
		vTask.execute();
	}
	
	class VideoTask extends AsyncTask<Void, Void, Void>{
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
			 dialog.show();
			
			}
			@Override
			protected Void doInBackground(Void... params) {
				
				sections=service.getVideosDetails().getSubSections();
				return null;
			}
			@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				
				dialog.dismiss();
				
				videoListView.setAdapter(new Adapter_Demo());
				videoListView.setOnItemClickListener(Videos.this);
			}
	}
	
	private class Adapter_Demo extends BaseAdapter{
		@Override
		public int getCount() {
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
				arg1 = videoinflater.inflate(R.layout.listinflate_livetv, null);
				holder = new ListViewHolder();
				holder.textName = (TextView) arg1.findViewById(R.id.text_name);
				holder.icon = (ImageView) arg1.findViewById(R.id.icon);
				arg1.setTag(holder);
			} else {
				holder = (ListViewHolder) arg1.getTag();
			}
			holder.textName.setText(sections.get(arg0).getSection());
		//	holder.textName.setGravity(Gravity.CENTER_VERTICAL);
			holder.icon.setImageResource(R.drawable.video);
	
			return arg1;
		}
		public class ListViewHolder {
		TextView textName;
		ImageView icon;
	}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		
		dDialog= new Dialog(Videos.this);
		dDialog.setContentView(R.layout.dialoglist);
		ListView lld=	(ListView)dDialog.findViewById(R.id.dialogList);
		
		
		String str[]={"package!","package2","package3","package4","package5","package6","package7"};
		
		lld.setAdapter(new ArrayAdapter<String>(Videos.this, android.R.layout.simple_list_item_1,str));
		dDialog.setTitle("Select A Package");
		dDialog.show();
		lld.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0,
					View arg1, int arg2, long arg3) {
				dDialog.dismiss();	
				Toast.makeText(getApplicationContext(), ""+arg2, 500).show();
				
			}
		});
	
	}
	
	private void initializeProgressDialog() {

		dialog = new ProgressDialog(this);
		dialog.setMessage("Processing...");
		dialog.setIndeterminate(true);
		dialog.setCancelable(true);

	}
}
