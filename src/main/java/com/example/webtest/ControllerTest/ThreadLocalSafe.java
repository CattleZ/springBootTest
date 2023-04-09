package com.example.webtest.ControllerTest;


import com.example.webtest.entity.Data;
import jodd.util.concurrent.ThreadFactoryBuilder;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static java.lang.System.out;


@RestController
@RequestMapping("/threadLocal")
public class ThreadLocalSafe {
    @GetMapping("/test")
    public Object getTest(){
        String payload = IntStream.rangeClosed(1,10)
                .mapToObj(p -> "b")
                .collect(Collectors.joining(""))+UUID.randomUUID().toString();
        out.println("over");
        return payload;
    }
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
        out.println("init size:"+concurrentHashMap.size());
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        // 使用线程池并发处理逻辑
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
            // 查询还需要补充多少个元素
            int gap = TIME_COUNT - concurrentHashMap.size();
            out.println("gap size:"+gap);
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
        out.println("finish size:"+concurrentHashMap.size());
        return "ok";
    }

    // 修正方法
    // 对ConcurrentHash 的获取大小操作进行添加锁，但是这样操作的后果就是没有使用其新的特性 ，无法发挥出其威力。
    @GetMapping("/right1")
    public String right1(){
        ConcurrentHashMap<String, Long> concurrentHashMap = getData(TIME_COUNT-100);
        // 初始化900个元素
        out.println("init size:"+concurrentHashMap.size());
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        // 使用线程池并发处理逻辑
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
            synchronized (concurrentHashMap) {
                // 查询还需要补充多少个元素
                int gap = TIME_COUNT - concurrentHashMap.size();
                out.println("gap size:"+gap);
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
        out.println("finish size:"+concurrentHashMap.size());
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
        out.println(watch.prettyPrint());
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

        out.println(stopWatch.prettyPrint());
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

    /**
     * 静态字段属于类，类级别的锁才能保护，而非静态字段属于类实例，实例级别的锁就可以保护。
     */
    @GetMapping("/wrong2")
    public int wrong2(@RequestParam(value = "count", defaultValue = "1000000") int count){
        Data.reset();
        // 多线程循环一定次数调用Data类不同实例的wrong方法
        IntStream.rangeClosed(1, count).parallel().forEach(i -> new Data().wrong());
        return Data.getCounter();
    }
    @GetMapping("/right2")
    public int right2(@RequestParam(value = "count", defaultValue = "1000000") int count){
        Data.reset();
        // 多线程循环一定次数调用Data类不同实例的wrong方法
        IntStream.rangeClosed(1, count).parallel().forEach(i -> new Data().right());
        return Data.getCounter();
    }

    /**
     * 代码块加锁粒度，降低锁的粒度，仅对必要的代码块进行加锁
     */
    private List<Integer> data = new ArrayList<>();

    // 不涉及共享资源的慢方法
    private void slow(){
        try{
            TimeUnit.MILLISECONDS.sleep(10);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    // 错误的加锁方法
    @GetMapping("/wrong3")
    public int wrong3(){
        StopWatch sw = new StopWatch();
        sw.start("wrong 全锁");
        IntStream.rangeClosed(1,1000).parallel().forEach(i ->
        {
            synchronized (this) {
                slow();
                data.add(i);
        }
        });
        sw.stop();
        out.println(sw.prettyPrint());
        return data.size();
    }

    // 细粒度锁
    @GetMapping("/right3")
    public int right3(){
        StopWatch sw = new StopWatch();
        sw.start();
        IntStream.rangeClosed(1,1000).parallel().forEach(i->{
            slow();
            synchronized (this){
                data.add(i);
            }
        });
        sw.stop();
        out.println(sw.prettyPrint());
        return data.size();
    }

    /**
     * 线程池的使用
     * 1. 禁止使用Executors类中定义的快捷方式来创建线程，而应该使用new ThreadPoolExecutor来创建线程
     * newFixedThreadPool 方法中的等待队列LinkedBlockingQueue 是一个Integer.Max_VALUE的无限队列，如果任务一直来，但是线程处理比较慢的
     * 话会导致oom
     * newCachedThreadPool的最大线程数量是Integer.MAX_VALUE 是一个没有存储空间的阻塞队列。只要有任务来没有线程处理就要创建一条线程处理。
     * 大量的任务会导致大量的线程创建，从而导致oom
     *
     */
    //  newFixedThreadPool 出现的OOM问题
    @GetMapping("/oom1")
    public void oom1() throws InterruptedException {
        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        // 打印线程池的信息
        printStatus(threadPool);
        for(int i = 0;i< 100000000;i++){
            threadPool.execute(()-> {
                String payload = IntStream.rangeClosed(1,1000000)
                        .mapToObj(__ -> "a")
                        .collect(Collectors.joining(""))+UUID.randomUUID().toString();
                try {
                    TimeUnit.HOURS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                out.println(payload);
            });
        }
        threadPool.shutdown();
        threadPool.awaitTermination(1,TimeUnit.HOURS);
    }

    /**
     * 线程池的特性检测
     * @param
     */
    @GetMapping("/rightoom")
    public int rightoom() throws InterruptedException{
        // 使用一个计数器跟踪完成的任务数量
        AtomicInteger atomicInteger = new AtomicInteger();
        // 创建一个具有两个核心线程，5个最大线程、使用容量为10的ArrayBlockingQueue阻塞队列作为工作队列的线程池，
        // 使用默认的abortPolicy拒绝策略
        ThreadPoolExecutor threadpool = new ThreadPoolExecutor(2, 5,
                5, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new ThreadFactoryBuilder().setNameFormat("demo-threadPool-%d").get(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        printStatus(threadpool);
        // 每隔一秒提交一次，一共提交20次
        IntStream.rangeClosed(1,20).forEach(i->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int id = atomicInteger.incrementAndGet();
            try{
                threadpool.submit(()->{
                    out.println(id+":start");
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    out.println(id+":end");
                });
            }catch (Exception e){
                out.println(id+"error submit task");
                atomicInteger.incrementAndGet();
            }
        });
        TimeUnit.SECONDS.sleep(60);
        return atomicInteger.incrementAndGet();
    }
    // 异地调用，多线程方式
    @GetMapping("/asyncmethodcaller")
    public void AsyncMethodCaller() throws InterruptedException {
        // 创建一个新线程并启动
        Thread thread = new Thread(() -> {
            // 调用需要异步执行的方法
            String result = asyncMethod();
            System.out.println("异步方法执行结果：" + result);
        });
        thread.start();

        // 主线程继续执行其他任务
        System.out.println("主线程继续执行其他任务...");

        // 等待异步线程执行完成
        thread.join();

        System.out.println("主线程执行完成");
    }

    // 需要异步执行的方法
    public String asyncMethod() {
        // 模拟一个耗时操作
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello World";
    }

    private void printStatus(ThreadPoolExecutor threadPool) {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
                ()->
        {
            System.out.println("==========");
            System.out.println("Pool Size: {}"+threadPool.getPoolSize());
            out.println("Active Threads: "+threadPool.getActiveCount());
            out.println("Number of Tasks Completed:"+threadPool.getCompletedTaskCount());
            out.println("Number of Tasks in Queue:"+threadPool.getQueue().size());
            out.println("===========");
        },0,1,TimeUnit.SECONDS);
    }
}
