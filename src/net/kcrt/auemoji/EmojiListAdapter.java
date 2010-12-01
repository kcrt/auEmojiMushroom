package net.kcrt.auemoji;

import android.database.DataSetObserver;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ListAdapter;
import android.widget.TextView;

public class EmojiListAdapter implements ListAdapter{

	OnClickListener listener;
	
	@Override public boolean areAllItemsEnabled() {
		return true;
	}

	@Override public boolean isEnabled(int position) {
		return true;
	}

	@Override public int getCount() {
		return EmojiList.Emoji.length;
	}

	@Override public Object getItem(int position) {
		return EmojiList.Emoji[position].toString();
	}

	@Override public long getItemId(int position) {
		return EmojiList.Emoji[position].Codepoint;
	}

	@Override public int getItemViewType(int position) {
		return 0;
	}

	@Override public View getView(int position, View convertView, ViewGroup parent) {

		if(convertView == null)
			convertView = new TextView(parent.getContext());
		
		TextView t = (TextView)convertView;
		t.setText((String)this.getItem(position));
		t.setGravity(Gravity.CENTER);
		t.setPadding(12, 12, 12, 12);
		t.setTextSize(24);
		t.setTag((Integer)(int)this.getItemId(position));
		t.setOnClickListener(listener);
		
		return convertView;
	}

	@Override public int getViewTypeCount() {
		return 1;
	}

	@Override public boolean hasStableIds() {
		return true;
	}

	@Override public boolean isEmpty() {
		return getCount()==0;
	}

	@Override public void registerDataSetObserver(DataSetObserver observer) {
		return;
	}

	@Override public void unregisterDataSetObserver(DataSetObserver observer) {
		return;
	}
	public void setOnClickListener(OnClickListener _listener){
		listener = _listener;
	}

}
