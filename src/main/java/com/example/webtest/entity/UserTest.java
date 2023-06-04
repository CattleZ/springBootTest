package com.example.webtest.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Gorge
 * @since 2023-06-04
 */
@Data
@Accessors(chain = true)
@TableName("USER_TEST")
public class UserTest {

    private static final long serialVersionUID = 1L;

    @TableId("USER_ID")
    private Long userId;

    @TableField("USER_NAME")
    private String userName;

    @TableField("PASS_WORD")
    private String passWord;


}
