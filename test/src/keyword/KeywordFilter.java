package keyword;


public abstract class KeywordFilter implements Filter {
	
	/**
	 * -1:不存在未完结的关键字 <br/>
	 * 0:关键字处于上一次结尾处 <br/>
	 * 1~:关键字未完，数字表示向后推移位数<br/>
	 * end:上次查询结束时处于末尾的字符集合<br/>
	 * start:当前查询的字符集合<br/>
	 * keyword:检查关键字
	 */
	public int endValidate(char[] end, char[] start, String keyword) {
		
		char[] keywordChar = keyword.toCharArray();

		int unfinished = 0;// 关键字检验未完成部分所在位置

		int point = -1;// start中包含未完成部分的位置

		for (int i = 0, j = 0; i < end.length; i++) {
			if (end[i] == keywordChar[j]) {
				unfinished = j;
				j++;
			} else {
				j = 0;
				unfinished = 0;
			}
		}

		if (unfinished == keywordChar.length - 1) {
			point = 0;
		} else if (unfinished > 0) {
			for (int i = 0, j = unfinished; i < start.length; i++) {
				if (start[i] == keywordChar[j]) {
					if (j == keywordChar.length - 1) {
						point = i;
						break;
					}
					unfinished = j;
					j++;
				} else {
					break;
				}
			}
		}
		return point;
	}

}
