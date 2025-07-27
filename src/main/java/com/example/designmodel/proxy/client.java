package com.example.designmodel.proxy;

import com.example.designmodel.proxy.dynamicproxy.BlackListFilter;
import com.example.designmodel.proxy.dynamicproxy.Intranet;
import com.example.designmodel.proxy.dynamicproxy.Switch;
import com.example.designmodel.proxy.staticproxy.Internet;
import com.example.designmodel.proxy.staticproxy.Model;
import com.example.designmodel.proxy.staticproxy.RouterProxy;

import java.lang.reflect.Proxy;

public class client {
    public static void main(String[] args) throws Exception {
        // 静态代理
        RouterProxy routerProxy = new RouterProxy();
        routerProxy.httpAccess("游戏");
        routerProxy.httpAccess("电影");
        routerProxy.httpAccess("学习");

        System.out.println("-------------------------" +
                "动态代理" +
                "-------------------------" );
        // 动态代理
        Internet internet = (Internet) Proxy.newProxyInstance(
                com.example.designmodel.proxy.dynamicproxy.RouterProxy.class.getClassLoader(),
                com.example.designmodel.proxy.dynamicproxy.RouterProxy.class.getInterfaces(),
                new BlackListFilter(new com.example.designmodel.proxy.dynamicproxy.RouterProxy())
                );
        internet.httpAccess("游戏");
        internet.httpAccess("电影");
        internet.httpAccess("学习");
        internet.httpAccess("音乐");
        internet.httpAccess("购物");
        internet.setModem(new Model("123"));

        System.out.println("-------------------------" +
                "动态代理交换机黑名单过滤" +
                "-------------------------" );
        Intranet intranet = (Intranet) Proxy.newProxyInstance(
                Switch.class.getClassLoader(),
                Switch.class.getInterfaces(),
                new BlackListFilter(new Switch())
        );

        intranet.fileAccess("游戏");
        intranet.fileAccess("电影");
        intranet.fileAccess("学习");
    }
}
