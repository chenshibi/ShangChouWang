package com.atguigu.crowd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.AdminExample;
import com.atguigu.crowd.entity.AdminExample.Criteria;
import com.atguigu.crowd.exception.LoginFailedException;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.util.CrowdUtil;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper adminMapper;

	public void saveAdmin(Admin admin) {

		adminMapper.insert(admin);

		// throw new RuntimeException();

	}

	public List<Admin> getAll() {
		return adminMapper.selectByExample(new AdminExample());
	}

	@Override
	public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {
		AdminExample adminExample = new AdminExample();
		Criteria criteria = adminExample.createCriteria();
		criteria.andLoginAcctEqualTo(loginAcct);
		List<Admin> selectByExample = adminMapper.selectByExample(adminExample);
		if (selectByExample == null || selectByExample.size() == 0) {
			throw new LoginFailedException("登录失败！");
		}
		if (selectByExample.size() > 1) {
			throw new LoginFailedException("用户不唯一！");
		}
		Admin admin = selectByExample.get(0);
		String passWord = admin.getUserPswd();
		String md5 = CrowdUtil.md5(userPswd);
		if (!passWord.equals(md5)) {
			throw new LoginFailedException("输入密码不正确！");
		}
		return admin;

	}

}
