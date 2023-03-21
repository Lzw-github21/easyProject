
# day 1
### 设置svn或者git上传忽略
```
1.点击File->Settings->Editor->File Types`
2.选择 Ignored Files and Folders
```
### 项目启动类，启动基本依赖，测试helloWord接口添加
######相关依赖
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency> --web场景启动器 为web开发予以支持
        <parent>
            <groupId>org.springframework.boot</groupId>
            <version>2.3.2.RELEASE</version>
          <artifactId>spring-boot-starter-parent</artifactId>
        </parent>  --集成springboot基本组件
######注解@RestController
```
是@controller和@ResponseBody 的结合，@Controller 将当前修饰的类注入SpringBoot IOC容器，使得从该类所在的项目跑起来的过程中，这个类就被实例化。
@ResponseBody 它的作用简短截说就是指该类中所有的API接口返回的数据，甭管你对应的方法返回Map或是其他Object，它会以Json字符串的形式返回给客户端。
```
######注解@RequestMapping
```
1.标注在类或方法上，映射请求路径
2.可以设置多个value  例如：@RequestMapping(value = {"/testRequest","/test"}，,method = RequestMethod.GET)
``` 
######注解@GetMapping
```
@GetMapping等同于@RequestMapping(method = { RequestMethod.GET })
```
### 初始化项目模块apigateWay和dataServer
```
在server模块下添加新模块自动集成父级模块，不需要手动添加。
父级模块<modules>中内容也会自动生成指向子模块
```

# day 2
###1.添加yml配置文件
+ 找到resourse包，右键点击new ——> file ——> 名称取xxx.yml ——> 在文件下生成.yml文件
### 2.修改启动端口号
```
server: 
    port: XXXX
````
# day 3
###1.项目整合nacos
######遇到的问题
```
1.nacos依赖，在引入spring-cloud-alibaba-dependencies，无法集成springCloud中的nacos版本
解决方法，将版本仲裁父级依赖放入<dependencyManagement>标签中。
2.nacos配置 file-extension指定文件类型，相关博客中都为ymal，我项目中不生效，改为yml则生效，原因有待分析。
3.nacos版本不兼容问题，通过springboot版本找到相应的spring-cloud-alibaba-dependencies版本，依靠父子项目版本仲裁规则解决依赖版本冲突问题。
```
###2. 实体类依赖 lombok
```
添加lombok依赖，相关注解使用方式有待归纳。
```
###3.通过注解开启异步。
```
具体使用方法
1.添加依赖（有待验证是否需要添加）
        <dependency>
            <groupId>com.google.guava</groupId>
            <artifactId>guava</artifactId>
            <version>30.1.1-jre</version>
        </dependency>
2.启动类添加注解@EnableAsync(proxyTargetClass=true)开启异步
3.创建线程池配置文件 两种写法，具体参考 --https://www.dianjilingqu.com/335384.html
4.在异步类方法添加 @Async 注解，标识该类为异步。
```

# day 4
###1.项目整合commonsLang3工具包，并整合整理相关用法
######整理常用类
```
1.StringUtils.isEmpty() 判断字符串是否为空 ("") 或 null 不能判断空格
2.StringUtils.isBlank 方法检查字符串是否为空格、空 ("") 或 null
3.StringUtils.chomp("abc\r\n");//---"abc"  去除字符串中的"\n", "\r", or "\r\n"
4.StringUtils.deleteWhitespace("   ab  c  "); 删除字符串中的梭有空格
5.StringUtils.equalsIgnoreCase("ss", "Ss");  //不区分大小写--结果是true
6.StringUtils.equals("abc", "abc");// //判断两字符串是否相同  可以判断null，都为null则相等
7.StringUtils.split("a..b.c", '.');//---["a", "b", "c"] 分割字符串
8.截取字符串
  StringUtils.substring("abcd", 2);//---"cd"
  StringUtils.substring("abcdef", 2, 4);//---"cd"
9.left、right从左(右)开始截取n位字符
  StringUtils.left("abc", 2);//---"ab"
```

# day 5
###1.项目修改不需要重新启动程序
```
快捷键：ctrl + shift + F1
或
右键类文件，build model ${modelName}
```
###2.整理线程池基本用法。

###3.整理线程新建方法
```
new Thread(() -> {
.....
}).start();
```

###4.整理线程锁ReentrantLock()的基本用法。

###5.重新整理Async实现异步方法
```
@Async标记的类和调用类不能在同一个类中，否则不生效。
```
# day 6
```
一.整合filter过滤器相关知识点

1.实现Filter接口，用@WebFilter注解，指定拦截路径以及一些参数，同时需要在启动类使用@ServletComponentScan扫描带@WebFilter、@WebServlet、@WebListener并将帮我们注入bean
2.实现类加@WebFilter(filterName = "testFilter",urlPatterns = {"/test"})
3.默认不通过，返回状态码200，没有任何返回结果，通过response.setstatus设置返回状态，并手动抛出异常，结合统一异常处理，实现鉴权失败的异常处理。
```

```
二.补充Aop切面相关知识点

1.添加依赖 spring-boot-starter-aop
2.自定义切面注解
3.切面类添加@Aspect@Component，切面类方法通过@Pointcut("@annotation(easy.project.springbootaop.MyAnnotation)")指定切入点。
4.切面类方法通过@Around，@Before，@After等指定切面过程中的对应操作。
```

