package com.example.designmodel.Mediator;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
    private String name;

    public ChatRoom(String name)
    {
        this.name = name;
    }
    List<User> users = new ArrayList<>();

    public void register(User user)
    {
        users.add(user);
        System.out.println("用户" + user.getName() + "加入聊天室");
    }

    public void sendMessage(User from, String message)
    {
        for (User user : users)
        {
            if (user != from)
            {
                user.listen(from, message);
            }
        }
    }
}
