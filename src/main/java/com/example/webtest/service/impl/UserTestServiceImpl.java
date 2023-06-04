package com.example.webtest.service.impl;

import com.example.webtest.entity.UserTest;
import com.example.webtest.mapper.UserTestMapper;
import com.example.webtest.service.UserTestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Gorge
 * @since 2023-06-04
 */
@Service
public class UserTestServiceImpl extends ServiceImpl<UserTestMapper, UserTest> implements UserTestService {

}
