package com.example.designmodel.composite;

import java.util.ArrayList;
import java.util.List;

public class Folder extends Node{
    private List<Node> children = new ArrayList<>();

    public Folder(String name) {
        super(name);
    }

    @Override
    protected void add(Node child) {
        this.children.add(child);
    }

    @Override
    protected void tree(int level) {
        super.tree(level);
        level++;
        for (Node child : children) {
            child.tree(level );
        }
    }

}
