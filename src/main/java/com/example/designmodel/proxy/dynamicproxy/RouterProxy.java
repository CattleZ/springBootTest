package com.example.designmodel.proxy.dynamicproxy;

import com.example.designmodel.proxy.staticproxy.Internet;
import com.example.designmodel.proxy.staticproxy.Model;

public class RouterProxy implements Internet {

    private Internet modem;

    public RouterProxy() throws Exception {
        modem = new Model("123");
    }
    @Override
    public void httpAccess(String url) {
        modem.httpAccess(url);
    }

    @Override
    public void setModem(Internet modem) throws Exception {
        System.out.println("设置新的调制解调器");
    }
}
