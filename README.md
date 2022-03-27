# SpringMvc学习

## 一、SpringMVC概述

## 二、基于maven创建一个web工程

### 2.1 maven项目设置

- 新建一个maven项目
- pom中设置packaging为war
- main目录下新建目录wepapp，在webapp下新建目录WEB-INF
- 导入jsp和servlet依赖--通过模块导入tomcat即可实现
- 配置tomcat

### 2.2 添加springmvc依赖

```xml
    <packaging>war</packaging>
    <properties>
        <spring.version>5.3.16</spring.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aspects</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>

    </dependencies>
```

### 2.3 创建springmvc的配置文件

在resource下创建mvc的配置文件，添加mvc的命名空间

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/aop
       http://www.springframework.org/schema/aop/spring-aop.xsd
       http://www.springframework.org/schema/mvc
       http://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!--    声明使用注解配置-->
    <context:annotation-config/>
    <!--    声明spring工厂的注解扫描范围-->
    <context:component-scan base-package="com.lne"/>
    <!--    基于注解配置的aop代理-->
    <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
<!--    声明mvc使用注解驱动-->
    <mvc:annotation-driven/>

</beans>
```

WEB-INF目录创建web.xml文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="3.0"
         xmlns="http://java.sun.com/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
    http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd">
    <servlet>
        <servlet-name>SpringMVC</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring-servlet.xml</param-value>
        </init-param>
    </servlet>
    <servlet-mapping>
        <servlet-name>SpringMVC</servlet-name>
<!--        /* 拦截所有的http请求，包括jsp页面的请求,交给控制-->
<!--        / 拦截所有http请求，不包括jsp的请求，但不会放行html/css/js/图片等资源的请求-->
        <url-pattern>/</url-pattern>
    </servlet-mapping>
</web-app>
```

## 三、SpringMVC框架的使用

在springMVC中，我们把接受用户请求的类称为controller（控制器）

### 3.1 创建控制器

- 创建controller包，这包要在spring扫描范围内。

- 创建一个类

- 为类添加注解@Controller

- 在类上加注解@RequestMapping("url")声明控制器的请求url

- ```java
  @Controller
  @RequestMapping("/book")
  public class BookController {
  }
  ```

### 3.2 在控制器类中定义请求的方法

### 3.3 前端数据提交到控制器

#### 3.3.1 创建前端页面

book-add.jsp表单中的action属性设置为控制器类的url和对应的方法url的组合路径

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>book添加</title>
</head>
<body>
    <h3>添加图书</h3>
<form action="/book/add" method="post">
    <p>图书名称：<input type="text"/></p>
    <p>图书作者：<input type="text"></p>
    <p>图书价格：<input type="text">/p>
    <p><input type="submit" value="提交"></p>

</form>
</body>
</html>
```

#### 3.3.2 静态资源配置（放行）

springmvc配置文件中放行

spring-servlet.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/aop
       http://www.springframework.org/schema/aop/spring-aop.xsd
       http://www.springframework.org/schema/mvc
       http://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!--    声明使用注解配置-->
    <context:annotation-config/>
    <!--    声明spring工厂的注解扫描范围-->
    <context:component-scan base-package="com.lne"/>
    <!--    基于注解配置的aop代理-->
    <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
<!--    声明mvc使用注解驱动-->
    <mvc:annotation-driven/>
<!--    静态资源放行-->
    <mvc:resources mapping="/css/**" location="/css/"></mvc:resources>

</beans>
```

web.xml

```xml
    <servlet-mapping>
        <servlet-name>SpringMVC</servlet-name>
<!--        /* 拦截所有的http请求，包括jsp页面的请求,交给控制-->
<!--        / 拦截所有http请求，不包括jsp的请求，但不会放行html/css/js/图片等资源的请求-->
        <url-pattern>/</url-pattern>
    </servlet-mapping>
```

#### 3.3.2 前端页面提交数据

- 表单提交：输入框需要提供name属性，SpringMvc通过name属性取值

  ```jsp
      <h3>表单提交</h3>
  <form action="/book/add" method="post">
      <p>图书名称：<input name="bookName" type="text"/></p>
      <p>图书作者：<input name="bookAuthor" type="text"></p>
      <p>图书价格：<input name="bookPrice" type="text"></p>
      <p><input type="submit" value="提交"></p>
  </form>
  ```

  

- 超链接提交：<a href="book/add?bookname=java"/>

  ```jsp
  <h3>超链接提交</h3>
  <a href="/book/add?bookName=java"/>
  ```

  

- AJAX提交：请求头、请求行、请求体都可以传值

  ```jsp
  <h3>AJAX提交</h3>
  <input type="button" value="ajax提交" id="btn1"/>
  <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
  <script type="text/javascript" >
      $("#btn1").click(function(){
      var obj ={};
      obj.bookName = "Java";
      obj.bookAuthor = "张三";
      obj.bookPrice = 3.33;
      $.ajax({
          url: "/book/add",
          type: "post",
          headers: {},
          contentType: "application/json",
          data: obj,
          success:function(res)
          {
              console.log(res);
          }
      });
          });
  </script>
  ```

  ### 3.4 控制器接收请求参数

  #### 3.4.1 @ReauestParam注解

  用于接收请求行的参数，接收的参数名默认和方法的参数一致，否则要指定传入的参数名称

  ```java
      @PostMapping("/add")
      public void addBook(@RequestParam(name = "bookName") String bookName,
                          @RequestParam(name = "bookAuthor") String bookAuthor,
                          @RequestParam(name = "bookPrice") double bookPrice){
          System.out.println("~~~~~~~book add~~~~~~~~~~");
      }
  ```

  #### 3.4.2 @RequestHeader注解

  用于接收请求头的信息，名称必须要执行

  ```java
      @RequestMapping("/list")
      public void listBook(@RequestHeader(name = "token") String token){
          System.out.println("~~~~~~~book list~~~~");
          System.out.println(token);
      }
  ```

  #### 3.4.3 @RequestBody注解

  用于接收请求体的数据，两种方式

  - servlet传统使用流读取

  - 使用注解接收json格式的数据，转换对象类型（提前创建对象），注意转换json数据需要导包jackson

    ```xml
    <!--        jackson-->
            <dependency>
                <groupId>com.fasterxml.jackson.core</groupId>
                <artifactId>jackson-databind</artifactId>
                <version>2.13.2</version>
            </dependency>
    ```

    

  ```java
      @RequestMapping("/update")
      public void updateBook(@RequestBody Book book){
          System.out.println("~~~~~~~book update~~~~~~");
          System.out.println(book);
  
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
  ```

  ### 3.5 控制器响应前端请求

  #### 3.5.1 控制器响应同步请求

  > 同步请求：form，超链接请求

  - 处理同步请求的方法的返回类型定义为String或ModelAndView，以实现页面的跳转

    - 返回类型为String，直接return 页面名称（属于转发，web页面路径没变）

      ```java
          @PostMapping("/add")
          public String addBook(@RequestParam(name = "bookName") String bookName,
                              @RequestParam(name = "bookAuthor") String bookAuthor,
                              @RequestParam(name = "bookPrice") double bookPrice){
              System.out.println("~~~~~~~book add~~~~~~~~~~");
              return "/title.jsp";
              //return "redirect:/title.jsp" //重定向
          }
      ```

    - 返回类型为ModelAndView，创建对象，返回对象（属于转发，web页面路径没变）

      ```java
          @PostMapping("/add")
          public ModelAndView addBook(@RequestParam(name = "bookName") String bookName,
                                      @RequestParam(name = "bookAuthor") String bookAuthor,
                                      @RequestParam(name = "bookPrice") double bookPrice){
              System.out.println("~~~~~~~book add~~~~~~~~~~");
              ModelAndView modelAndView = new ModelAndView("/title.jsp");
              //ModelAndView modelAndView = new ModelAndView("redirect:/title.jsp");  //重定向
              return modelAndView;
          }
      ```

> 异步请求：ajax请求

- 以流的形式的响应

  ```java
      @RequestMapping("/update")
      public void updateBook(@RequestBody Book book, HttpServletResponse response){
          System.out.println("~~~~~~~book update~~~~~~");
          System.out.println(book);
          //流的形式响应异步请求
          try {
              String s = new ObjectMapper().writeValueAsString(book);
              response.setCharacterEncoding("utf-8");
              response.setContentType("application/json");
              PrintWriter writer = response.getWriter();
              writer.println(s);
              writer.flush();
              writer.close();
          } catch (JsonProcessingException e) {
              e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
  
  ```

  

- springMVC提供的方式，直接在控制器方法中返回响应的对象

- 在控制器方法前添加注解，将返回类型转换为json格式@ResponseBody

  ```java
      @RequestMapping("/update")
      @ResponseBody
      public Book updateBook(@RequestBody Book book){
          System.out.println("~~~~~~~book update~~~~~~");
          System.out.println(book);
          Book book1 = new Book("python","利达",33.34);
          return book1;
      }
  ```

#### 3.5.2 控制器响应同步请求的数据传递

- 返回类型为String

- ```java
    //1.在方法传参中定义一个Model类型的参数
    //2.在return 页面之前，向Model中添加键值对
    @PostMapping("/add")
      public String addBook(@RequestParam(name = "bookName") String bookName,
                                  @RequestParam(name = "bookAuthor") String bookAuthor,
                                  @RequestParam(name = "bookPrice") double bookPrice,
                                  Model model){
          System.out.println("~~~~~~~book add~~~~~~~~~~");
          model.addAttribute("key1","666");
          model.addAttribute("key2",new Book("data","马克",33.22));
          return "redirect:/title.jsp";
      }
  //方法二，使用HttpServletRequest对象
    @PostMapping("/add")
      public String addBook(@RequestParam(name = "bookName") String bookName,
                                  @RequestParam(name = "bookAuthor") String bookAuthor,
                                  @RequestParam(name = "bookPrice") double bookPrice,
                                  HttpServletRequest request){
          System.out.println("~~~~~~~book add~~~~~~~~~~");
          request.setdAttribute("key1","666");
          request.asetAttribute("key2",new Book("data","马克",33.22));
          return "redirect:/title.jsp";
      }
  ```

  

- 返回类型为ModelAndView

- ```java
      @PostMapping("/add")
      public ModelAndView addBook(@RequestParam(name = "bookName") String bookName,
                                  @RequestParam(name = "bookAuthor") String bookAuthor,
                                  @RequestParam(name = "bookPrice") double bookPrice
                                  ){
          System.out.println("~~~~~~~book add~~~~~~~~~~");
          ModelAndView modelAndView = new ModelAndView("redirect:/title.jsp");
          modelAndView.addObject("key1","666");
          modelAndView.addObject("key2",new Book("data","马克",33.22));
          return modelAndView;
      }
  ```

### 3.6 解决中文乱码

#### 3.6.1 前端乱码

- jsp页面

- ```jsp
  <%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
  ```

- html页面

- ```html
  <meta charset="UTF-8">
  ```

  

3.6.2 服务器编码

- tomcat/conf/server

- ```xml
      <Connector port="8080" protocol="HTTP/1.1"
                 connectionTimeout="20000"
                 redirectPort="8443" URIEncoding="UTF-8" />
  ```

3.6.3 SpringMVC的编码

- 在web.xml中配置springMVC的编码方式

- ```xml
       <filter>
          <filter-name>EncodingFilter</filter-name>
          <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
          <init-param>
              <param-name>encoding</param-name>
              <param-value>utf-8</param-value>
          </init-param>
          <init-param>
              <param-name>foreEncoding</param-name>
              <param-value>true</param-value>
          </init-param>
      </filter>
      <filter-mapping>
          <filter-name>EncodingFilter</filter-name>
          <url-pattern>/*</url-pattern>
      </filter-mapping>
  ```

  

## 四、springMVC处理流程

### 4.1 请求处理流程

![image-20220326224223286](SpringMvc%E5%AD%A6%E4%B9%A0.assets/image-20220326224223286.png)

### 4.2 SpringMVC核心组件

- DispatcherServlet：前端控制器，总控制器
  - 作用：接收请求、协同各组件、响应请求
- HadnlerMapping：处理器映射
  - 作用：负责根据用户请求的url找到对应的Handler
- HandlerAdaper：处理器适配器
  - 作用：按照调用链通过设配器模式完成Handler的调用
- Handler：处理器/控制器，由工程师根据业务需求开发
  - 作用：处理请求
- ModelAndView：视图模型
  - 用于封装Handler返回的数据以及响应的视图
- ViewSolver：视图解析器
  - 作用：对ModelAndView进行解析
- View：数据渲染

### 4.3 处理器映射器

> springMvc提供的处理器映射器：
>
> - BeanNameUrlHandlerMapping：根据控制器的id进行访问控制器
> - SimpleUrlHandlerMapping：根据配置的url进行访问（默认），可通过注解或在配置文件中设置

### 4.4 视图解析器

> spring提供了多个视图解析器：
>
> - UrlBaseViewResolver
> - InternalResourceViewResolver

- UrlBaseViewResolver

- - 添加依赖jstl

  - ```xml
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>jstl</artifactId>
        <version>1.2</version>
    </dependency>
    ```

  - 配置视图解析器spring-servlet.xml

  - ```xml
    <!--    配置视图解析器-->
        <bean id="viewResolver" class="org.springframework.web.servlet.view.UrlBasedViewResolver">
            <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"></property>
            <property name="prefix" value="/"></property>
            <property name="suffix" value=".jsp"></property>
        </bean>
    ```

- InternalResourceViewResolver

- ```xml
  <!--    配置视图解析器-->
      <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
          <property name="prefix" value="/"></property>
          <property name="suffix" value=".jsp"></property>
      </bean>
  ```



## 五、日期格式处理

### 5.1 日期格式处理

#### 5.1.1 创建自定义日期格式转化器

```java
package com.lne.utils;


import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * FileName: MyDateFormat
 * Author:   fengsulin
 * Date:     2022/3/27 0:00
 * Description:
 1.创建一个类是实现Converter接口，泛型指定从什么类型转换为什么类型
 2.实现convert方法
 */
public class MyDateFormat implements Converter<String, Date> {
    SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd:HH:MM:SS");
    public Date convert(String source) {
        Date date = null;
        try {
            date = format.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
```

5.1.2  配置自定义转换器spring-servlet.xml

```xml
<!--    声明mvc使用注解驱动-->
    <mvc:annotation-driven conversion-service="convertDate"/>


<!--    配置自定义日期格式转换器-->
    <bean id="convertDate" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
        <property name="converters">
            <set>
                <bean class="com.lne.utils.MyDateFormat"></bean>
            </set>
        </property>
    </bean>

```

## 六、文件上传

### 6.1 SpringMVC框架部署

- 基于Maven创建web项目
- 添加SpringMVC所需依赖
  - context 、aspects 、jdbc、 test、web、webmvc、jackson
- 创建spring配置文件
- 在main目录下创建文件夹webapp，webapp想创建文件夹WEB-INF，WEB-INF下新建文件web.xml
- 添加tomcat模块（添加jsp和servlet依赖）
- 放行静态资源
- web.xml配置编码过滤器



### 6.2 文件上传案例

#### 6.2.1 前端提交文件

- 表单提交方式为post

- 表单enctype属性设置为multipart/form-data

- ```jsp
  <h4>添加图书信息</h4>
  <form action="books/add" method="post" enctype="multipart/form-data">
      <p>图书名称：<input type="text" name="bookName"> </p>
      <p>图书作者：<input type="text" name="bookAuthor"> </p>
      <p>图书价格：<input type="text" name="bookPrice"> </p>
      <p>图书封面：<input type="file" name="bookImage"> </p>
      <p><input type="submit" value="提交"></p>
  
  </form>
  ```

#### 6.2.2 控制器接受数据和文件

> springMVC处理上传文件需要借助与类CommonsMultipartResolver文件解析器

- 添加依赖commons-io、commons--upload

- ```xml
  <!--        commons-io-->
          <dependency>
              <groupId>commons-io</groupId>
              <artifactId>commons-io</artifactId>
              <version>2.11.0</version>
          </dependency>
  <!--commons-fileupload-->
          <dependency>
              <groupId>commons-fileupload</groupId>
              <artifactId>commons-fileupload</artifactId>
              <version>1.4</version>
          </dependency>
  ```

- spring-servlet.xml配置文件解析器

- ```xml
  <!--    配置文件解析器-->
      <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
          <property name="defaultEncoding" value="utf-8"></property>
          <property name="maxInMemorySize" value="102400"></property>
          <property name="maxUploadSize" value="102400"></property>
      </bean>
  ```

- controller控制器

- ```java
      @RequestMapping("/add")
      public String addBooks(Books books, MultipartFile bookImage, HttpServletRequest request) throws IOException {//bookImage表示上传的文件
          System.out.println("~~~~~~books add~~~~~~~~~~");
          //截取文件后缀，生成新的文件名
          String  bn = bookImage.getOriginalFilename();
          String ext = bn.substring(bn.lastIndexOf('.'));
          String fileName = System.currentTimeMillis()+ext;
  
          //获取images目录所在服务器的路径
          String dir = request.getServletContext().getRealPath("images");
          String savePath = dir +"/"+fileName;
  
          //保存文件
          bookImage.transferTo(new File(savePath));
  
          //将文件的访问路径设置到books对象
          books.setBookImg("images/"+fileName);
          return "title";
      }
  ```

  ### 6.3 文件下载

  #### 6.3.1 前端设置（注意静态资源放行）

  资源路径：https://www.bootcss.com/

  ```jsp
  <%--
    Created by IntelliJ IDEA.
    User: 18311
    Date: 2022/3/27
    Time: 15:51
    To change this template use File | Settings | File Templates.
  --%>
  <%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
  <html>
  <head>
      <title>图书列表</title>
  </head>
      <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
      <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
  
      <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
      <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css" integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">
  
      <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
      <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>
  <body>
  <h4>文件列表</h4>
  <div class="row" id="container"></div>
  
  <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
  <script type="text/javascript">
      $.get("books/list",function (res){
          for(var i = 0;i<res.length;i++){
              var fn = res[i];
              var htlStr = "<div class='col-lg-2 col-md-4 col-sm-6'><div class='thumbnail'><img src='imgs/"+fn+"' alt='...'><div class='caption'><p><a href='books/upload?fileName="+fn+"' class='btn btn-primary' role='button'>下载</a> </p></div></div></div>";
              $("#container").append(htlStr);
          }
          // console.log(res);
      },"json");
  </script>
  </body>
  </html>
  ```

  #### 6.3.2 后端设置

  ```java
      @RequestMapping("/upload")
      public void uploadFile(String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
          //从imgs目录找到当前文件
          String dir = request.getServletContext().getRealPath("imgs");
          String realName = dir +"/"+fileName;
          response.setContentType("application/exe");
          FileInputStream fileInputStream = new FileInputStream(new File(realName));
          response.addHeader("Content-Disposition","attachment;filename="+fileName);
          IOUtils.copy(fileInputStream,response.getOutputStream());
      }
  ```

## 七、统一异常处理

> http状态异常、exception等

### 7.1 HTTP异常状态统一处理

> HTTP Status 404

- 创建一个用于进行异常提示的页面：404.jsp

- web.xml设置

- 基于servlet-api的处理

- ```xml
  <!--    http异常处理提示-->
      <error-page>
          <error-code>404</error-code>
          <location>/404.jsp</location>
      </error-page>
      <error-page>
          <error-code>500</error-code>
          <location>/500.jsp</location>
      </error-page>
      <error-page>
          <error-code>400</error-code>
          <location>/400.jsp</location>
      </error-page>
  ```

### 7.2 Java代码异常

#### 7.2.1 基于servlet-api的处理

```xml
<!--    java代码异常处理-->
    <error-page>
        <exception-type>java.lang.NumberFormatException</exception-type>
        <location>/err.jsp</location>
    </error-page>
```

#### 7.2.2 基于SpringMVC处理

