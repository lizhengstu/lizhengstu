package com.test.xy.controller;

import com.test.xy.service.TestDemo;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestController {
    private TestDemo testDemo=new TestDemo();

    private String token ;


    @Test(groups ="test1" ,priority = 0)
    public void login() throws IOException {
        String token = testDemo.loginPost();
        Assert.assertNotNull(token,"token获取失败");
        this.token=token;
    }
    @Test(groups ="test1" ,priority = 1)
    public void productInfo() throws IOException {
        testDemo.productInfo(token);
    }
}
