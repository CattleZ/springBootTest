package com.example.designmodel.visitor;

public interface Acceptable {
    /**
     * 接受访问者
     *
     * @param visitor 访问者
     */
    void accept(Visitor visitor);
}
