package com.okhttplib.cache;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑
 * 　　　　┃　　　┃代码无BUG！
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━━━━━━
 * Created by root on 2017/4/3  10:36.
 */
public class BasicCache {

    private DiskLruCache diskCache;
    private String fileName = "cache";
    private String mFilePath;
    private long mMaxDiskSize;

    public BasicCache(String filePath, long maxDiskSize) {
        try {
            this.mFilePath = filePath;
            this.mMaxDiskSize = maxDiskSize;
            diskCache = DiskLruCache.open(new File(filePath, fileName), 1, 1, maxDiskSize);
        } catch (IOException exc) {
            diskCache = null;
        }
    }

    /**
     * url转MD5
     *
     * @param url
     * @return
     */
    private String urlToKey(HttpUrl url) {
        return MD5.getMD5(url.toString());
    }

    private String urlToKey(String url) {
        return MD5.getMD5(url.toString());
    }

    /**
     * 添加缓存数据
     *
     * @param response
     */
     public void addCache(String date, Response response) {
         if (diskCache == null) {
            return;
        }
        Buffer buffer = new Buffer();
        try {
            //对存储在本地的数据进行加密
            date = BaseUtils.encryptBASE(date.getBytes());

            buffer.write(date.getBytes());
            byte[] rawResponse = buffer.readByteArray();
            DiskLruCache.Editor editor = diskCache.edit(urlToKey(response.request().url()));
            if (editor == null) {
                return;
            }
            editor.set(0, new String(rawResponse, Charset.defaultCharset()));
            editor.commit();
            buffer.clone();
        } catch (IOException exc) {
            buffer.clone();
        } catch (Exception e) {
            buffer.clone();
        }
    }

    /**
     * 获取缓存数据
     *
     * @param request
     * @return
     */
    public ResponseBody getCache(Request request) {
        if (diskCache == null) {
            return null;
        }
        String cacheKey = urlToKey(request.url());
        try {
            DiskLruCache.Snapshot cacheSnapshot = diskCache.get(cacheKey);
            if (cacheSnapshot != null) {
                //对取出本地的数据进行解密
                byte[] data = null;
                data = new String(BaseUtils.decryptBASE(cacheSnapshot.getString(0))).getBytes();
                return ResponseBody.create(null, data);
            } else {
                return null;
            }
        } catch (IOException exc) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 删除全部文件
     */
    public void deleteAllCache() {
        if (diskCache == null) {
            return;
        }
        try {
            diskCache.delete();
            diskCache = DiskLruCache.open(new File(mFilePath, fileName), 1, 1, mMaxDiskSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除缓存
     *
     * @param url
     */
    public void deleteCache(String url) {
        if (diskCache == null) {
            return;
        }
        try {
            String cacheKey = urlToKey(url);
            DiskLruCache.Snapshot cacheSnapshot = diskCache.get(cacheKey);
            if (cacheSnapshot != null) {
                diskCache.remove(cacheKey);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}