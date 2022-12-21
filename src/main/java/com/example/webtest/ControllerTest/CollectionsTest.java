package com.example.webtest.ControllerTest;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**集合类操作常见的坑
 * @Author gorge
 * @Version 1.0
 * @Date 2022/9/12 09:23
 **/
public class CollectionsTest {
    /**
     * Arrays.asList
     */
    @Test
    public void asListTest(){
        /**
         * 数组转换为list类型
         */
        int [] arr  = {1,2,3,4,5};
        // 调用asList只能生成一个元素，该元素是一个int数组
        List res = Arrays.asList(arr);
        System.out.println(res.size());
        // 直接遍历该集合只会有一个参数,因此，不能直接使用asList方法转换。
        // asList没有实现对list集合的增加删除操作，并且共享了原始数组，即原来的数组数据更改后，list集合的数据也会更改
        // 可以通过 List res = new ArrayList(Arrays.asList(arr)) 实现;
        for(Object i : res){
            System.out.print(i+" ");
            System.out.println(i.getClass());
        }
        System.out.println();
        // java8以上版本可以使用流式处理法
        List<Integer> list2 = Arrays.stream(arr).boxed().collect(Collectors.toList());
        System.out.println(list2.size());
        // 通过流处理的方式，我们可以获取到正确的结果
        for(Object i : list2){
            System.out.print(i+" ");
            System.out.println(i.getClass());
        }
        // List.subList进行切片操作会导致OOM
        // 原因是List.subList 返回的不是一个普通的List，是原始list的视图。会跟原始list互相影响
        List<Integer> origin = IntStream.rangeClosed(1,10).boxed().collect(Collectors.toList());
        List<Integer> target1 = origin.subList(0,3);
        System.out.println(target1);
        origin.remove(4);
        // 这里origin 会正常输出
        System.out.println(origin);
        // 此时，会出现ConcurrentModificationException的异常。原因是，在进行subList的时候会传
        // 原始数组的大小，如果后期修改了size会导致判等出问题
        System.out.println(target1);

        // 为了避免上述问题，可以采用java8的skip结合limit进行使用。或者新初始化一个list对象
        List<Integer> subList = origin.stream().skip(4).limit(3).collect(Collectors.toList());
        System.out.println(subList);

    }
}
