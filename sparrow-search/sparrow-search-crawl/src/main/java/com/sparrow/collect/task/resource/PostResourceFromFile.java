package com.sparrow.collect.task.resource;

import com.sparrow.collect.crawler.httpclient.CrawlKit;
import com.sparrow.collect.crawler.httpclient.HttpResp;
import com.sparrow.collect.utils.FileIOUtil;
import com.sparrow.collect.utils.JsonMapper;
import org.codehaus.jackson.JsonNode;

import java.io.File;
import java.util.List;

/**
 * Created by Yzc on 2017/5/5.
 */
public class PostResourceFromFile {
    static CrawlKit kit = CrawlKit.KIT;

    public static void main(String args[]) {
        postResource(new File("d:/sources"));
    }

    static void postResource(File dir) {
        List<String> lines = FileIOUtil.readLines("classpath:resources.txt");
        for (String str : lines)
            postResource(dir, str);
    }

    static void postResource(File dir, String type) {
        File target = new File(dir, type);
        if (!target.exists())
            return;
        String json = FileIOUtil.readFile(target);
        JsonNode node = JsonMapper.jsonNode(json);
        JsonNode entry = node.get("entry");
        if (entry == null)
            return;
        int size = entry.size();

        HttpResp resp;
        for (int i = 0; i < size; i++) {
            JsonNode n = entry.get(i);
            if (n != null && n.has("resource")) {
                JsonNode resNode = n.get("resource");
                String s = resNode.toString();
                resp = kit.post(String.format("http://127.0.0.1:8088/resource/%s", type), s);
                System.out.println(resp.getStatus());
            }
        }
    }

    static void postResource1(File dir, String type) {
        File target = new File(dir, type);
        if (!target.exists())
            return;
        String json = FileIOUtil.readFile(target);
        JsonNode node = JsonMapper.jsonNode(json);
        JsonNode entry = node.get("entry");
        if (entry == null)
            return;
        int size = entry.size();
        HttpResp resp;
        for (int i = 0; i < size; i++) {
            JsonNode n = entry.get(i);
            if (n != null && n.has("fullUrl")) {
                JsonNode urlNode = n.get("fullUrl");
                resp = kit.get(urlNode.getTextValue());
                if (resp.getStatus() != 200)
                    return;
                String s = resp.getHtml();
                resp = kit.post(String.format("http://127.0.0.1:8088/resource/%s", type), s);
                System.out.println(resp.getStatus());
            }
        }
    }

}
