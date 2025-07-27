package com.example.designmodel.Adapter;

public class TV implements DualPin{

    @Override
    public void electrify(int l, int r) {
        System.out.println("使用双脚插线" +
                "，左脚电压：" + l +
                "，右脚电压：" + r +
                "，电视机开始工作");
    }
}
