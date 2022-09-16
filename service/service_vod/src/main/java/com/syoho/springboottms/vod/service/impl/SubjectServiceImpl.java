package com.syoho.springboottms.vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.syoho.springboottms.model.vod.Subject;
import com.syoho.springboottms.vod.mapper.SubjectMapper;
import com.syoho.springboottms.vod.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author syoho
 * @since 2022-09-16
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

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

   //判断是否有下一层
    private boolean isChildren(Long subjectId) {
       QueryWrapper<Subject> wrapper = new QueryWrapper<>();
       wrapper.eq("parent_id",subjectId);
       Integer count = baseMapper.selectCount(wrapper);
       return count>0;
    }


}
