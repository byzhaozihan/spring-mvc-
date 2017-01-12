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
	 * ������������ķ���ֵ�����Responsebody�����������
	 * RequestParamע�����ǰ�˴������������ʱ������һ���ش��ģ�ͬʱ�ñ������age
	 * @return
	 */
	@RequestMapping(value="baseType.do")
	@ResponseBody
	public String baseType(@RequestParam("xage") int age){
		return "age:"+age;
	}
	
	/**
	 * ǰ���int���Ͳ������ݲ���Ϊ�գ�����װ�����Ϊ�գ���ʾnull
	 * @param age
	 * @return
	 */
	//http://localhost:8080/springmvc/baseType2.do?age=10
	@RequestMapping(value="baseType2.do")
	@ResponseBody
	public String baseType2(Integer age){
		return "age:"+age;
	}
	
	//�����
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
	 * initBinderע�����Ƚ��г�ʼ��Ȼ��Ŵ��ݵ�������
	 * 
	 */
	//http://localhost:8080/springmvc/object2.do?user.name=Tom&admin.name=Lucy&age=10
	@RequestMapping(value="object2.do")
	@ResponseBody
	public String object2(User user,Admin admin){
		return user.toString()+" "+admin.toString();
	}
	
	//���û��@InitBinderע��ĸ���������ֱ�Ӵ���user.name��admin.name�����ʾnull
	@InitBinder("user")
	public void initUser(WebDataBinder binder){
		binder.setFieldDefaultPrefix("user.");
	}
	@InitBinder("admin")
	public void initAdmin(WebDataBinder binder){
		binder.setFieldDefaultPrefix("admin.");
	}
	
	/**
	 * List���ϴ��ݲ���
	 */
	@RequestMapping(value="list.do")
	@ResponseBody	
//	List<User> userList����Ч��
//	public String list(List<User> userList){
//		return userList.toString();
//	}
	//��Ҫ�������ռ�����
	//http://localhost:8080/springmvc/list.do?userList[0].name=Tom&userList[1].name=Lucy
	//���ֱ�ӽ�userList[1]��Ϊ[20],��ôsize��Ϊ11��ͬʱ�м�����ݶ�Ϊnull
	public String list(UserListForm userListForm){
		return  "size="+userListForm.getUserList().size()+" "+ userListForm.toString();
	}
	
	/**
	 * Set������ʵ��Ӧ��������������ظ��жϺ��ų��ظ�����дequals��hashcode����
	 * ��������ѧ��������Email����ȣ���̨�ǰ󶨵�һ��������
	 */
	//http://localhost:8080/springmvc/set.do?userSet[0].name=Tom&userSet[1].name=Lucy
	//�������޸�ΪuserSet[20].name��ᱨ���ڽ���set���ݰ󶨵�ʱ��Ҫ��size���г�ʼ��
	@RequestMapping(value="set.do")
	@ResponseBody
	public String set(UserSetForm userSetForm){
		return  "size="+userSetForm.getUserSet().size()+" "+ userSetForm.toString();
	}
	/**
	 * map����
	 */
	//http://localhost:8080/springmvc/map.do?userMap["X"].name=Tom&userMap["X"].age=10&userMap["Y"].name=Lucy
	@RequestMapping(value="map.do")
	@ResponseBody
	public String map(UserMapForm userMapForm){
		return  "size="+userMapForm.getUserMap().size()+" "+ userMapForm.toString();
	}
	
	/**
	 * json��
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
