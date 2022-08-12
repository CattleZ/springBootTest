package com.example.webtest.ControllerTest;

import org.springframework.util.Assert;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;


@RestController
@RequestMapping("/threadLocal")
public class ThreadLocalSafe {
    /**
     * ThreadLocal使用事例
     */
    private static final ThreadLocal<Integer> currentUser = ThreadLocal.withInitial(()->null);
    // 错误方案设置，会出现线程重用，导致后来的用户获取到之前请求遗留的值。这种现象多请求几次就会出现（在tomcat这种服务器上跑的
    // 代码本身就处于一个多线程的环境）。
    @GetMapping("wrong")
    public Map Wrong(@RequestParam("userId") Integer userId){
        // 设置用户信息之前先查询一次ThreadLocal 中的用户信息
        String beforeName = Thread.currentThread().getName()+":" + currentUser.get();
        // 设置用户信息到ThreadLocal
        currentUser.set(userId);
        // 设置用户信息之后在查询一次ThreadLocal 中的用户信息
        String after = Thread.currentThread().getName()+":"+currentUser.get();
        // 汇总输出两次结果
        Map result = new HashMap();
        result.put("before",beforeName);
        result.put("after",after);
        return result;
    }

    // 正确方案，重复数据是应为线程重用导致的，因此，就需要特别注意，在代码运行完成之后就要显式的清空设置的数据。
    // 在Threadlocal中显式的清除数据
    @GetMapping("/right")
    public Map Right(@RequestParam("userId") Integer userId){
        Map result= new HashMap();
        // 设置用户之前先查询一次ThreadLocal中的数据
        String beforeName = Thread.currentThread().getName()+":"+currentUser.get();
        // 设置用户信息到ThreadLocal中
        currentUser.set(userId);
        try{
            String after = Thread.currentThread().getName()+":"+currentUser.get();
            result.put("before",beforeName);
            result.put("after", after);
        }finally {
            currentUser.remove();
        }
        return result;
    }

    /**
     * ConcurrentHashMap 使用事例
     * ConcurrentHashMap 可以确保多个线程之间在执行时互不影响，但是无法保证多个线程从ConcurrentHashMap中获取数值时的互斥性
     * 诸如putAll这样的聚合方法，也不能确保原子性，在putAll的过程中去获取数据可能会获取到部分数据
     */
    // 错误使用，多线程往ConcurrentHashMap中添加值
            // 线程个数
    private static int THREAD_COUNT = 10;
    // 总元素数量
    private static int TIME_COUNT = 1000;
    // 帮助方法，用来获得一个指定元素数量模拟数据的ConcurrentHashMap
    private ConcurrentHashMap<String , Long> getData(int count){
        return LongStream.rangeClosed(1, count)
                .boxed()
                .collect(Collectors.toConcurrentMap(i-> UUID.randomUUID().toString(),
                        Function.identity(),
                        (o1,o2) -> o1, ConcurrentHashMap::new));
    }

    // 错误方法
    @GetMapping("/wrong1")
    public String wrong1 () {
        ConcurrentHashMap<String, Long> concurrentHashMap = getData(TIME_COUNT-100);
        // 初始化900个元素
        System.out.println("init size:"+concurrentHashMap.size());
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        // 使用线程池并发处理逻辑
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
            // 查询还需要补充多少个元素
            int gap = TIME_COUNT - concurrentHashMap.size();
            System.out.println("gap size:"+gap);
            // 补充元素
            concurrentHashMap.putAll(getData(gap));
        }));
        //等待所有任务完成
        forkJoinPool.shutdown();
        try {
            forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 输出最后的元素个数
        System.out.println("finish size:"+concurrentHashMap.size());
        return "ok";
    }

    // 修正方法
    // 对ConcurrentHash 的获取大小操作进行添加锁，但是这样操作的后果就是没有使用其新的特性 ，无法发挥出其威力。
    @GetMapping("/right1")
    public String right1(){
        ConcurrentHashMap<String, Long> concurrentHashMap = getData(TIME_COUNT-100);
        // 初始化900个元素
        System.out.println("init size:"+concurrentHashMap.size());
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        // 使用线程池并发处理逻辑
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
            synchronized (concurrentHashMap) {
                // 查询还需要补充多少个元素
                int gap = TIME_COUNT - concurrentHashMap.size();
                System.out.println("gap size:"+gap);
                // 补充元素
                concurrentHashMap.putAll(getData(gap));
            }
        }));
        //等待所有任务完成
        forkJoinPool.shutdown();
        try {
            forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 输出最后的元素个数
        System.out.println("finish size:"+concurrentHashMap.size());
        return "OK";
    }

    /**
     * ConcurrentHashMap提供了一些原子性的简单复合逻辑。
     * 场景： 使用ConcurrentHashMap 来统计key出现次数的场景，
     *       使用ConcurrentHashMap来统计，Key的范围是10
     *       使用最多10个并发，循环操作1000万次，每次操作都累加随机的Key
     *       如果key不存在的话，首次设置值为1
     */
    // 循环次数
    private static int LOOP_COUNT = 10000000;
    //线程数量
    private static int THREAD_COUNT2 = 10;
    // 元素数量
    private static int TIME_COUNT2 = 10;
    private Map<String, Long> normaluse() throws Exception {
        ConcurrentHashMap<String, Long> freqs = new ConcurrentHashMap<>(TIME_COUNT2);
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT2);
        forkJoinPool.execute(()-> IntStream.rangeClosed(1, LOOP_COUNT).parallel().forEach(
                i -> {
                    // 获得一个随机的key
                    String key = "item:"+ ThreadLocalRandom.current().nextInt(TIME_COUNT2);
                    synchronized (freqs) {
                        if (freqs.containsKey(key)) {
                            freqs.put(key,freqs.get(key) +1);
                        }else {
                            freqs.put(key, 1L);
                        }
                    }
                }
        ));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        return freqs;
    }

    // 通过锁的方式锁住Map，然后做判断，读取现在的累加值、加1、保存累加后值的逻辑。
    private Map<String , Long> gooduse() throws InterruptedException {
        // 初始化元素的数量
        ConcurrentHashMap<String, LongAdder> freqs = new ConcurrentHashMap<>(TIME_COUNT2);
        // 创建线程池 默认大小THREAD_COUNT2
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT2);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, LOOP_COUNT).parallel().forEach(i-> {
           String key = "item" + ThreadLocalRandom.current().nextInt(TIME_COUNT2);
           // 利用computeIfAbsent() 方法来实例化LongAdder ,然后利用LongAdder 来进行线程安全计数
            freqs.computeIfAbsent(key, k-> new LongAdder()).increment();

        }));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        // 因为value 是LongAdder 而不是Long ,所以需要做一次转换才能返回
        return freqs.entrySet().stream().collect(
                Collectors.toMap(
                        e ->e.getKey(),
                        e -> e.getValue().longValue()
                )
        );
    }

    // 测试ConcurrentHashMap使用
    @GetMapping("/TestConcur")
    public String TestConCurrent() throws Exception {
        // 创建一个监听器，用来检验某一段代码的执行效率
        StopWatch watch = new StopWatch();
        watch.start("normaluse");
        Map<String, Long> nor = normaluse();
        watch.stop();

        // 检验元素的数量  Assert是springboot中的断言语法
        Assert.isTrue(nor.size() == TIME_COUNT2, "normaluse size error");
        // 检验元素的累计总数
        Assert.isTrue(nor.entrySet().stream().mapToLong(item -> item.getValue()).reduce(0, Long::sum) == LOOP_COUNT,
                "normaluse count error");

        watch.start("gooduse");
        Map<String, Long> god = gooduse();
        watch.stop();

        // 检验元素的数量  Assert是springboot中的断言语法
        Assert.isTrue(god.size() == TIME_COUNT2, "gooduse size error");
        // 检验元素的累计总数
        Assert.isTrue(god.entrySet().stream().mapToLong(item -> item.getValue()).reduce(0, Long::sum) == LOOP_COUNT,
                "gooduse count error");
        // 输出性能
        System.out.println(watch.prettyPrint());
        return "OK";
    }

    /**
     * CopyOnWriteArrayList 是一个线程安全的ArrayList ，但因为其实现方式是，每次修改数据的时候都会
     * 复制一份数据出来，因此适用于 读多写少的场景。
     */

    @GetMapping("/copyWrite")
    public Map testWrite() {
        List<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        StopWatch stopWatch = new StopWatch();
        int loopCount = 1000000;
        stopWatch.start("Write:copyOnWriteArrayList");
        // 循环100000次，并发往CopyOnwrite中 写入随机元素
        IntStream.rangeClosed(1,loopCount).parallel().forEach(__ -> copyOnWriteArrayList.add(ThreadLocalRandom.current().nextInt(loopCount)));
        stopWatch.stop();

        stopWatch.start("Write:synchronzedList");
        IntStream.rangeClosed(1,loopCount).parallel().forEach(__ -> synchronizedList.add(ThreadLocalRandom.current().nextInt(loopCount)));
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
        Map result = new HashMap();
        result.put("copyOnWrite", copyOnWriteArrayList.size());
        result.put("synchronize", synchronizedList.size());
        return  result;
    }

    // 测试并发读性能
    // 定义一个帮助方法用来填充List
    private void addAll(List<Integer> list){
        list.addAll(IntStream.rangeClosed(1,1000000).boxed().collect(Collectors.toList()));
    }

    @GetMapping("/read")
    public Map testRead() {
        List<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        List<Integer> syncronizedList = Collections.synchronizedList(new ArrayList<>());
        // 填充数据
        addAll(copyOnWriteArrayList);
        addAll(syncronizedList);

        StopWatch stopWatch = new StopWatch();
        int loopCount = 1000000;
        int count = copyOnWriteArrayList.size();

        stopWatch.start("Read：copyOnWriteArrayList");
        // 循环100 0000 次并发从CopyOnWriteArrayList随机查询元素
        IntStream.rangeClosed(1, loopCount).parallel().forEach(__ -> copyOnWriteArrayList.get(ThreadLocalRandom.current().nextInt(count)));
        stopWatch.stop();

        stopWatch.start("Read:synchronizedlist");
        IntStream.rangeClosed(1, loopCount).parallel().forEach(__ -> syncronizedList.get(ThreadLocalRandom.current().nextInt(count)));
        stopWatch.stop();

        Map result = new HashMap();
        result.put("copyOnWriteArrayList",copyOnWriteArrayList.size());
        result.put("syncronizedList",syncronizedList.size());
        return result;
    }




}
