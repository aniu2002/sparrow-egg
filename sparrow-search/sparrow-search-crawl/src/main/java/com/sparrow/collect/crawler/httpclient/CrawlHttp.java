package com.sparrow.collect.crawler.httpclient;

import com.sparrow.collect.crawler.httpclient.proxy.ProxyInfo;
import com.sparrow.collect.crawler.httpclient.proxy.ProxyKit;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA. User: YZC Date: 12-11-8 Time: 上午10:20 To change
 * this template use File | Settings | File Templates.
 */
public class CrawlHttp {
    private static org.slf4j.Logger logger = org.slf4j.LoggerFactory
            .getLogger(CrawlHttp.class);
    public static final Map<String, String> headers;
    HttpClient client;
    boolean hasSetProxy = false;

    static {
        headers = new HashMap<String, String>();
        headers.put("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        headers.put("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
        headers.put("Accept-Encoding", "gzip, deflate");
        // headers.put("X-Requested-With", "XMLHttpRequest");
        headers.put("Upgrade-Insecure-Requests", "1");
        headers.put("Host", "www.alegev.com");
        headers.put("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:54.0) Gecko/20100101 Firefox/54.0");
        // headers.put("Host", "www.tuicool.com");
        // headers.put("Referer", "http://www.tuicool.com/");
    }

    public CrawlHttp() {
        this(false);
    }

    public CrawlHttp(String user, String pwd) {
        this(false, false, user, pwd);
    }

    public CrawlHttp(HttpClient client) {
        this.client = client;
    }

    public CrawlHttp(boolean handleRedirect) {
        this(handleRedirect, false);
    }

    public CrawlHttp(boolean handleRedirect, boolean supportGzip) {
        this(HttpTool.getDefaultClient(handleRedirect, supportGzip));
    }

    public CrawlHttp(int useHttps) {
        DefaultHttpClient client = HttpTool.getMultiThreadClient(true, false,
                useHttps > 0);
        this.client = client;
    }

    public CrawlHttp(boolean handleRedirect, boolean supportGzip, String user,
                     String pwd) {
        DefaultHttpClient client = HttpTool.getDefaultClient(handleRedirect,
                supportGzip);
        AuthScope scope = new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT,
                AuthScope.ANY_REALM, AuthScope.ANY_SCHEME);
        client.getCredentialsProvider().setCredentials(scope,
                new UsernamePasswordCredentials(user, pwd));
        this.client = client;
    }

    public void setParameter(String key, Object value) {
        this.client.getParams().setParameter(key, value);
    }

    public void removeParameter(String key) {
        this.client.getParams().removeParameter(key);
    }

    void setProxyHost(HttpClient _client, HttpReq request) {
        if (request.proxyHost != null) {
            _client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
                    request.proxyHost);
            request.addHeader("X-Forward-For", request.proxyHost.toString());
            this.hasSetProxy = true;
        } else if (this.hasSetProxy) {
            _client.getParams().removeParameter(ConnRoutePNames.DEFAULT_PROXY);
            request.removeHeader("X-Forward-For");
            this.hasSetProxy = false;
        }
    }

    public HttpResp execute(HttpReq request) throws Exception {
        HttpClient _client = this.client;
        if (_client == null)
            _client = HttpTool.getHttpClient(false);
        this.setProxyHost(_client, request);
        if (logger.isInfoEnabled())
            logger.info("$$$$ {} {}  - (charset:{},paras:{})", new Object[]{
                    request.method, request.url, request.charset, request.body});
        HttpRequestBase method = HttpTool.genHttpMethod(request);
        HttpResp resp = HttpTool.doInvokeMethod(_client, method, request.charset);
        if (logger.isInfoEnabled())
            logger.info("Status: {} ", resp.status);
        return resp;
    }

    public void saveToStream(String uri, OutputStream ops) {
        HttpTool.saveStream(uri, this.client, ops);
    }

    public ByteArrayOutputStream downImage(String uri) throws IOException {
        return this.downImage(uri, false);
    }

    public ByteArrayOutputStream downImage(String uri, boolean useProxy) throws IOException {
        HttpClient _client = this.client;
        if (_client == null)
            _client = HttpTool.getDefaultClient(false);
        HttpReq req = new HttpReq(uri);
        if (useProxy) {
            ProxyKit.setProxyHost(req);
            this.setProxyHost(_client, req);
        }
        try {
            URI tUri = URI.create(uri);
            req.addHeader("Referer", tUri.getHost());
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpRequestBase method = HttpTool.genHttpMethod(req);
        try {
            return HttpTool.downImageStream(_client, method);
        } finally {
            method.releaseConnection();
            _client.getConnectionManager().shutdown();
        }
    }

    public boolean downloadStream(HttpReq req, File file) {
        HttpClient _client = this.client;
        if (_client == null)
            _client = HttpTool.getDefaultClient(false);
        this.setProxyHost(_client, req);
        if (logger.isInfoEnabled())
            logger.info("$$$$ {} {}  - (charset:{},paras:{})", new Object[]{
                    req.method, req.url, req.charset, req.body});
        try {
            URI tUri = URI.create(req.url);
            req.addHeader("Referer", "http://" + tUri.getHost());
            req.addHeader("Host", tUri.getHost());
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpRequestBase method = HttpTool.genHttpMethod(req);
        boolean fg = false;
        try {
            fg = HttpTool.downloadStream(_client, method, file);
        } finally {
            // method.releaseConnection();
            _client.getConnectionManager().shutdown();
        }
        if (logger.isInfoEnabled())
            logger.info("Download stream success:{} , url = {} , dest = {}",
                    new Object[]{fg, req.url, file.getPath()});
        return fg;
    }
}
