package main.blog.mapper;

import main.blog.dto.admin.DeptSearchDTO;
import main.blog.dto.admin.MenuSearchDTO;
import main.blog.dto.admin.StatusDTO;
import main.blog.entity.Dept;
import main.blog.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptMapper
{
    /**
     * 部门管理
     * @param dto
     * @return
     */
    List<Dept> getDeptPage(DeptSearchDTO dto);

    /**
     * 部门查询
     * @param deptId
     * @return
     */
    Dept getDeptInfo(Integer deptId);

    /**
     * 新增部门
     * @param detp
     * @return
     */
    Boolean insertDept(Dept detp);

    /**
     * 编辑部门
     * @param detp
     * @return
     */
    Boolean updateDept(Dept detp);

    /**
     * 获取最新编码
     * @return
     */
    Dept getLastDept();

    /**
     * 删除部门
     * @return
     */
    Boolean deleteDept(Integer deptId);

    /**
     * 更新部门状态
     * @param dto
     * @return
     */
    Boolean updateDeptStatus(StatusDTO dto);
}
