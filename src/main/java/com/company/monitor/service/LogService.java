package com.company.monitor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.monitor.entity.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

/**
 * @author jim
 * @date 2018-11-24
 */
public interface LogService extends IService<Log> {

    /**
     * 新增日志
     * @param joinPoint
     * @param log
     */
    @Async
    void save(ProceedingJoinPoint joinPoint, Log log);
}
