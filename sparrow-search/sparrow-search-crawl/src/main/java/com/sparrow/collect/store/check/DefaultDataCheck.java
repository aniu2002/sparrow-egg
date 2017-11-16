package com.sparrow.collect.store.check;

import com.sparrow.collect.orm.session.DefaultSession;
import com.sparrow.collect.utils.PropertyUtils;

/**
 * Project Name: sparrow-egg
 * Package Name: com.sparrow.collect.store.check
 * Author : YZC
 * Date: 2016/12/12
 * Time: 18:42
 */
public class DefaultDataCheck implements TagCheck {

    @Override
    public boolean checkTag(Object object) {
        return false;
    }

    @Override
    public void saveTag(Object object) {

    }

    @Override
    public void close() {

    }
}
