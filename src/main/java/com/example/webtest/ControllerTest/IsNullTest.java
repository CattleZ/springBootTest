package com.example.webtest.ControllerTest;

import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


/**
 * java中避免空值的技巧
 * @Author gorge
 * @Version 1.0
 * @Date 2022/9/12 12:05
 **/
public class IsNullTest {
    /**
     * 常见的出现空值的五种情况
     * 1. 参数类型为对象，Integer 等类型。入参数为空
     * 2. 多层对象调用
     * 3. 远程方法返回的参数为空，在使用的时候没有进行判断
     * 4. ConcurrentHashMap的容器不支持key和value的值为空
     * 5. String 类型判断是否为空
     */
    public void errorExample(String a,Integer b ,Person p){
        System.out.println(a.equals("1"));
        System.out.println(b+1);
        System.out.println(p.getStudent().getAge());
    }
    //  使用Optional.ofNullable来构造Option ，使用orElse() 替换成默认值 。常见的几种解决
    // 方式就不在测试了。比如if 判空， "xx".equals()
    public void rightExample(String a,Integer b ,Person p){
        System.out.println(Optional.ofNullable(a).orElse("").equals("1"));
        System.out.println(Optional.ofNullable(b).orElse(0)+1);
        System.out.println(Optional.ofNullable(p).map(Person::getStudent).map(Student::getAge));
        List<Integer> x = new ArrayList<>();
        boolean add = x.add(1);
        for (int i = 0, xSize = x.size(); i < xSize; i++) {
            Integer y = x.get(i);
            y += 1;
        }
    }
    @Test
    public void test(){
        Person p = new Person();
        try{
            errorExample(null,null,p);
        }catch (Exception e){
            throw new IllegalArgumentException(e);
        }

        rightExample(null,null,p);
    }
    class Person{
        Student student;
        public Student getStudent() {
            return student;
        }
    }

    class Student{
        int age;
        public int getAge() {
            return age;
        }
    }
    /**
     *  sql中的null值
     *  1. mysql 中的sum函数没有统计到任何记录时，会返回null，而不是0 。可以使用ifnull 函数转换为0
     *  2. count函数时不统计null值的
     *  3. mysql中的=、< 、> 这样的算数比操作符比较NULL的结果总是NULL。需要使用IS NULL、 IS NOT NULL或者 ISNULL()函数来比较
     */

    /**
     * java8函数方式接口定义
     */
    public void interfaceTest(){
        // Predicate 接口输入一个参数，返回boolean值
        Predicate<Integer> positiveNumber = i->i>0;
        Predicate<Integer> evenNumber = i->i%2==0;
        assert(positiveNumber.and(evenNumber).test(2));
        // Consumer 消费者窗口，通过andThen方法组合调用两个Consumer 输出两行abcdefg
        Consumer<String> print = System.out::println;
        print.andThen(print).accept("abcdefg");
        // Function 接口是一个输入数据接口。先把字符串转换为大写，在实现字符串拼接
        Function<String,String> upp = String::toUpperCase;
        Function<String,String> duplic = s -> s.concat(s);
        System.out.println(upp.andThen(duplic).apply("test"));

    }
    /**
     * java8 中stream 与 list 以及 OPtional简化操作事例
     */
    @Test
    public void java8lamTest(){
        // 实现将一个整数列表转换为Point2D列表，找出y轴数值大于1的对象，
        // 计算每个点到原点的距离。累加所有计算出的距离，并计算平均值
        double res = Arrays.asList(1,2,3,4,5,6,7,8)
                .stream()
                // 映射到point2d上
                .map(i->new Point2D.Double((double)i%3,(double)i/3))
                // 过滤y大于1的数值
                .filter(i->i.getY()>1)
                // 计算每个点到原点的距离
                .mapToDouble(ite -> ite.distance(0,0))
                // 计算累加平均值
                .average()
                .orElse(0);
        System.out.println(res);
        System.out.println("-----------");
        // Option类使用测试
        // 将值进行Option包装，如果为null,抛出异常
        System.out.println(Optional.of(1).get());
        // 如果传入的值为null，则是使用其他的符号代替
        System.out.println(Optional.ofNullable(null).orElse("A"));
        // 是否有值
        System.out.println(OptionalDouble.of(2.0).isPresent());
        System.out.println(Optional.of(1).map(Math::incrementExact).get());
        System.out.println(Optional.of(1).filter(i -> i%2 ==0).orElse(null));
        Optional.empty().orElseThrow(IllegalArgumentException::new);
        System.out.println("-----------");
        // HashMap的增强
        HashMap<String,String> cache = new HashMap<String,String>();
        cache.computeIfAbsent("4",i->i="45");
        cache.putIfAbsent("5","46");
        System.out.println(cache.keySet());
    }

}
