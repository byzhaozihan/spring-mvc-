package com.imooc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.object.Admin;
import com.imooc.object.User;
import com.imooc.object.UserListForm;
import com.imooc.object.UserMapForm;
import com.imooc.object.UserSetForm;

@Controller
public class TestController {
	/**
	 * @ResponseBody
	 * 代表这个方法的返回值会放在Responsebody这个数据区里
	 * RequestParam注解告诉前端传输这个参数的时候是有一个必传的，同时用别名替代age
	 * @return
	 */
	@RequestMapping(value="baseType.do")
	@ResponseBody
	public String baseType(@RequestParam("xage") int age){
		return "age:"+age;
	}
	
	/**
	 * 前面的int类型参数传递不能为空，而包装类可以为空，显示null
	 * @param age
	 * @return
	 */
	//http://localhost:8080/springmvc/baseType2.do?age=10
	@RequestMapping(value="baseType2.do")
	@ResponseBody
	public String baseType2(Integer age){
		return "age:"+age;
	}
	
	//数组绑定
	//http://localhost:8080/springmvc/array.do?name=Tom&name=Lucy&name=Jim
	@RequestMapping(value="array.do")
	@ResponseBody
	public String array(String[] name){
		StringBuilder sbf = new StringBuilder();
		for (String item : name) {
			sbf.append(item).append(" ");
		}
		return sbf.toString();
	}
	
	//http://localhost:8080/springmvc/object.do?name=Tom&age=10&contactInfo.phone=10086
	@RequestMapping(value="object.do")
	@ResponseBody
	public String object(User user){
		return user.toString();
	}
	
	/**
	 * initBinder注解首先进行初始化然后才传递到方法上
	 * 
	 */
	//http://localhost:8080/springmvc/object2.do?user.name=Tom&admin.name=Lucy&age=10
	@RequestMapping(value="object2.do")
	@ResponseBody
	public String object2(User user,Admin admin){
		return user.toString()+" "+admin.toString();
	}
	
	//如果没有@InitBinder注解的辅助方法，直接传递user.name和admin.name则会显示null
	@InitBinder("user")
	public void initUser(WebDataBinder binder){
		binder.setFieldDefaultPrefix("user.");
	}
	@InitBinder("admin")
	public void initAdmin(WebDataBinder binder){
		binder.setFieldDefaultPrefix("admin.");
	}
	
	/**
	 * List集合传递参数
	 */
	@RequestMapping(value="list.do")
	@ResponseBody	
//	List<User> userList是无效的
//	public String list(List<User> userList){
//		return userList.toString();
//	}
	//需要用数据收集对象
	//http://localhost:8080/springmvc/list.do?userList[0].name=Tom&userList[1].name=Lucy
	//如果直接将userList[1]改为[20],那么size变为11，同时中间的数据都为null
	public String list(UserListForm userListForm){
		return  "size="+userListForm.getUserList().size()+" "+ userListForm.toString();
	}
	
	/**
	 * Set集合在实际应用中是做对象的重复判断和排除重复，重写equals和hashcode方法
	 * 例如两个学生姓名和Email都相等，后台是绑定到一个对象上
	 */
	//http://localhost:8080/springmvc/set.do?userSet[0].name=Tom&userSet[1].name=Lucy
	//如果跨界修改为userSet[20].name则会报错，在进行set数据绑定的时候要对size进行初始化
	@RequestMapping(value="set.do")
	@ResponseBody
	public String set(UserSetForm userSetForm){
		return  "size="+userSetForm.getUserSet().size()+" "+ userSetForm.toString();
	}
	/**
	 * map集合
	 */
	//http://localhost:8080/springmvc/map.do?userMap["X"].name=Tom&userMap["X"].age=10&userMap["Y"].name=Lucy
	@RequestMapping(value="map.do")
	@ResponseBody
	public String map(UserMapForm userMapForm){
		return  "size="+userMapForm.getUserMap().size()+" "+ userMapForm.toString();
	}
	
	/**
	 * json绑定
	 * application/json
	 */
//	{
//	    "name":"Jim",
//	      "age": 16,
//	      "contactInfo":{
//	        "address": "beijing",
//	        "phone": "10010"
//	      }
//	}
	@RequestMapping(value="json.do")
	@ResponseBody
	public String json(@RequestBody User user){
		return  user.toString();
	}
	
	/**
	 * xml
	 * application/xml
	 */
//	<?xml version="1.0" encoding="UTF-8" ?>
//	<admin>
//	    <name>Jim</name>
//	    <age>16</age>
//	</admin>
	@RequestMapping(value="xml.do")
	@ResponseBody
	public String xml(@RequestBody Admin admin){
		return  admin.toString();
	}
	
	@RequestMapping(value="date1.do")
	@ResponseBody
	public String date1(Date date1){
		return  date1.toString();
	}
//	@InitBinder("date1")
//	public void initDate1(WebDataBinder binder){
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
//	}
	
	@RequestMapping(value="date2.do")
	@ResponseBody
	public String date2(Date date2){
		return  date2.toString();
	}
	
	@RequestMapping(value="/book",method = RequestMethod.GET)
	@ResponseBody
	public String book(HttpServletRequest request){
		String contentType = request.getContentType();
		if (contentType == null){
			return "book.txt";
		}else if (contentType.equals("txt")){
			return "book.txt";
		}else if (contentType.equals("html")){
			return "book.html";
		}
		return "book.default";
	}
	
	
	
	
	
	
	
}
