package com.example.webtest.controller;


import com.example.webtest.BaseController;
import com.example.webtest.entity.UserTest;
import com.example.webtest.service.UserTestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Gorge
 * @since 2023-06-04
 */
@RestController
@RequestMapping("/user-test")
public class UserTestController extends BaseController {
    @Resource
    UserTestService userTestService;
    @GetMapping("getAll")
    List<UserTest> getAll(){
       List<UserTest> res =  userTestService.list();
       return res;
    }

}
