package com.syoho.springboottms.vod.controller;


import com.syoho.springboottms.model.vod.Subject;
import com.syoho.springboottms.result.Result;
import com.syoho.springboottms.vod.service.SubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author syoho
 * @since 2022-09-16
 */
@CrossOrigin //跨域解决
@RestController
@RequestMapping("/admin/vod/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    //课程分类接口
    //懒加载，每次查询一层数据
    @GetMapping("getChildSubject/{id}")
    public Result getChildSubject(@PathVariable Long id) {
        List<Subject> list = subjectService.selectSubjectList(id);
        return Result.ok(list);
    }










}

