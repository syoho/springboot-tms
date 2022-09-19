package com.syoho.springboottms.vod.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.syoho.springboottms.exception.CustomException;
import com.syoho.springboottms.model.vod.Subject;
import com.syoho.springboottms.vo.vod.SubjectEeVo;
import com.syoho.springboottms.vod.listener.SubjectListener;
import com.syoho.springboottms.vod.mapper.SubjectMapper;
import com.syoho.springboottms.vod.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author syoho
 * @since 2022-09-16
 */

//实现类，实现方法

@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    //注入对象
    @Autowired
    private SubjectListener subjectListener;

    //课程分类接口
    //懒加载，每次查询一层数据
   public List<Subject> selectSubjectList(Long id){

       //SELECT * FROM SUBJECT WHERE parent_id = 0
       QueryWrapper<Subject> wrapper= new QueryWrapper<>();
       wrapper.eq("parent_id",id);
       List<Subject> subjectList = baseMapper.selectList(wrapper);

       //subjectList遍历，得到每个subject对象，判断是否有下层对象
       for (Subject subject:subjectList) {
           //获取subject的id
           Long subjectId = subject.getId();
           //查询
           boolean isChild = this.isChildren(subjectId);
           //封装到对象
           subject.setHasChildren(isChild);
       }
       return subjectList;
   }

   //课程分类导出
    @Override
    public void exportData(HttpServletResponse response) {

        try {
            //设置下载信息
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");

            // URLEncoder.encode可以防止中文乱码，与easyexcel无关
            String fileName = URLEncoder.encode("课程分类", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");

            //查询课程分类表所有数据
            List<Subject> subjectList = baseMapper.selectList(null);

            //List<Subject> --->  List<SubjectEeVo>
            List<SubjectEeVo> subjectEeVoList = new ArrayList<>();
            for (Subject subject: subjectList) {
                SubjectEeVo subjectEeVo = new SubjectEeVo();
//                subjectEeVo.setId(subject.getId());
//                subjectEeVo.setParentId(subject.getParentId());
                BeanUtils.copyProperties(subject,subjectEeVo); //一个对象复制到另一个对象
                subjectEeVoList.add(subjectEeVo);
            }

            //EasyExcel写操作
            EasyExcel.write(response.getOutputStream(), SubjectEeVo.class)
                    .sheet("课程分类")
                    .doWrite(subjectEeVoList);
        }catch(Exception e) {
            throw new CustomException(20001,"导出失败");
        }

    }

    //课程分类导入
    //需要监听器
    @Override
    public void importData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),
                    SubjectEeVo.class,
                    subjectListener).sheet().doRead();
        } catch (IOException e) {
            throw new CustomException(20001,"导入失败");
        }
    }


    //判断是否有下一层
    private boolean isChildren(Long subjectId) {
       QueryWrapper<Subject> wrapper = new QueryWrapper<>();
       wrapper.eq("parent_id",subjectId);
       Integer count = baseMapper.selectCount(wrapper);
       return count>0;
    }


}
