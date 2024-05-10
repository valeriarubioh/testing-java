package org.example.testing1.util;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilTest {
    @Test
    public void testRepeat(){
        Assert.assertEquals("holaholahola",StringUtil.repeat("hola",3));
    }
    @Test(expected = IllegalArgumentException.class)
    public void repeatNegative(){
        StringUtil.repeat("hola",-1);
    }
}