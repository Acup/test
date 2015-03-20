package keyword.kdelete;

import util.ArrayUtil;
import util.CharArray;
import keyword.KeywordFilter;

public final class KDeleteFilter extends KeywordFilter {

	private static KeywordFilter keywordFilter = new KDeleteFilter();

	private ArrayUtil charArrayUtil = CharArray.create();

	private KDeleteFilter() {
	}

	public static KeywordFilter creaet() {
		
		return keywordFilter;
	}

	@Override
	public char[] filter(char[] content) {
		char[] after = new char[content.length];
		return charArrayUtil.trimArray(after);
	}

}