package com.sparrow.collect.index.format;

import com.sparrow.collect.utils.StringKit;

public class MeanfulSameStringFormat implements StringFormat {

    @Override
    public String format(String string) {
        return StringKit.removeSpecialChars(string);
    }
}
