package com.example.designmodel.composite;

public class Client {
    public static void main(String[] args) {
        Folder folder = new Folder("文件夹1");
        folder.add(new File("文件1"));
        Folder folder1 = new Folder("文件夹1");
        folder1.add(new File("文件2"));
        folder.add(folder1);
        folder.add(new File("文件3"));
        folder.tree();
    }
}
