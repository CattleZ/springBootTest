package com.example.webtest.ControllerTest;

import com.example.webtest.entity.student;
import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.xerial.snappy.Snappy;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


@RestController
@RequestMapping("/student")
public class StudentController {
    @GetMapping("/st/{name}")
    @CrossOrigin(value="http://localhost:8081",allowedHeaders = "*",maxAge = 1800)
    public String getStudent(@PathVariable String name){
        System.out.println("---"+name);
        return "接收成功";
    }

    @PostMapping("/st")
    @CrossOrigin(value="http://localhost:8081",allowedHeaders = "*",maxAge = 1800)
    public String getStudent2(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("---"+request.getParameter("name"));
        return "接收成功";
    }

    @GetMapping("/Snappytest")
    public void SnappyCompressCeshi(){
        // 先创建一个byte长度为10000的字符串
        StringBuffer sb = new StringBuffer();
        int i=0;
        while(sb.length()<=10000){
            i++;
            String cur = "BJ00"+Integer.toString(i)+",";
            sb.append(cur);
        }
        String origin = sb.toString();
        System.out.println("原始数据为："+origin);
        byte [] org = origin.getBytes(StandardCharsets.UTF_8);
        System.out.println("Snappy原始长度："+org.length);
        System.out.println("Snappy原始内容："+origin);
        byte [] target = null;
        try{
            // 使用Snappy 进行压缩
            target = Snappy.compress(org);
            System.out.println("Snappy压缩后大小："+target.length);
            System.out.println("Snappy压缩后内容："+target.toString());

            // 使用Snappy 进行解压缩
            String targets = new String(Snappy.uncompress(target));
            System.out.println("Snappy解压缩后的大小："+targets.length());
            System.out.println("Snappy解压缩后的内容："+targets);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //测试lz4压缩算法
    @GetMapping("/lz4Test")
    public void Lz4CompressCeshi(){
        // 先创建一个byte长度为10000的字符串
        StringBuffer sb = new StringBuffer();
        int i=0;
        while(sb.length()<=10000){
            i++;
            String cur = "BJ00"+Integer.toString(i)+",";
            sb.append(cur);
        }
        String origin = sb.toString();
        byte [] org = origin.getBytes(StandardCharsets.UTF_8);
        System.out.println("LZ4压缩前的数据大小："+org.length);
        // 创建LZ4工厂
        LZ4Factory factory = LZ4Factory.fastestInstance();
        // 设置解压数据的长度
        final int decompressedLength = org.length;
        // 压缩数据
        LZ4Compressor compressor = factory.fastCompressor();
        int maxCompressedLength = 6000;
        byte [] compressed = new byte[6000];
        int compressedLength = compressor.compress(org, 0, decompressedLength, compressed,0,maxCompressedLength);

        System.out.println("LZ4压缩后的数据大小："+compressedLength);
        System.out.println("LZ4压缩后2的数据大小："+compressed.length);

    }

    //测试jdk Default压缩算法
    @GetMapping("/jdkDefu")
    public void JdkDefaultCompress(){
        // 先创建一个byte长度为10000的字符串
        StringBuffer sb = new StringBuffer();
        int i=0;
        while(sb.length()<=10000){
            i++;
            String cur = "BJ00"+Integer.toString(i)+",";
            sb.append(cur);
        }
        String origin = sb.toString();
        byte [] org = origin.getBytes(StandardCharsets.UTF_8);
        System.out.println("jdkDefu原始数据："+origin);
        System.out.println("jdkDefu压缩前的数据大小："+org.length);
        try{
            // 压缩数据
            Deflater compresser = new Deflater();
            compresser.setInput(org);
            compresser.finish();
            byte [] output = new byte[origin.length()];
            int compressedDatalength = compresser.deflate(output);
            compresser.end();
            System.out.println("jdkDefu压缩后的数据大小："+compressedDatalength);
            System.out.println("jdkDefu压缩后output的数据大小："+output.length);
            // 解压数据
            Inflater decompresser = new Inflater();
            decompresser.setInput(output,0,compressedDatalength);
            byte [] result = new byte[origin.length()+1];
            int resultLength = decompresser.inflate(result);
            decompresser.end();
            System.out.println("jdkDefu解压缩后的数据大小："+resultLength);
            String outputString = new String(result, 0, resultLength, "UTF-8");
            System.out.println("jdkDefu解压后的数据为："+outputString);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // 测试
    @GetMapping("/tx")
    public void getTest(){
        List<student> ls = new  ArrayList<>();
        student stu = new student();
        stu.setName("李四");
        student stu2 = new student();
        stu.setName("张三");
        ls.add(stu);
        ls.add(stu2);
        for(student s : ls){
            System.out.println(s.toString());
        }
        ls.stream().forEach(it -> it.setName("王武"));
        for(student s : ls){
            System.out.println(s.toString());
        }
    }


}
