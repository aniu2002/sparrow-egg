package com.sparrow.collect.index.format;

import com.sparrow.collect.utils.StringKit;

public class ReverseStringFormat implements StringFormat {

    @Override
    public String format(String string) {
        return StringKit.reverseString(string);
    }

}
