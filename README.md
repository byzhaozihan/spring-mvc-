# springmvc-data-bind

***

* 使用spring mvc中的@Controller注解，就必须要配置<mvc:annotation-driven />，否则org.springframework.web.servlet.DispatcherServlet无法找到控制器并把请求分发到控制器。

> 如果没有<mvc:annotation-driven/>，那么所有的Controller可能就没有解析，所有当有请求时候都没有匹配的处理请求类，
就都去<mvc:default-servlet-handler/>即default servlet处理了。添加上<mvc:annotation-driven/>后，相应的do请求被Controller处理，
而静态资源因为没有相应的Controller就会被default servlet处理。总之没有相应的Controller就会被default servlet处理

* 使用@InitBinder只能对特定的controller类生效，为达到全局的类型转换，可以选择实现接口WebBindingInitializer或者Formatter<Date>还有Converter<String,Date> 。 
但这样一来就无法使用mvc:annotation-driven 了。需要修改dispatcher-servlet.xml配置文件中的annotation-driven,增加属性conversion-service指向新增的bean。
