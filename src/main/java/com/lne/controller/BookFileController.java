package com.lne.controller;

import com.lne.vo.Books;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * FileName: BookFileController
 * Author:   fengsulin
 * Date:     2022/3/27 14:07
 * Description:
 */
@Controller
@RequestMapping("/books")
public class BookFileController {
    @RequestMapping("/add")
    public String addBooks(Books books, MultipartFile bookImage, HttpServletRequest request) throws IOException {//bookImage表示上传的文件
        System.out.println("~~~~~~books add~~~~~~~~~~");
        //截取文件后缀，生成新的文件名
        String  bn = bookImage.getOriginalFilename();
        String ext = bn.substring(bn.lastIndexOf('.'));
        String fileName = System.currentTimeMillis()+ext;

        //获取images目录所在服务器的路径
        String dir = request.getServletContext().getRealPath("imgs");
        String savePath = dir +"/"+fileName;

        //保存文件
        bookImage.transferTo(new File(savePath));

        //将文件的访问路径设置到books对象
        books.setBookImg("imgs/"+fileName);
        return "title";
    }
    @RequestMapping("/list")
    @ResponseBody
    public String[] listBooks(HttpServletRequest request){
        //获取imgs目录下所有文件
        System.out.println("~~~~~~~~~books list~~~~~~~~~");
        String filePath = request.getServletContext().getRealPath("imgs");
        File file = new File(filePath);
        String[] filesName = file.list();
        return filesName;
    }
    @RequestMapping("/upload")
    public void uploadFile(String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //从imgs目录找到当前文件
        String dir = request.getServletContext().getRealPath("imgs");
        String realName = dir +"/"+fileName;
        response.setContentType("application/exe");
        response.setCharacterEncoding("utf-8");
        FileInputStream fileInputStream = new FileInputStream(new File(realName));
        response.addHeader("Content-Disposition","attachment;filename="+fileName);
        IOUtils.copy(fileInputStream,response.getOutputStream());
    }
}
