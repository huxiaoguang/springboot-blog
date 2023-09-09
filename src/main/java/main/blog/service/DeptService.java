package main.blog.service;

import cn.hutool.core.lang.tree.Tree;
import com.github.pagehelper.PageInfo;
import main.blog.dto.admin.*;
import main.blog.entity.Dept;
import java.util.List;

public interface DeptService
{
    /**
     * 获取部门信息
     * @return
     */
    PageInfo<Dept> getDeptPage(DeptSearchDTO dto);

    List<Dept> getDeptList();

    Dept getDeptInfo(Integer deptId);

    /**
     * 更新部门状态
     * @return
     */
    Boolean updateDeptStatus(StatusDTO dto);

    /**
     * 新增部门
     * @return
     */
    Boolean insertDept(DeptSaveDTO dto);

    /**
     * 编辑部门
     * @return
     */
    Boolean updateDept(DeptSaveDTO detp);

    /**
     * 删除部门
     * @return
     */
    Boolean deleteDept(Integer deptId);

    List<Tree<String>> getTreeDeptList(TreeSearchDTO dto);
}
