package com.example.webtest.ControllerTest;

import org.junit.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @Author gorge
 * @Version 1.0
 * @Date 2022/9/7 22:24
 **/
@RestController
@RequestMapping("/normal")
public class NormalErrorTest {
    /**
     * 数值计算需要注意的问题
     */
    @GetMapping("/doubleNum")
    public void doubleNumErrorTest() {
        // 0.1 无法精确表达，浮点数计算精度丢失根本原因
        System.out.println(0.1 + 0.2);
        System.out.println(1.0 - 0.8);
        System.out.println(4.015 * 100);
        System.out.println(123.3 / 100);
        // 0.1 的二进制表达为 0.0 0011 0011 0011… （0011 无限循环)
        System.out.println(0.1);
        System.out.println(0.2);
        System.out.println("----");
        //BigDecimal 类型 (虽然更精确了，但还不是我们想要的结果)
        System.out.println(new BigDecimal(0.1).add(new BigDecimal(0.2)));
        System.out.println(new BigDecimal(1.0).subtract(new BigDecimal(0.8)));
        System.out.println(new BigDecimal(4.015).multiply(new BigDecimal(100)));
        System.out.println(new BigDecimal(123.3).divide(new BigDecimal(100)));
        System.out.println("----");
        // BigDecimal 类型 字符串传递参数
        System.out.println(new BigDecimal("0.1").add(new BigDecimal("0.2")));
        System.out.println(new BigDecimal("1.0").subtract(new BigDecimal("0.8")));
        System.out.println(new BigDecimal("123.3").divide(new BigDecimal("100")));
        System.out.println("----");
        // 可以发现，这个结果 401.500 后面多了两个00
        System.out.println(new BigDecimal("4.015").multiply(new BigDecimal("100")));
        // double 类型转换为 字符串类型
        System.out.println(Double.toString(4.015));
        System.out.println(new BigDecimal(Double.toString(4.015)));
        // new BigDecimal(Double.toString(100)) 得到的 BigDecimal 的 scale=1、precision=4；
        // 而 new BigDecimal(“100”) 得到的 BigDecimal 的 scale=0、precision=3
        System.out.println(new BigDecimal(Double.toString(4.015)).multiply(new BigDecimal("100")));
        System.out.println(new BigDecimal("4.015").multiply(new BigDecimal(Double.toString(100))));

    }

    public void floatFormat(){
        System.out.println("----");
        //  double 和 float 的 3.35 其实相当于 3.350xxx 和 3.349xxx
        double num1 = 3.35;
        float num2 = 3.35f;
        System.out.println(String.format("%.1f", num1));//四舍五入
        System.out.println(String.format("%.1f", num2));
        System.out.println("----");
    }

    // 使用DecimalFormat进行浮点数的字符串格式化
    @Test
    public void decimalFormatTest(){
        double num1 = 3.35;
        float num2 = 3.35f;
        // 浮点数格式化 （也会存在精度丢失的问题）
        DecimalFormat format = new DecimalFormat("#.##");
        // 浮点数向下舍入
        format.setRoundingMode(RoundingMode.DOWN);
        System.out.println(format.format(num1));
        // 浮点数向下舍入
        format.setRoundingMode(RoundingMode.UP);
        System.out.println(format.format(num2));
    }
    // 使用BigDecimal进行字符串的格式化,浮点数的格式化必须使用这个类

    public void bigDecimalTest(){
        BigDecimal num1 = new BigDecimal("3.35");
        BigDecimal num2= num1.setScale(1,BigDecimal.ROUND_DOWN);
        System.out.println(num2);
        BigDecimal num3= num1.setScale(1,BigDecimal.ROUND_HALF_UP);
        System.out.println(num3);

        // 判断一下是不是相等(结果是false 因为equals判断的是value以及scale ,1的scale是0，1.0的scale是1 )
        // 因此，在使用hashMap或者hashCode的时候就需要注意，可以使用stripTrailingZeros方法去掉尾部的零。
        // 确保value相同，scale也相同。或者改用hashSet
        System.out.println(new BigDecimal("1").equals(new BigDecimal("1.0")));
        // 如果只是希望比较一下value值的话，可以使用compareTo的方法
        System.out.println(new BigDecimal("1").compareTo(new BigDecimal("1.0")));
    }
    // 数值运算需要注意溢出的问题，并且溢出后不会报异常。可以BigInteger对象结合使用Math.xxExact方进行计算。或者使用Math.addExact
    @Test
    public void exactTest(){
        long x = Long.MAX_VALUE;
        // System.out.println(Math.addExact(1,x));
        System.out.println(x+1);
        BigInteger bi = new BigInteger(String.valueOf(Long.MAX_VALUE));
        System.out.println(bi.add(new BigInteger("1")).longValueExact());
    }



}
