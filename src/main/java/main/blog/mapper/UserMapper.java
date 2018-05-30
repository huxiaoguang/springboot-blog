package main.blog.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import main.blog.entity.UserBean;

@Repository
public interface UserMapper {
	
	//获取会员信息
	public UserBean infoUser(int id);
	
	//会员管理
	public List<UserBean> listUser(Map<String, Object> param);
	
	//添加会员
	public boolean addUser(UserBean user);
	
	//删除会员
	public boolean deleteUser(int id);
	
	//更新用户状态
	public boolean updateUserStatus(UserBean user);
}
