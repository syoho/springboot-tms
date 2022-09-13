package com.syoho.springboottms.vod.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.syoho.springboottms.vod.mapper")
public class VodConfig {
}
