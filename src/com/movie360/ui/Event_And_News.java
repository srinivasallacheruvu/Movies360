package com.movie360.ui;

import java.util.List;

import com.movie360.dto.NewsDTO;
import com.movie360.service.Movie360Service;
import com.movie360.service.impl.Movie360ServiceImpl;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Event_And_News  extends Activity{
	
	private ProgressDialog dialog;
	private Movie360Service service;
	private Context context;
	private ListView eventList;
	LayoutInflater eventInflater;
	List<NewsDTO> sections;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_and_news);
		service = new Movie360ServiceImpl(context);
		
		eventList=(ListView) findViewById(R.id.event_List);
		eventInflater = LayoutInflater.from(getApplicationContext());
	
		
		
		
		ENTask enTask = new ENTask();
		enTask.execute();
	}
	
	private class ENTask extends AsyncTask<Void, Void, Void>{
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
			dialog.show();
			
			}
			
			@Override
			protected Void doInBackground(Void... params) {
			
			NewsDTO n=	service.getNewsDetails();
					sections=n.getSubSections();
			return null;
			}
			
			@Override
			protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			dialog.dismiss();
			eventList.setAdapter(new Adapter_Demo());
			eventList.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
			                                                                                      		
				}
				
			});
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
				arg1 = eventInflater.inflate(R.layout.listinflate_livetv, null);
				holder = new ListViewHolder();
				holder.textName = (TextView) arg1.findViewById(R.id.text_name);
				holder.icon = (ImageView) arg1.findViewById(R.id.icon);
				arg1.setTag(holder);
			} else {
				holder = (ListViewHolder) arg1.getTag();
			}
		//	holder.textName.setText(sections.get(arg0).getTitle());
		//	holder.textName.setGravity(Gravity.CENTER_VERTICAL);
			holder.icon.setImageResource(R.drawable.game);
			
			
			return arg1;
		}
		public class ListViewHolder {
		TextView textName;
		ImageView icon;
	}
	}
	private void initializeProgressDialog() {

		dialog = new ProgressDialog(this);
		dialog.setMessage("Processing...");
		dialog.setIndeterminate(true);
		dialog.setCancelable(true);

	}
}
