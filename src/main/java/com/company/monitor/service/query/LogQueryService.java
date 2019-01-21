package com.company.monitor.service.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.company.monitor.entity.Log;
import com.company.monitor.mapper.LogMapper;
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
 * @date 2018-11-24
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LogQueryService {

    @Autowired
    private LogMapper logMapper;

    public Page queryAll(Log log, Pageable pageable) {

        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Log> page =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<Log>(pageable.getPageNumber(), pageable.getPageSize());
        QueryWrapper<Log> queryWrapper = new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(log.getUsername())) {
            queryWrapper.like("username", log.getUsername());
        }
        if (!ObjectUtils.isEmpty(log.getLogType())) {
            queryWrapper.eq("logType", log.getLogType());
        }
        logMapper.selectPage(page, queryWrapper);

        Page<Log> pageRet = new PageImpl<>(
                page.getRecords(),
                pageable,
                page.getTotal());
        return pageRet;
    }

//    public List queryAll(Log log) {
//
//        return logMapper.findAll(new Spec(logging));
//    }
}
