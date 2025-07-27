package com.example.designmodel.proxy.staticproxy;

/**
 * 猫
 */
public class Model implements Internet{

    public Model(String password) throws  Exception{
        if (password.equals("123")) {
            System.out.println("密码正确，可以访问");
        }else{
            throw new Exception("密码错误，请重新输入");
        }

    }
    @Override
    public void httpAccess(String url) {
        System.out.println("访问" + url + "成功");
    }

    @Override
    public void setModem(Internet modem) {
        System.out.println("设置代理成功");
    }
}
