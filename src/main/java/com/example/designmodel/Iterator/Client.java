package com.example.designmodel.Iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        DrivingRecoeder dr = new DrivingRecoeder();

        for(int i =0;i<12;i++){
            dr.append("Record_" + i);
        }

        List<String> uSerList = new ArrayList<>();

        Iterator<String> iterator = dr.iterator();

        while (iterator.hasNext()) {
            String record = iterator.next();
            System.out.println(record);
            if ("Record_8".equals(record) || "Record_10".equals(record)) {
                uSerList.add(record);
            }
        }
        System.out.println("事故证据——：" + uSerList);

        uSerList.iterator();
    }
}
