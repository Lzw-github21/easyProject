
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