package com.company.monitor.config;

import com.company.monitor.service.VisitsService;
import org.springframework.context.annotation.Configuration;

/**
 * 初始化站点统计
 * @author jim
 */
@Configuration
public class VisitsInitialization {

    public VisitsInitialization(VisitsService visitsService){
        System.out.println("--------------- 初始化站点统计，如果存在今日统计则跳过 ---------------");
        visitsService.save();
    }
}
