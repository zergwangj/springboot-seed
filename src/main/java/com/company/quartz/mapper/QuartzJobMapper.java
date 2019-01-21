package com.company.quartz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.quartz.entity.QuartzJob;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 张俊辉
 * @since 2019-01-11
 */
public interface QuartzJobMapper extends BaseMapper<QuartzJob> {

    /**
     * 更新状态
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    @Update("update quartz_job set pause = 1 where id = #{id}")
    void updatePause(@Param("id") Long id);

    /**
     * 查询不是启用的任务
     * @return
     */
    @Select("SELECT * FROM quartz_job WHERE pause = 0")
    List<QuartzJob> findByPauseIsFalse();
}
