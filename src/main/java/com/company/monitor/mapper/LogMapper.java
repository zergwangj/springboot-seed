package com.company.monitor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.monitor.entity.Log;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 张俊辉
 * @since 2019-01-11
 */
public interface LogMapper extends BaseMapper<Log> {

    /**
     * 获取一个时间段的IP记录
     * @param startDate
     * @param endDate
     * @return
     */
    @Select("SELECT count(*) FROM (SELECT requestIp FROM log WHERE createTime BETWEEN #{startDate} AND #{endDate} GROUP BY requestIp) AS s")
    Long findIp(@Param("startDate") String startDate, @Param("endDate") String endDate);
}
