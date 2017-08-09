package com.sparrow.collect.website.handler;

import java.io.File;

import com.sparrow.collect.website.utils.FileUtil;


public class TextFileHandler extends FileHandler {

	@Override
	protected String getContent(File file) {
		String contents = FileUtil.getFileContent(file, "utf-8");
		return contents;
	}

}
