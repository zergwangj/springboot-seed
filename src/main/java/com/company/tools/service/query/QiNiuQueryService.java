package com.company.tools.service.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.company.tools.mapper.QiniuContentMapper;
import com.company.tools.entity.QiniuContent;
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
 * @date 2018-12-31
 */
@Service
@CacheConfig(cacheNames = "qiNiu")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class QiNiuQueryService {

    @Autowired
    private QiniuContentMapper qiniuContentMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(QiniuContent qiniuContent, Pageable pageable){
        //return PageUtil.toPage(qiniuContentRepository.findAll(new Spec(qiniuContent),pageable));

        com.baomidou.mybatisplus.extension.plugins.pagination.Page<QiniuContent> page =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<QiniuContent>(pageable.getPageNumber(), pageable.getPageSize());
        QueryWrapper<QiniuContent> queryWrapper = new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(qiniuContent.getName())) {
            queryWrapper.like("name", qiniuContent.getName());
        }
        qiniuContentMapper.selectPage(page, queryWrapper);

        Page<QiniuContent> pageRet = new PageImpl<>(
                page.getRecords(),
                pageable,
                page.getTotal());
        return pageRet;
    }

//    class Spec implements Specification<QiniuContent> {
//
//        private QiniuContent qiniuContent;
//
//        public Spec(QiniuContent qiniuContent){
//            this.qiniuContent = qiniuContent;
//        }
//
//        @Override
//        public Predicate toPredicate(Root<QiniuContent> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
//
//            List<Predicate> list = new ArrayList<Predicate>();
//
//            if(!ObjectUtils.isEmpty(qiniuContent.getKey())){
//                /**
//                 * 模糊
//                 */
//                list.add(cb.like(root.get("key").as(String.class),"%"+qiniuContent.getKey()+"%"));
//            }
//
//            Predicate[] p = new Predicate[list.size()];
//            return cb.and(list.toArray(p));
//        }
//    }
}
