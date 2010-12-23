package net.kcrt.auemoji;


public class RecentEmojiListAdapter extends EmojiListAdapter{


	@Override public int getCount() {
		return RecentEmojiList.NumEmoji;
	}

	@Override public Object getItem(int position) {
		return RecentEmojiList.Emoji[position].toString();
	}

	@Override public long getItemId(int position) {
		return RecentEmojiList.Emoji[position].Codepoint;
	}

}
