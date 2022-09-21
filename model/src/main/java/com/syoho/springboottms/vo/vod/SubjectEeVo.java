package com.syoho.springboottms.vo.vod;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * <p>
 * Dict
 * </p>
 *
 * @author qy
 */

//Excel导入导出
@Data
public class SubjectEeVo {

	@ExcelProperty(value = "ID" ,index = 0)
	private Long id;

	//表头
	@ExcelProperty(value = "Course" ,index = 1)
	private String title;

	@ExcelProperty(value = "Parent ID" ,index = 2)
	private Long parentId;

	@ExcelProperty(value = "Sort" ,index = 3)
	private Integer sort;


}

