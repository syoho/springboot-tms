package com.syoho.springboottms.vod.service;

import com.syoho.springboottms.model.vod.Subject;
import com.syoho.springboottms.model.vod.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author syoho
 * @since 2022-09-16
 */

//创建方法
public interface SubjectService extends IService<Subject> {



    //课程分类列表
    //懒加载，每次查询一层数据
    List<Subject> selectSubjectList(Long id);

    //课程分类导出
    void exportData(HttpServletResponse response);

    //课程分类导入
    void importData(MultipartFile file);

}
