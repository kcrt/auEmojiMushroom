package net.kcrt.auemoji;

// 参考文献:
//	Emoji Symbols: Background Data <<http://unicode.org/~scherer/emoji4unicode/snapshot/full.html>>
//	emoji4unicode <<http://groups.google.com/group/emoji4unicode>>
//	絵文字を使いこなして見るためのページ <<http://trialgoods.com/emoji/?career=au&page=all>>

public class EmojiChar {
	int Codepoint;
	// String[] Info;
	// String Genre;
	public int SetCodepoint(int _Codepoint){
		return this.Codepoint = _Codepoint;
	}
	public int SetCodepoint(String _Codepoint){
		return this.SetCodepoint(Integer.decode(_Codepoint));
	}
	public @Override String toString(){
		return new String(Character.toChars(Codepoint));
	}
	public static String CodeToChar(int code){
		return new String(Character.toChars(code));
	}
/*	boolean HasInfo(String mInfo){
		for(String s: Info){
			if(s == mInfo){
				return true;
			}
		}
		Integer a;
		return false;
	}*/
}
