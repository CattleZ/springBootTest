package com.example.designmodel.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlackListFilter implements InvocationHandler {
    private Object origin;

    private List<String> blackList = Arrays.asList("电影", "游戏", "小说", "购物");

    public BlackListFilter(Object object) {
        this.origin = object;
        System.out.println("黑名单过滤器初始化完成......");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始进行黑名单过滤......"
                + "\n被代理对象：" + origin.getClass().getName()
                + "\n被调用方法：" + method.getName()
                + "\n参数列表：" + Arrays.toString(args));
        String arg = args[0].toString();
        for (String s : blackList) {
            if (arg.contains(s)) {
                System.out.println("该网址被加入黑名单：" + arg);
                return null;
            }
        }
        System.out.println("校验通过，转向实际的业务......");
        return method.invoke(origin, args);
    }
}
