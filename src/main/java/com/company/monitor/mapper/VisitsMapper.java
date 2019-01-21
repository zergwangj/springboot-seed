package com.company.monitor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.monitor.entity.Visits;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 张俊辉
 * @since 2019-01-11
 */
public interface VisitsMapper extends BaseMapper<Visits> {

    /**
     * findByDate
     * @param date
     * @return
     */
    @Select("SELECT * FROM visits WHERE date = #{date}")
    Visits findByDate(@Param("date") String date);

    /**
     * 获得一个时间段的记录
     * @param startDate
     * @param endDate
     * @return
     */
    @Select("SELECT * FROM visits WHERE " +
            "createTime BETWEEN #{startDate} AND #{endDate}")
    List<Visits> findAllVisits(@Param("startDate") String startDate, @Param("endDate") String endDate);
}
