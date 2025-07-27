package com.example.designmodel.proxy.staticproxy;

public interface Internet {
   void httpAccess(String url);

   void setModem(Internet modem) throws Exception;
}
