package com.example.designmodel.Memento;

import java.util.ArrayList;
import java.util.List;

public class Editor {

    private Doc doc;
    private List<History> historyRecords;
    private int historyPosition = -1;

    public Editor(Doc doc)
    {
        System.out.println("打开文档");
        this.doc = doc;
        this.historyRecords = new ArrayList<>();
        buckup();
        show();
    }
    private void buckup(){
        historyRecords.add(doc.createHistory());
        historyPosition++;
    }

    private void show(){
        System.out.println(doc.getBody());
        System.out.println("显示内容结束>>>");
    }

    public void append(String txt){
        System.out.println("添加内容：" + txt);
        doc.setBody(doc.getBody() + txt);
        buckup();
        show();
    }

    public void save(){
        System.out.println("保存文档");
    }

    public void delete(){
        System.out.println("删除文档");
        doc.setBody("");
        buckup();
        show();
    }

    public void undo(){
        System.out.println("撤销文档");
        if (historyPosition == 0) {
            return;
        }
        historyPosition--;
        History history = historyRecords.get(historyPosition);
        doc.restoreHistory(history);
        show();
    }
}
