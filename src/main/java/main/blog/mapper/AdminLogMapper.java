package main.blog.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import main.blog.entity.AdminLogBean;

@Repository
public interface AdminLogMapper {
	
	//管理员操作日志
	public List<AdminLogBean> listAdminLog(Map<String, Object> param);
		
	//删除管理员日志
	public boolean deleteAdminLog(int id);
}
