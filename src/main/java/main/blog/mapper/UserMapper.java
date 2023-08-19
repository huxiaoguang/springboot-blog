package main.blog.mapper;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import main.blog.entity.User;

@Repository
public interface UserMapper {

	//获取会员信息
	User infoUser(int id);

	//会员管理
	List<User> listUser(Map<String, Object> param);

	//添加会员
	boolean addUser(User user);

	//删除会员
	boolean deleteUser(int id);

	//更新用户状态
	boolean updateUserStatus(User user);
}
