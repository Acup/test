package util;

public final class CharArray extends ArrayUtil {
	private static ArrayUtil arrayUtil = null;

	private CharArray() {
	}

	@Override
	public char[] mergeCharArrays(char[]... chars) {
		int totleLength = 0;
		for (char[] c : chars) {
			totleLength += c.length;
		}
		int index = 0;
		char[] mergeArray = new char[totleLength];
		for (char[] cs : chars) {
			for (char c : cs) {
				mergeArray[index++] = c;
			}
		}
		return mergeArray;
	}

	@Override
	public char[] trimArray(char[] chars) {
		int size = chars.length;
		int i = 0;
		for (i = chars.length - 1; i >= 0; i--) {
			if (chars[i] == '\0' && chars[i - 1] != '\0') {
				size--;
			}
		}
		i = 0;
		char[] trimArray = new char[size];
		for (char c : chars) {
			trimArray[i] = c;
			i++;
		}
		return trimArray;
	}

	public static ArrayUtil create() {
		return arrayUtil == null ? new CharArray() : arrayUtil;
	}

}
