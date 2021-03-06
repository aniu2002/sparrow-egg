package com.sparrow.collect.crawler.httpclient;

import com.sparrow.collect.crawler.httpclient.proxy.ProxyInfo;
import com.sparrow.collect.crawler.httpclient.proxy.ProxyKit;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.conn.ConnectTimeoutException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA. User: YZC Date: 12-11-8 Time: 下午12:33 To change
 * this template use File | Settings | File Templates.
 */
public class CrawlKit {
    private static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CrawlKit.class);

    public static final CrawlKit KIT = new CrawlKit();

    private CrawlKit() {

    }

    public HttpResp get3(String url, String param, Map<String, String> headers,
                         String encode, boolean useProxy, int retry) {
        return this.execute(url, "GET", param, headers, encode, useProxy, retry);
    }

    public HttpResp getHtml(String url, Map<String, String> params,
                            Map<String, String> headers, String encode, boolean useProxy,
                            int retry) {
        return this.executeExt(url, "GET", params, headers, encode, useProxy,
                retry);
    }

    public HttpResp getHtml(String url, Map<String, String> params,
                            Map<String, String> headers, String encode, boolean useProxy) {
        return this.executeExt(url, "GET", params, headers, encode, useProxy, 1);
    }

    public HttpResp post3(String url, String param,
                          Map<String, String> headers, String encode, boolean useProxy,
                          int retry) {
        return this.execute(url, "POST", param, headers, encode, useProxy,
                retry);
    }

    public ByteArrayOutputStream image3(String imageUrl, boolean useProxy,
                                        int retry) {
        if (retry < 1)
            return null;
        CrawlHttp http = new CrawlHttp();
        ByteArrayOutputStream opstream;
        try {
            opstream = http.downImage(imageUrl);
        } catch (IOException e) {
            opstream = image3(imageUrl, useProxy, retry - 5);
        }
        return opstream;
    }

    public boolean saveStream(String imageUrl, File file, boolean useProxy,
                              int retry) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "image/png,image/*;q=0.8,*/*;q=0.5");
        headers.put("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:22.0) Gecko/20100101 Firefox/22.0");

        CrawlHttp http = new CrawlHttp(true, true);
        HttpReq request = new HttpReq(imageUrl, headers);
        return this.saveStream(http, request, file, useProxy, retry);
    }

    public boolean saveStream(String url, File file) {
        return this.saveStream(url, file, null);
    }

    public boolean saveStream(String imageUrl, File file, Map<String, String> headers, boolean useProxy) {
        CrawlHttp http = new CrawlHttp(true, true);
        HttpReq request = new HttpReq(imageUrl, headers);
        boolean proxy = useProxy;
        if (imageUrl.indexOf("sinaimg.cn") != -1)
            proxy = false;
        if (proxy)
            ProxyKit.setProxyHost(request);
        boolean fg = http.downloadStream(request, file);
        if (!fg)
            file.delete();
        return fg;
    }

    public boolean saveStream(String imageUrl, File file, Map<String, String> headers) {
        CrawlHttp http = new CrawlHttp(true, true);
        HttpReq request = new HttpReq(imageUrl, headers);
        boolean fg = http.downloadStream(request, file);
        return fg;
    }


    public boolean downloadFile(String url, File file,
                                Map<String, String> headers) {
        CrawlHttp http = new CrawlHttp(true, true);
        HttpReq request = new HttpReq(url, headers);
        return this.saveStream(http, request, file, false, 3);
    }

    public boolean saveStream(CrawlHttp http, HttpReq request, File file,
                              boolean useProxy, int retry) {
        if (retry < 1) {
            if (file.exists()) {
                file.delete();
            }
            return false;
        }
        boolean useProxyTmp = useProxy;
        if (retry % 2 == 0)
            useProxyTmp = false;
        if (useProxyTmp) {
            ProxyInfo proxyInfo = ProxyKit.getProxy(proxyRuleName);
            if (proxyInfo != null && StringUtils.isNotBlank(proxyInfo.getIp())) {
                HttpHost hcProxyHost = new HttpHost(proxyInfo.getIp(),
                        proxyInfo.getPort());
                request.setProxyHost(hcProxyHost);
            }
        } else if (useProxy) {
            request.setProxyHost(null);
        }
        boolean fg = http.downloadStream(request, file);
        if (!fg)
            return this.saveStream(http, request, file, useProxy, retry - 1);
        else
            return true;
    }

    private String proxyRuleName = "s-baidu";

    public HttpResp executeExt(String url, String method,
                               Map<String, String> params, Map<String, String> headers,
                               String encode, boolean useProxy, int retry) {
        CrawlHttp http = new CrawlHttp(true, true);
        HttpReq request = new HttpReq(url, method, encode, headers);
        // set request parameters ,while post method will invoke setBody method
        // set formed submit
        request.setParameter(params);
        HttpResp resp;
        try {
            resp = doExecute(http, request, useProxy, retry);
        } catch (Exception e) {
            logger.error("Http access error {}", e.getMessage());
            resp = new HttpResp(0, null, e.getMessage());
        }
        return resp;
    }

    public HttpResp execute(String url, String method, String param,
                            Map<String, String> headers, String encode, boolean useProxy,
                            int retry) {
        CrawlHttp http = new CrawlHttp();
        HttpReq request = new HttpReq(url, method, encode, headers);
        // set request parameters ,while post method will invoke setBody method
        // set formed submit
        request.setParaStr(param);
        HttpResp resp = null;
        try {
            resp = doExecute(http, request, useProxy, retry);
            if (resp.status == 200) {
                return resp;
            }
        } catch (Exception e) {
            logger.error("{}", e.getMessage());
            if (resp == null)
                resp = new HttpResp();
            resp.status = 0;
            resp.html = null;
        }
        return resp;
    }

    private HttpResp doExecute(CrawlHttp http, HttpReq request,
                               boolean useProxy, int ret) throws Exception {
        HttpResp resp;
        int count = ret;
        int retry = ret + 1;
        boolean needRetry = true;

        do {
            try {
                boolean useProxyTmp = useProxy;
                if (retry > 0 && retry % 2 == 0)
                    useProxyTmp = false;
                if (useProxyTmp) {
                    ProxyKit.setProxyHost(request, proxyRuleName);
                } else if (useProxy) {
                    request.setProxyHost(null);
                }
                resp = http.execute(request);
                if (resp.status == 200) {
                    needRetry = false;
                    return resp;
                } else {
                    int status = resp.getStatus();
                    if (retry > 0)
                        logger.error("Retry : {} {}", status, request.url);
                    else
                        logger.error("Resp : {} {}", status, request.url);
                    retry -= 1;
                }
            } catch (ConnectTimeoutException e) {
                if (retry > 0)
                    logger.error("Timeout Retry:" + request.url);
                else
                    logger.error("Timeout:" + request.url);
                retry -= 1;
                resp = new HttpResp(-1, null, e.getMessage());
            } catch (IOException e) {
                if (retry > 0)
                    logger.error("IO Failed Retry:" + request.url + ", - " + e.getMessage());
                else
                    logger.error("IO Failed:" + request.url);
                retry -= 1;
                resp = new HttpResp(-1, null, e.getMessage());
            }
        } while (needRetry && retry > 0);

        if (retry == 1)
            return resp;
        if (count > 1 && resp == null)
            throw new Exception("Failure: retry " + count + " times, URL:" + request.url);
        else
            return resp;
    }

    public static void main(String args[]) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("User-Agent",
                "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)");
        Map<String, String> paras = new HashMap<String, String>();
        paras.put("b", "(12907216.96,4828467.72;13009104.96,4848115.72)");
        paras.put("biz", "1");
        paras.put("c", "131");
        paras.put("ie", "utf-8");
        paras.put("l", "12");
        paras.put("newmap", "1");
        paras.put("qt", "s");
        paras.put("reqflag", "pcmap");
        paras.put("sefrom", "1");
        paras.put("t", String.valueOf(System.currentTimeMillis()));
        paras.put("tn", "B_NORMAL_MAP");
        paras.put("wd", "餐饮");
        HttpResp res = new CrawlKit().getHtml(
                "http://detail.tmall.com/item.htm?id=15261774826", paras,
                headers, "utf-8", false, 1);
        System.out.println(res.getHtml());
        // Document doc = Jsoup.parse(res.getHtml());
        // JSONObject job = null;
        // try {
        // job = new JSONObject(res.getHtml());
        // job = (JSONObject) job.get("result");
        // if (job != null)
        // System.out.println(job.get("total"));
        // } catch (JSONException e) {
        // e.printStackTrace();
        // }
        System.out.println("INFO -------------XXXXXXXXXXXXXXXXXXXXX--");
    }
}
