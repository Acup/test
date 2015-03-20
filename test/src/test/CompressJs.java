package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

import util.ArrayUtil;
import util.CharArray;

public class CompressJs {

	private static int cache = 100;

	private static int temporaryCache = 10;

	private static ArrayUtil arrayUtil = CharArray.create();

	@Test
	public void start() {
		char[] cache = new char[this.cache];
		try {
			FileReader fileInputStream = new FileReader("D:\\work\\project\\test\\src\\test\\jquery-1.11.2.js");
			char[] lastChars = new char[temporaryCache];
			if (fileInputStream.ready()) {
				while (fileInputStream.read(cache) > 0) {
					cache = filterVar(cache, lastChars);

					newFile(cache);
				}
			}
			fileInputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public char[] filterNewLine(char[] cache) {
		char[] resultArray = new char[cache.length];
		for (int i = 0, j = 0; i < cache.length; i++, j++) {
			if (cache[i] != '\n') {
				resultArray[j] = cache[i];
			}
		}
		return arrayUtil.trimArray(resultArray);
	}

	public char[] filterVar(char[] cache, char[] lastChars) throws IOException {
		int i = 0;
		int lcl = lastChars.length;
		char[] resultArray = new char[cache.length];
		boolean isV = false;
		boolean isVa = false;
		if (isVar(lastChars[lcl - 3], lastChars[lcl - 2], lastChars[lcl - 1])) {
			i++;
		} else if (lastChars[lcl - 2] == 'v' && lastChars[lcl - 1] == 'a') {
			isVa = true;
		} else if (lastChars[lcl - 1] == 'v') {
			isV = true;
		}
		for (int j = 0; i < cache.length; i++, j++) {
			if (cache[i] == ' ') {

			}
			if (i == 0) {
				if (isV && isVar('v', cache[i], cache[i + 1])) {
					resultArray[j] = cache[i];
					resultArray[++j] = cache[++i];
					i++;
				} else if (i == 0 && isVa && isVar('v', 'a', cache[i])) {
					resultArray[j] = cache[i++];
				}
				continue;
			}
			if (i + 2 < cache.length && isVar(cache[i], cache[i + 1], cache[i + 2])) {
				resultArray[j] = cache[i];
				resultArray[++j] = cache[++i];
				resultArray[++j] = cache[++i];
				i++;
				continue;
			}
			resultArray[j] = cache[i];
		}
		return arrayUtil.trimArray(resultArray);
	}

	private boolean isVar(char... c) {
		if (c[0] == 'v' && c[1] == 'a' && c[2] == 'r') {
			return true;
		}
		return false;
	}

	public void newFile(char[]... chars) throws IOException {
		char[] merageChar = arrayUtil.mergeCharArrays(chars);
		File file = new File("jquery-1.11.2.js");
		FileWriter fos = new FileWriter(file);
		fos.write(merageChar);
		fos.close();
	}

}
