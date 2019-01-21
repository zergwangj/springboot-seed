package com.company.quartz.service.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.company.quartz.entity.QuartzLog;
import com.company.quartz.mapper.QuartzLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

/**
 * @author jim
 * @date 2019-01-07
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class QuartzLogQueryService {

    @Autowired
    private QuartzLogMapper quartzLogMapper;

    public Page queryAll(QuartzLog quartzLog, Pageable pageable){

        com.baomidou.mybatisplus.extension.plugins.pagination.Page<QuartzLog> page =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageable.getPageNumber(), pageable.getPageSize());
        QueryWrapper<QuartzLog> queryWrapper = new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(quartzLog.getJobName())) {
            queryWrapper.like("jobName", quartzLog.getJobName());
        }
        if (!ObjectUtils.isEmpty(quartzLog.getSuccess())) {
            queryWrapper.eq("success", quartzLog.getSuccess() ? "1" : "0");
        }
        quartzLogMapper.selectPage(page, queryWrapper);

        Page<QuartzLog> pageRet = new PageImpl<>(
                page.getRecords(),
                pageable,
                page.getTotal());
        return pageRet;
    }

}
