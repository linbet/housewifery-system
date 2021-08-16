package com.yongsui.utils;

import junit.framework.TestCase;
import org.junit.Test;

public class RsaUtilsTest extends TestCase {

    private String privateFilePath = "D://auth/id_key_rsa";
    private String publicFilePath = "D://auth/id_key_rsa.pub";


    @Test
    public void testGenerateKey()throws Exception {

        RsaUtils.generateKey(publicFilePath,privateFilePath,"yongsuikeji",2048);
    }
}