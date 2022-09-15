package com.syoho.springboottms.vod.controller;


import com.syoho.springboottms.model.vod.Teacher;
import com.syoho.springboottms.result.Result;
import com.syoho.springboottms.vo.vod.TeacherQueryVo;
import com.syoho.springboottms.vod.service.TeacherService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author syoho
 * @since 2022-09-13
 */
@RestController
@RequestMapping("/admin/vod/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

/*    //1.查询所有
    @GetMapping("findAll")
    public List<Teacher> findAllTeacher(){
        List<Teacher> list = teacherService.list();
        return list;
    }*/

    //1.查询所有
    @GetMapping("findAll")
    public Result findAllTeacher(){
        List<Teacher> list = teacherService.list();
        return Result.ok(list).message("查询成功");
    }

    //2.逻辑删除
    @DeleteMapping("remove/{id}")
    public Result removeTeacher(@PathVariable Long id){
        boolean isSuccess = teacherService.removeById(id);
        if(isSuccess){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

  /*  //3.分页查询
    @PostMapping("findQueryPage/{current}/{limit}")
    public Result findPage(@PathVariable long current,
                           @PathVariable long limit,
                           @RequestBody(required = false) TeacherQueryVo teacherQueryVo){//@RequestBody以json提交；与Post一起使用
        //创建分页对象
        Page<Teacher> pageParam = new Page<>(current,limit);

        //判断teacherQueryVo对象是否为空
        if ( teacherQueryVo == null){

            //查询全部
            IPage<Teacher> pageModel = teacherService.page(pageParam,null);//.var
            return Result.ok(pageModel);

        } else{

            //获取条件值，进行非空判断，条件封装
            String name = teacherQueryVo.getName();
            Integer level = teacherQueryVo.getLevel();
            String joinDateBegin = teacherQueryVo.getJoinDateBegin();
            String joinDateEnd = teacherQueryVo.getJoinDateEnd();

            //进行非空判断，条件封装
            QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
            if (!StringUtils.isEmpty(name)){
                wrapper.like("name",name);
            }
            if (!StringUtils.isEmpty(name)){
                wrapper.eq("level",level);
            }
            if(!StringUtils.isEmpty(joinDateBegin)) {
                wrapper.ge("join_date",joinDateBegin);//>=
            }
            if(!StringUtils.isEmpty(joinDateEnd)) {
                wrapper.le("join_date",joinDateEnd);//<=
            }

            //调用方法分页查询

            IPage<Teacher> pageModel = teacherService.page(pageParam, wrapper);

            //返回
            return Result.ok(pageModel);
        }
    }
*/

    //3 条件查询分页
    @ApiOperation("条件查询分页")
    @PostMapping("findQueryPage/{current}/{limit}")
    public Result findPage(@PathVariable long current,
                           @PathVariable long limit,
                           @RequestBody(required = false) TeacherQueryVo teacherQueryVo) {
        //创建page对象
        Page<Teacher> pageParam = new Page<>(current,limit);

        //获取条件值
        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        String joinDateBegin = teacherQueryVo.getJoinDateBegin();
        String joinDateEnd = teacherQueryVo.getJoinDateEnd();

        //进行非空判断，条件封装
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.like("name",name);
        wrapper.eq("level",level);
        wrapper.ge("join_date",joinDateBegin);
        wrapper.le("join_date",joinDateEnd);

        //调用方法分页查询
        IPage<Teacher> pageModel = teacherService.page(pageParam, wrapper);
        //返回
        return Result.ok(pageModel);
    }





      /*  //判断teacherQueryVo对象是否为空
        if(teacherQueryVo == null) {//查询全部
            IPage<Teacher> pageModel =
                    teacherService.page(pageParam,null);
            return Result.ok(pageModel);
        } else {
            //获取条件值
            String name = teacherQueryVo.getName();
            Integer level = teacherQueryVo.getLevel();
            String joinDateBegin = teacherQueryVo.getJoinDateBegin();
            String joinDateEnd = teacherQueryVo.getJoinDateEnd();

            //进行非空判断，条件封装
            QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
            wrapper.like("name",name);
            wrapper.eq("level",level);
            wrapper.ge("join_date",joinDateBegin);
            wrapper.le("join_date",joinDateEnd);

*//*            if(!ObjectUtils.isEmpty(name)) {
                wrapper.like("name",name);
            }
            if(!ObjectUtils.isEmpty(level)) {
                wrapper.eq("level",level);
            }
            if(!ObjectUtils.isEmpty(joinDateBegin)) {
                wrapper.ge("join_date",joinDateBegin);
            }
            if(!ObjectUtils.isEmpty(joinDateEnd)) {
                wrapper.le("join_date",joinDateEnd);
            }*//*

*/





    //4.添加讲师
    @PostMapping("saveTeacher")
    public Result saveTeacher(@RequestBody Teacher teacher){
        boolean isSuccess = teacherService.save(teacher);
        if(isSuccess){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    //5.修改讲师-根据id查询
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        Teacher teacher = teacherService.getById(id);
        return Result.ok(teacher);
    }

    //6.修改讲师-最终实现
    @PostMapping("updateTeacher")
    public Result updateTeacher(@RequestBody Teacher teacher){
        boolean isSuccess = teacherService.updateById(teacher);
        if(isSuccess){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    //7.批量删除
    @DeleteMapping("removeBatch")
    public Result removeBatch(@RequestBody List<Long> idList){
        boolean isSuccess = teacherService.removeByIds(idList);
        if(isSuccess){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }


}

