package net.kcrt.auemoji;

import java.util.Iterator;
import org.json.*;
import android.content.Context;
import android.util.Log;

public class EmojiList {

	public static EmojiChar Emoji[];
	
	public static int LoadEmojiStr(String EmojiStr){
		
		// JSON遅いので自前バージョン Splitするのもちょっと遅いっぽい
		if(Emoji != null) { return 0;}	
		String s;
		int EmojiStrLen = EmojiStr.length();
		Emoji = new EmojiChar[(EmojiStrLen + 1) / 8];
		int p = 0; int nextcomma; int i = 0;
		do{
			nextcomma = EmojiStr.indexOf(',', p);
			if(nextcomma == -1){
				nextcomma = EmojiStrLen;
			}
			s = EmojiStr.substring(p, nextcomma);
			Emoji[i] = new EmojiChar();
			Emoji[i].SetCodepoint(s);
			i++;
			p = nextcomma + 1;
		}while(nextcomma != EmojiStrLen);
		return 0;
		
	}
	
	public static int LoadEmojiJson(String EmojiJsonStr){
		
		// 当初はJSON使うつもりでしたが、あまりにLoadに時間がかかるのでお蔵入りになりました。
		if(Emoji != null){ return 0;}
		
		try{
			Log.d("auEmoji", "LoadStart");
			JSONArray jsons = new JSONArray(EmojiJsonStr);
			int nEmoji = jsons.length();
			Emoji = new EmojiChar[nEmoji];
			
			for(int i=0; i<nEmoji; i++){
				JSONArray value = jsons.getJSONArray(i);
				Emoji[i] = new EmojiChar();
				Emoji[i].SetCodepoint(value.getString(0));
				// TODO: Emoji[i].SetGenre(value[1]);
				// TODO: Emoji[i].SetInformation(value[2, ...]);
			}
			Log.d("auEmoji", "LoadEnd");
		}catch(Exception e){
			Log.e("auEmoji", e.getMessage());
		}
		
		return 1;
	
	}

}
