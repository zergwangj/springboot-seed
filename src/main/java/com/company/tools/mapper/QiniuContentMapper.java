package com.company.tools.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.tools.entity.QiniuContent;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jim wang
 * @since 2019-01-17
 */
public interface QiniuContentMapper extends BaseMapper<QiniuContent> {

    /**
     * 根据key查询
     * @param key
     * @return
     */
    @Select("SELECT * FROM qiniu_content WHERE key = #{key}")
    QiniuContent findByKey(@Param("key") String key);
}
