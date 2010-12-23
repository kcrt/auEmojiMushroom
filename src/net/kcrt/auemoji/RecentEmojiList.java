package net.kcrt.auemoji;

public class RecentEmojiList {

	final static int NumEmoji = 40;
	public static EmojiChar Emoji[];
	
	
	public static int[] GetRecentEmoji(){
		
		int Codepoints[] = new int[Emoji.length];
		
		for(int i = 0; i < Codepoints.length; i++){
			Codepoints[i] = Emoji[i].Codepoint;
		}
		
		return Codepoints;
		
	}
	
	public static void SetRecentEmoji(int Codepoints[]){
		
		if(Emoji == null) Emoji = new EmojiChar[NumEmoji];
		
		if(Codepoints.length != NumEmoji) throw new ArrayIndexOutOfBoundsException();
		
		for(int i = 0; i < Codepoints.length; i++){
			if(Emoji[i] == null) Emoji[i] = new EmojiChar();
			Emoji[i].SetCodepoint(Codepoints[i]);
		}
		
	}
	
	public static void AddRecentEmoji(int Codepoint){

		int c;
		for(c = 0; c < Emoji.length; c++){
			if(Emoji[c].Codepoint == Codepoint) break;
		}
	
		for(int i = 0; i < c - 1; i++){
			Emoji[i+1].SetCodepoint(Emoji[i].Codepoint);
		}
		Emoji[0].SetCodepoint(Codepoint);
		
	}
	
}
