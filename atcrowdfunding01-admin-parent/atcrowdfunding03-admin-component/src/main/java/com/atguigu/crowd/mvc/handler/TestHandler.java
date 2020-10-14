package com.atguigu.crowd.mvc.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.ParamData;
import com.atguigu.crowd.entity.Student;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.util.CrowdUtil;
import com.atguigu.crowd.util.ResultEntity;
import com.github.pagehelper.PageInfo;

@Controller
public class TestHandler {
	
	@Autowired
	private AdminService adminService;
	
	private Logger logger = LoggerFactory.getLogger(TestHandler.class);
	
	@RequestMapping("/login.html")
	public String Login() {
		return "admin-login";
	}
	
	@ResponseBody
	@RequestMapping("/send/compose/object.json")
	public ResultEntity<Student> testReceiveComposeObject(@RequestBody Student student, HttpServletRequest request) {
		
		boolean judgeResult = CrowdUtil.judgeRequestType(request);
		
		logger.info("judgeResult="+judgeResult);
		
		logger.info(student.toString());
		
		// 将“查询”到的Student对象封装到ResultEntity中返回
		ResultEntity<Student> resultEntity = ResultEntity.successWithData(student);
		
		String a = null;
		
		System.out.println("**************"+resultEntity+"*************");
		
		return resultEntity;
	}
	
	@ResponseBody
	@RequestMapping("/send/array/three.html")
	public String testReceiveArrayThree(@RequestBody List<Integer> array) {
		
		for (Integer number : array) {
			logger.info("number="+number);
		}
		
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("/send/array/two.html")
	public String testReceiveArrayTwo(ParamData paramData) {
		
		List<Integer> array = paramData.getArray();
		
		for (Integer number : array) {
			System.out.println("number="+number);
		}
		
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("/send/array/one.html")
	public String testReceiveArrayOne(@RequestParam("array[]") List<Integer> array) {
		
		for (Integer number : array) {
			System.out.println("number="+number);
		}
		
		return "success";
	}
	
	@RequestMapping("/test/ssm.html")
	public String testSsm(ModelMap modelMap, HttpServletRequest request) {
		
		boolean judgeResult = CrowdUtil.judgeRequestType(request);
		
		logger.info("judgeResult="+judgeResult);
		
		List<Admin> adminList = adminService.getAll();
		
		modelMap.addAttribute("adminList", adminList);
		
		int a = 1/0;
		
		return "target";
	}
	
	//登录
	@RequestMapping("/admin/do/login.html")
	public String doLogin(@RequestParam("loginAcct") String loginAcct,@RequestParam("userPswd") String userPswd, HttpSession session) {
		Admin admin = adminService.getAdminByLoginAcct(loginAcct, userPswd);
		session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN, admin);
		return "redirect:/admin/to/main/page.html";
	}
	
	//退出登录
	@RequestMapping("admin/do/logout.html")
	public String doLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/admin/to/login/page.html";
	}
//	
//	@RequestMapping("/admin/get/page.html")
//	public String getPageInfo(@RequestParam(value = "keyword", defaultValue = "") String keyword,
//			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
//			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, ModelMap map) {
//		PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);
//		map.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO, pageInfo);
//		return "admin-page";
//
//	}

}
