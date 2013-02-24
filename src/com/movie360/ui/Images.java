package com.movie360.ui;



import java.util.ArrayList;
import java.util.List;

import com.movie360.dto.ImageDTO;
import com.movie360.service.Movie360Service;
import com.movie360.service.impl.Movie360ServiceImpl;
import com.movie360.ui.AboutMovie.Adapter_Demo.ListViewHolder;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Images  extends Activity{
	
	LayoutInflater linflater; 
	ListView imageListView;
	Context context=this;
	Movie360Service service;
	List<ImageDTO> sections;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.images);
		
		linflater = LayoutInflater.from(getApplicationContext());
		imageListView=(ListView) findViewById(R.id.imagelistView);
		service= new Movie360ServiceImpl(context);
		sections= new ArrayList<ImageDTO>();
		
		StarTask sTask = new StarTask();
		sTask.execute();
	}
	class StarTask extends AsyncTask<Void, Void, Void>{
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		@Override
		protected Void doInBackground(Void... params) {
			
			sections=service.getImageDetails().getSubSections();
			
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			imageListView.setAdapter(new Adapter_Demo());
			imageListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,	int arg2, long arg3) {
					
					Toast.makeText(getApplicationContext(),""+ sections.get(arg2).getId(),500).show();
					
				}
			});
			
		}
		
}
	class Adapter_Demo extends BaseAdapter{
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
				arg1 = linflater.inflate(R.layout.listinflate_livetv, null);
				holder = new ListViewHolder();
				holder.textName = (TextView) arg1.findViewById(R.id.text_name);
				holder.icon = (ImageView) arg1.findViewById(R.id.icon);
				arg1.setTag(holder);
			} else {
				holder = (ListViewHolder) arg1.getTag();
			}
			holder.textName.setText(sections.get(arg0).getSection());
		//	holder.textName.setGravity(Gravity.CENTER_VERTICAL);
			holder.icon.setImageResource(R.drawable.game);
			
			
			return arg1;
		}
		public class ListViewHolder {
		TextView textName;
		ImageView icon;
	}
	}
}
