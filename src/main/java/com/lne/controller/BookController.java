package com.lne.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lne.vo.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.util.resources.en.CurrencyNames_en_MT;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * FileName: BookController
 * Author:   fengsulin
 * Date:     2022/3/18 23:27
 * Description:控制器类
 */
@Controller
@RequestMapping("/book")
public class BookController {
    @PostMapping("/add")
    public ModelAndView addBook(@RequestParam(name = "bookName") String bookName,
                                @RequestParam(name = "bookAuthor") String bookAuthor,
                                @RequestParam(name = "bookPrice") double bookPrice
                                ){
        System.out.println("~~~~~~~book add~~~~~~~~~~");
        ModelAndView modelAndView = new ModelAndView("title");
        modelAndView.addObject("key1","666");
        modelAndView.addObject("key2",new Book("data","马克",33.22));
        return modelAndView;
    }
    @RequestMapping("/list")
    public void listBook(@RequestHeader(name = "token") String token){
        System.out.println("~~~~~~~book list~~~~");
        System.out.println(token);
    }

    @RequestMapping("/update")
    @ResponseBody
    public Book updateBook(@RequestBody Book book){
        System.out.println("~~~~~~~book update~~~~~~");
//        System.out.println(book);
        Book book1 = new Book("python","利达",33.34);
        return book1;
        //流的形式响应异步请求
//        try {
//            String s = new ObjectMapper().writeValueAsString(book);
//            response.setCharacterEncoding("utf-8");
//            response.setContentType("application/json");
//            PrintWriter writer = response.getWriter();
//            writer.println(s);
//            writer.flush();
//            writer.close();
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //servlet中使用request的传输流接受请求体数据
        //方法传参HttpServletRequest request
//        try {
//            ServletInputStream is = request.getInputStream();
//            StringBuffer buffer = new StringBuffer();
//            byte[] bytes = new byte[10];
//            int len = -1;
//            while((len = is.read(bytes)) != -1){
//                String s = new String(bytes,0,len);
//                buffer.append(s);
//            }
//            System.out.println(buffer.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
