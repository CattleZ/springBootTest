package com.example.designmodel.Mediator;

public class User {
    private String name;
    private ChatRoom chatRoom;

    public User(String name)
    {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void login(ChatRoom chatRoom){
        this.chatRoom = chatRoom;
        this.chatRoom.register( this);
    }

    public void talk(String msg){
        chatRoom.sendMessage(this,msg);
    }

    public void listen(User from,String msg){
        System.out.println("【"+this.name+"的对话框");
        System.out.println(from.getName()+": 说"+msg);
    }
}
