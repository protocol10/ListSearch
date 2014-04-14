package com.akshay.listsearch;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Search extends Activity implements OnItemClickListener, TextWatcher {

	ArrayList<String> items_list;
	ArrayAdapter<String> adapter;
	ListView item_list_view ;
	EditText search_text;
	String s[]={"Play","Delete"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		init();
		
	}

	private void init() {
		// TODO Auto-generated method stub
		item_list_view=(ListView) findViewById(R.id.search_list);
		search_text=(EditText) findViewById(R.id.search_text);
		search_text.addTextChangedListener(this);
		items_list=new ArrayList<String>();
		items_list.add("INDIA");
		items_list.add("SPAIN");
		items_list.add("GERMANY");
		items_list.add("RUSSIA");
		items_list.add("BRAZIL");
		items_list.add("ENGLAND");
		items_list.add("HOLLAND");
		items_list.add("GREAT BRITAIN");
		adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items_list);
		item_list_view.setAdapter(adapter);
		item_list_view.setOnItemClickListener(this);
		item_list_view.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				showDialogActivity(position);
				//removeItem(position);
				return false;
			}
		});
	}

	protected void showDialogActivity(final int position) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder=new AlertDialog.Builder(Search.this);
		builder.setTitle("Choose Option").setItems(s, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(), s[which], Toast.LENGTH_SHORT).show();'
				String name=s[which];
				Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
				if(name.equals("Delete")){
					removeItem(position);
				}
			}
		});
		builder.show();
	}

	protected void removeItem(int position) {
		// TODO Auto-generated method stub
		
		items_list.remove(position);
		adapter.notifyDataSetChanged();
		adapter.notifyDataSetInvalidated();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int index, long id) {
		// TODO Auto-generated method stub
		int position=index;
		String name=items_list.get(position);
		Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
		
		
	}

	@Override
	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		Search.this.adapter.getFilter().filter(s);
	}

}
