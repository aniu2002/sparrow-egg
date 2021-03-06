package com.sparrow.orm.dyna.parser;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sparrow.orm.dyna.common.Tool;


public class SqlTool {
	private static final char[] PARAMETER_SEPARATORS = new char[] { '"', '\'',
			':', '&', ',', ';', '(', ')', '|', '=', '+', '-', '*', '%', '/',
			'\\', '<', '>', '^' };

	private static final char CHAR_COMMENTS = '-';
	private static final char CHAR_ASTERISK = '*';
	private static final char CHAR_SLASH = '/';
	static final char CHAR_BACKSLASH = '\\';
	static final char CHAR_QUESTION = '?';
	static final String STR_QUESTION = "?";
	private static final char CHAR_LINE = '\n';
	private static final char CHAR_QUOTES = '\'';
	private static final char CHAR_DOUBLE_QUOTES = '"';

	public static ParsedSql parseSqlStatement(final String sql) {
		if (StringUtils.isEmpty(sql))
			return null;
		StringBuilder sb = new StringBuilder();
		List<NamedParameter> namedItems = new ArrayList<NamedParameter>();

		final char[] statement = sql.toCharArray();
		final int len = sql.length();
		int i = 0, k, lastCut = 0, paraIdx = 0, namedIdx = 0;

		while (i < len) {
			k = skipCommentsAndQuotes(statement, i, len);
			if (k >= len)
				break;
			// 有字符被忽略，那么actual sql需要重新定义最后一次cut的pos
			if (k > i + 1) {
				i = k;
				lastCut = i;
			}
			char c = statement[i];
			// named parameter
			if (c == ':' || c == '&' || c == '#') {
				boolean gl = (c == '#');
				int j = i + 1;
				if (j < len && statement[j] == ':' && c == ':') {
					// Postgres-style "::" casting operator - to be skipped.
					i = i + 2;
					continue;
				}
				while (j < len && !isParameterSeparator(statement[j])) {
					j++;
				}
				if (j - i > 1) {
					String parameter = sql.substring(i + 1, j);
					if (gl && !Tool.isNumeric(parameter))
						throw new RuntimeException("#开始的参数必须是数字,描述的参数数组的下标索引");
					sb.append(sql.substring(lastCut, i)).append(CHAR_QUESTION);
					lastCut = j;
					namedItems.add(new NamedParameter(parameter, paraIdx, gl));
					paraIdx++;
					if (!gl)
						namedIdx++;
				}
				i = j - 1;
			} else if (c == CHAR_QUESTION) {
				// JDBCS parameter
				sb.append(sql.substring(lastCut, i)).append(CHAR_QUESTION);
				lastCut = i + 1;
				paraIdx++;
			}
			i++;
		}
		if (lastCut < len)
			sb.append(sql.substring(lastCut));
		if (paraIdx == 0) {
			return new ParsedSql(sql, sql, null, 0, 0);
		} else {
			if (namedIdx > 0 && paraIdx != namedIdx)
				throw new RuntimeException("'#'和':'不能能同时使用");
			String actualSql = sb.toString();
			return new ParsedSql(sql, actualSql,
					namedItems.toArray(new NamedParameter[namedItems.size()]),
					paraIdx, namedIdx);
		}
	}

	private static int skipCommentsAndQuotes(final char[] statement,
			int position, final int length) {
		// 7 - 6 - 4
		if (position > length - 3)
			return position;
		int pos = position;
		char fChar = statement[pos], sChar = statement[pos + 1];
		// start "--"
		if (fChar == CHAR_COMMENTS) {
			if (sChar == CHAR_COMMENTS) {
				pos = pos + 2;
				for (; pos < length; pos++)
					if (statement[pos] == CHAR_LINE)
						break;
			}
		} else if (fChar == CHAR_SLASH) {
			// start "/*"
			if (sChar == CHAR_ASTERISK) {
				pos = pos + 2;
				for (; pos < length; pos++) {
					// end "*/"
					if (statement[pos] == CHAR_ASTERISK
							&& statement[pos + 1] == CHAR_SLASH)
						break;
				}
				pos++;
			}
		} else if (fChar == CHAR_QUOTES) {
			pos = pos + 1;
			for (; pos < length; pos++)
				if (statement[pos] == CHAR_QUOTES)
					break;
		} else if (fChar == CHAR_DOUBLE_QUOTES) {
			pos = pos + 1;
			for (; pos < length; pos++)
				if (statement[pos] == CHAR_QUOTES)
					break;
		}
		return pos + 1;
	}

	private static boolean isParameterSeparator(char c) {
		if (Character.isWhitespace(c)) {
			return true;
		}
		for (int i = 0; i < PARAMETER_SEPARATORS.length; i++) {
			if (c == PARAMETER_SEPARATORS[i]) {
				return true;
			}
		}
		return false;
	}
}
