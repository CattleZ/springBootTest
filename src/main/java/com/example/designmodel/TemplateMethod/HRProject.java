package com.example.designmodel.TemplateMethod;

import java.sql.SQLOutput;
import java.util.Iterator;
import java.util.Random;

public class HRProject extends PM{

    private Random random  = new Random();
    @Override
    public String analyze() {
        System.out.println("开始需求分析");
        return "人力资源需求分析";
    }

    @Override
    public String design(String project) {
        System.out.println("开始需求设计");
        return "设计（" + project + "）";
    }

    @Override
    public String develop(String project) {
        if (project.contains("BUG")) {
            System.out.println("开始修复BUG");
            project = project.replace("BUG", "");
            project = "修复（"+ project + "）";
            if (random.nextBoolean()) {
                project += "BUG";
            }
            return project;
        }
        System.out.println("开始开发");
        if (random.nextBoolean()) {
            project += "BUG";
        }
        return project;
    }

    @Override
    public Boolean test(String project) {
        System.out.println("开始测试");
        if (project.contains("BUG")) {
            System.out.println("项目测试失败");
            return false;
        }
        System.out.println("项目测试成功");
        return true;
    }

    @Override
    public void deploy(String project) {
        System.out.println("开始部署");
        System.out.println("部署成功" + project + "完成");
    }

    public static void main(String[] args) {
        PM pm = new HRProject();
        pm.projectManage();

    }
}
