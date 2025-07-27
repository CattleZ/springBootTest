package com.example.designmodel.proxy.dynamicproxy;


public class Switch implements Intranet {
    @Override
    public void fileAccess(String url) {
        System.out.println("访问内网" + url + "文件");
    }
}
