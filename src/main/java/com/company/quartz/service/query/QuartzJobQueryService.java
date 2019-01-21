package com.company.quartz.service.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.company.quartz.entity.QuartzJob;
import com.company.quartz.mapper.QuartzJobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
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
@CacheConfig(cacheNames = "quartzJob")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class QuartzJobQueryService {

    @Autowired
    private QuartzJobMapper quartzJobMapper;

    @Cacheable(keyGenerator = "keyGenerator")
    public Page queryAll(QuartzJob quartzJob, Pageable pageable){

        com.baomidou.mybatisplus.extension.plugins.pagination.Page<QuartzJob> page =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageable.getPageNumber(), pageable.getPageSize());
        QueryWrapper<QuartzJob> queryWrapper = new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(quartzJob.getJobName())) {
            queryWrapper.like("jobName", quartzJob.getJobName());
        }
        quartzJobMapper.selectPage(page, queryWrapper);

        Page<QuartzJob> pageRet = new PageImpl<>(
                page.getRecords(),
                pageable,
                page.getTotal());
        return pageRet;
    }
}
