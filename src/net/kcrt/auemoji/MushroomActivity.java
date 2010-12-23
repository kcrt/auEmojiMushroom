package net.kcrt.auemoji;

/*
 * au Emoji Mushroom
 * 	マッシュルームを使用してau絵文字を入力するアプリケーション
 * 
 * 	Programmed by kcrt <kcrt __atmark__ kcrt.net>
 * 		More information, please refer to http://www.kcrt.net/
 * 
 * 	$id:$
 */

import net.kcrt.auemoji.R.id;
import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MushroomActivity extends Activity implements OnClickListener{

	private static final String ACTION_INTERCEPT = "com.adamrocker.android.simeji.ACTION_INTERCEPT";
	private static final String REPLACE_KEY = "replace_key";
	private boolean IsMushroomMode;

	private EmojiListAdapter EmojiFullList;
	private RecentEmojiListAdapter EmojiRecentList;
	
	/** Called when the activity is first created. */
    @Override public void onCreate(Bundle savedInstanceState) {
    	
		super.onCreate(savedInstanceState);

		// データのロード
		RecentEmojiList.SetRecentEmoji(
		this.getResources().getIntArray(R.array.DefaultRecentEmoji)
		);
		
		Intent it = getIntent();
		if(it.getAction() == ACTION_INTERCEPT){
			/* Called from Simeji, or Mushdoor */
			IsMushroomMode = true;
			String mReplaceString = it.getStringExtra(REPLACE_KEY); // 置換元の文字を取得
			if(mReplaceString.length() != 0){
				// TODO:convert: 例 晴れ -> 0xE488
			}
		}else{
			/* not called from simeji */
			IsMushroomMode = false;
		}
        setContentView(R.layout.main);

		if(!IsMushroomMode){
			((Button)this.findViewById(id.cmdOK)).setText(string.copy);
		}
		((Button)this.findViewById(id.cmdOK)).setOnClickListener(this);        
		((Button)this.findViewById(id.cmdFulllist)).setOnClickListener(this);        
        
		//EmojiList.LoadEmoji(getString(R.string.emojijson));
		EmojiList.LoadEmojiStr(getString(R.string.emojistr));
		
		EmojiFullList = new EmojiListAdapter();
		EmojiFullList.setOnClickListener(this);
		GridView grvList = (GridView) this.findViewById(R.id.grvList);
		grvList.setAdapter(EmojiFullList);
		
    }

    int SetWindowEmoji(){	
    	return 1;
    }
    
    @Override public void onSaveInstanceState(Bundle bundle){
    	super.onSaveInstanceState(bundle);
		LinearLayout laySelected = (LinearLayout) this.findViewById(id.laySelected);
		int Emoji[] = new int[laySelected.getChildCount()];
		for(int i = 0; i < laySelected.getChildCount(); i++){
			Emoji[i] = (Integer) ((TextView)laySelected.getChildAt(i)).getTag();
		}
   		bundle.putIntArray("Emoji", Emoji);
   		bundle.putIntArray("Recent", RecentEmojiList.GetRecentEmoji());
    }
    @Override public void onRestoreInstanceState(Bundle bundle){
    	super.onRestoreInstanceState(bundle);
    	int Emoji[] = bundle.getIntArray("Emoji");
		LinearLayout laySelected = (LinearLayout) this.findViewById(id.laySelected);
		if(Emoji != null && Emoji.length != 0){
//			laySelected.removeAllViews();
			for(int i = 0; i < Emoji.length; i++){
				AddEmoji(Emoji[i]);
			}
		}
		RecentEmojiList.SetRecentEmoji(bundle.getIntArray("Recent"));
    }

	@Override public void onClick(View view) {
		
    	switch(view.getId()){
    	case id.cmdFulllist:
    		if(((ToggleButton)view).isChecked()){
    			((GridView)this.findViewById(id.grvList)).setAdapter(EmojiFullList);    			
    		}else{
    			((GridView)this.findViewById(id.grvList)).setAdapter(EmojiRecentList);
    		}
    		break;
   		case id.cmdOK:
			// コピー または データを返して終了
   			StringBuilder EmoStr = new StringBuilder();
   			LinearLayout laySelected = (LinearLayout) this.findViewById(id.laySelected);
   			for(int i = 0; i < laySelected.getChildCount(); i++){
   				EmoStr.append(((TextView)laySelected.getChildAt(i)).getText().toString());
   			}
			if(IsMushroomMode){
				// Simejiにデータを返す
				this.getIntent().putExtra(REPLACE_KEY, EmoStr.toString());
			}else{
				// クリップボードにコピーするu
				
				
				 ClipboardManager cm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE); 
				 cm.setText(EmoStr.toString());
			}
			finish();
			break;
    	default:
    		switch(((View) view.getParent()).getId()){
    		case id.grvList:
    			// 絵文字たち
    			AddEmoji((Integer)((TextView)view).getTag());
        		if(!((ToggleButton)this.findViewById(id.cmdContinuous)).isChecked()){
        			// これで終了
        			this.onClick(this.findViewById(id.cmdOK));
        		}
	    		break;
	    	case id.laySelected:
	    		// 選択済みの絵文字たち
	    		((LinearLayout)view.getParent()).removeView(view);
	    		break;
    		}
    	}
    			
	}

	private void AddEmoji(Integer codepoint) {
        Button b = new Button(this);
        b.setText(EmojiChar.CodeToChar(codepoint));
        b.setTag(codepoint);
        b.setTextSize(18);
        b.setBackgroundColor(Color.rgb(128, 128, 128));
        b.setOnClickListener(this);
        ((LinearLayout)this.findViewById(id.laySelected)).addView(b);		
	}
    
}