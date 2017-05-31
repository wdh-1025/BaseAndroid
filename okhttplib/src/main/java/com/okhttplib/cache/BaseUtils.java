package com.okhttplib.cache;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

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
 * 　　　　┃　　　┃永无BUG！
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━━━━━━
 * Created by  Administrator  on 2017/4/11.
 * Email:924686754@qq.com
 */
public class BaseUtils {
    /**
     * BASE解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

}
