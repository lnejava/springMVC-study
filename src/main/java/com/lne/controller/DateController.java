package com.lne.controller;

import com.lne.vo.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * FileName: DateController
 * Author:   fengsulin
 * Date:     2022/3/26 23:54
 * Description:
 */
@Controller
@RequestMapping("/date")
public class DateController {
    @RequestMapping("add")
    @ResponseBody
    public Book addBook(Book book){
        System.out.println("~~~~~add book date");
        return book;
    }
}
