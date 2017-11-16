package com.sparrow.collect.cache.bloom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Project Name: test-parent
 * Package Name: com.sparrow.collect.website.bloom
 * Author : YZC
 * Date: 2016/12/9
 * Time: 12:44
 */
public class DuplicateUrlCheck implements UrlCheck {
    private static final Object synObject = new Object();
    private static DuplicateUrlCheck instance;
    private final File file;
    private final BloomFilter bloomFilter;
    private final boolean bloomFilterExists;
    
    public static final UrlCheck DEFAULT_CHECK = new UrlCheck() {
        @Override
        public boolean check(String url) {
            return false;
        }

        @Override
        public void add(String url) {

        }

        @Override
        public void close() {

        }
    };

    private DuplicateUrlCheck(String dir) {
        File f = new File(dir);
        if (!f.exists())
            f.mkdirs();
        this.file = new File(f, "bloomFilter.data");
        if (file.exists()) {
            bloomFilter = new BloomFilter();
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(file);
                bloomFilter.readObject(fileInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e.getMessage());
                }
            }
            bloomFilterExists = true;
        } else {
            bloomFilter = new BloomFilter(-1);
            bloomFilterExists = false;
        }
    }

    public static final DuplicateUrlCheck getInstance(String dir) {
        if (instance == null) {
            synchronized (synObject) {
                if (instance == null)
                    instance = new DuplicateUrlCheck(dir);
            }
        }
        return instance;
    }

    public boolean existsBloomData() {
        return this.bloomFilterExists;
    }

    public boolean check(String url) {
        return bloomFilter.contains(url);
    }

    public void add(String url) {
        bloomFilter.add(url);
    }

    public void close() {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            bloomFilter.writeObject(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null)
                    fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
