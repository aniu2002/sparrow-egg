package com.sparrow.collect.parse;

import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.FieldInfo.IndexOptions;

public class SameNotStoreFieldStrategy implements FieldParseStrategy {

    @Override
    public IndexableField parse(String searchID,String fieldName, String fieldValue) {
        FieldType fieldType = new FieldType();
        fieldType.setIndexed(true);
        fieldType.setTokenized(false);
        fieldType.setOmitNorms(true);
        fieldType.setIndexOptions(IndexOptions.DOCS_ONLY);
        fieldType.setStored(false);
        IndexableField field = new Field(fieldName, fieldValue, fieldType);
        return field;
    }

}
