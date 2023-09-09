package main.blog.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import main.blog.dto.admin.*;
import main.blog.entity.Admin;
import main.blog.entity.Dept;
import main.blog.mapper.DeptMapper;
import main.blog.service.DeptService;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService
{
    @Resource
    private HttpSession session;
    @Resource
    private DeptMapper deptMapper;

    @Override
    public PageInfo<Dept> getDeptPage(DeptSearchDTO dto)
    {
        PageHelper.startPage(dto.getPage(), dto.getLimit());
        List<Dept> list = deptMapper.getDeptPage(dto);
        return new PageInfo<>(list);
    }

    @Override
    public Dept getDeptInfo(Integer deptId)
    {
        return deptMapper.getDeptInfo(deptId);
    }

    @Override
    public Boolean updateDeptStatus(StatusDTO dto)
    {
        return deptMapper.updateDeptStatus(dto);
    }

    @Override
    public Boolean insertDept(DeptSaveDTO dto)
    {
        Admin admin = (Admin) session.getAttribute("admin");

        Dept dept = new Dept();
        BeanCopier.create(DeptSaveDTO.class, Dept.class, false).copy(dto, dept,  null);
        dept.setCreateBy(admin.getUsername());
        dept.setUpdateBy(admin.getUsername());
        dept.setDeptCode(this.generateDeptCode());
        return deptMapper.insertDept(dept);
    }

    @Override
    public Boolean updateDept(DeptSaveDTO dto)
    {
        Admin admin = (Admin) session.getAttribute("admin");

        Dept dept = new Dept();
        BeanCopier.create(DeptSaveDTO.class, Dept.class, false).copy(dto, dept,  null);
        dept.setUpdateBy(admin.getUsername());
        return deptMapper.updateDept(dept);
    }

    @Override
    public Boolean deleteDept(Integer deptId)
    {
        return deptMapper.deleteDept(deptId);
    }

    @Override
    public List<Tree<String>> getTreeDeptList(TreeSearchDTO dto)
    {
        List<Dept> deptList = deptMapper.getDeptPage(DeptSearchDTO.builder().status(1).build());
        List<TreeNode<String>> nodeList = CollUtil.newArrayList();
        for (Dept dept: deptList)
        {
            HashMap map = new HashMap();
            map.put("spread", true);
            map.put("field", dto.getField());
            map.put("title", dept.getDeptName());
            nodeList.add(new TreeNode<>(dept.getDeptId().toString(), dept.getPid().toString(), dept.getDeptName(), dept.getSort()).setExtra(map));
        }
        List<Tree<String>> treeList = TreeUtil.build(nodeList, "0");
        return treeList;
    }

    /**
     * 生成部门
     * @return
     */
    private String generateDeptCode()
    {
        String deptCode = "";
        Dept systemDept = deptMapper.getLastDept();
        if (ObjectUtil.isNotNull(systemDept))
        {
            String[] numbers = systemDept.getDeptCode().split("D");
            DecimalFormat decimalFormat = new DecimalFormat("00000");
            deptCode = decimalFormat.format(Integer.parseInt(numbers[1]) + 1);
        }else{
            deptCode = "00001";
        }
        return "D" + deptCode;
    }
}
