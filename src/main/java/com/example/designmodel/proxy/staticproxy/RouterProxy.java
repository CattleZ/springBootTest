package com.example.designmodel.proxy.staticproxy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RouterProxy implements  Internet{

    private Internet model;
    List<String> aband = Arrays.asList("游戏","电影", "小说", "购物");

    public RouterProxy() throws Exception {
        this.model = new Model("123");
    }

    @Override
    public void httpAccess(String url) {
        if(aband.contains(url)){
            System.out.println("访问被拒绝");
        }else{
            model.httpAccess(url);
        }
    }

    @Override
    public void setModem(Internet modem) throws Exception {
        System.out.println("设置代理");
    }
}
