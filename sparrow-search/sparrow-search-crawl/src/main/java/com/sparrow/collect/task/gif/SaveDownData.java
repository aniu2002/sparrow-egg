package com.sparrow.collect.task.gif;

import com.sparrow.collect.crawler.check.DuplicateUrlCheck;
import com.sparrow.collect.crawler.check.UrlCheck;
import com.sparrow.collect.crawler.check.UrlCheck4Guava;
import com.sparrow.collect.crawler.check.UrlCheck4HashSet;
import com.sparrow.collect.orm.extractor.ResultSetHandler;
import com.sparrow.collect.orm.jdbc.DataSourceConnectionFactory;
import com.sparrow.collect.orm.session.Session;
import com.sparrow.collect.persist.PersistConfig;
import com.sparrow.collect.persist.stor.FileDataSqlStore;
import com.sparrow.collect.utils.FileIOUtil;
import com.sparrow.collect.utils.JsonMapper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Administrator on 2017/7/30 0030.
 */
public class SaveDownData {
    public static void main(String args[]) {
        saveRecord();
        //writeBloomData();
    }

    static void saveRecord() {
        File rootDir = new File("D:\\fanhao\\extract");
        PersistConfig config = JsonMapper.bean(FileIOUtil.readString("classpath:persist-config.json"),
                PersistConfig.class);
        FileDataSqlStore sqlStore = new FileDataSqlStore(config);
        sqlStore.initialize();
        BufferedReader reader = FileIOUtil.getBufferedReader(new File(rootDir, "ad.txt"), FileIOUtil.DEFAULT_ENCODING);

        UrlCheck urlCheck = (rootDir.isDirectory() && rootDir.exists()) ?
                UrlCheck4HashSet.getInstance(rootDir.getPath(), "store_dup.dat")
                : DuplicateUrlCheck.DEFAULT_CHECK;
        sqlStore.setUrlCheck(urlCheck);
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String l = line.trim();
                if (StringUtils.isEmpty(l))
                    continue;
                sqlStore.save(l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(reader);
        }
        sqlStore.close();
        urlCheck.close();
    }

    static final AtomicLong counter = new AtomicLong(0);

    static void writeBloomData() {
        PersistConfig config = JsonMapper.bean(FileIOUtil.readString("classpath:persist-config.json"),
                PersistConfig.class);
        Session session = new Session(new DataSourceConnectionFactory(config.getProps()));

        final UrlCheck urlCheck = UrlCheck4Guava.getInstance("D:\\fanhao\\extract");
        session.query("select icons,gif_url,gif_desc from gif_info", new ResultSetHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                String url = rs.getString("gif_url") + "/" + rs.getString("icons");
                System.out.println(url);
                urlCheck.add(url);
                urlCheck.add(rs.getString("gif_desc"));
                counter.incrementAndGet();
            }
        });
        urlCheck.close();
        System.out.println(counter.get());
    }
}
