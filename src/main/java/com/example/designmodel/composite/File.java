package com.example.designmodel.composite;

public class File extends Node{

    public File(String name) {
        super(name);
    }

    @Override
    protected void add(Node child) {
        System.out.println("文件不能添加子节点" + child.name);
    }

    @Override
    protected void tree(int level) {
        super.tree(level);
    }
}
