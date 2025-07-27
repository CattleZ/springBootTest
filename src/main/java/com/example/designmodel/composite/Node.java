package com.example.designmodel.composite;

public abstract class Node {
    protected String name;

    public Node(String name) {
        this.name = name;
    }

    protected abstract void  add(Node  child);

    protected void tree(int level)
    {
        for (int i = 0;i<level;i++) {
            System.out.print(" ");
        }
        System.out.println(name);
    }

    protected  void tree(){
        this.tree(0);
    }
}
