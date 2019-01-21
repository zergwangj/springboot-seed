package com.company.tools.service.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.company.common.utils.PageUtil;
import com.company.tools.entity.Picture;
import com.company.tools.mapper.PictureMapper;
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
 * @date 2018-12-03
 */
@Service
@CacheConfig(cacheNames = "picture")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PictureQueryService {

    @Autowired
    private PictureMapper pictureMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(Picture picture, Pageable pageable){
        //return PageUtil.toPage(pictureRepository.findAll(new Spec(picture),pageable));

        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Picture> page =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<Picture>(pageable.getPageNumber(), pageable.getPageSize());
        QueryWrapper<Picture> queryWrapper = new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(picture.getFilename())) {
            queryWrapper.like("filename", picture.getFilename());
        }
        pictureMapper.selectPage(page, queryWrapper);

        Page<Picture> pageRet = new PageImpl<>(
                page.getRecords(),
                pageable,
                page.getTotal());
        return pageRet;
    }

//    class Spec implements Specification<Picture> {
//
//        private Picture picture;
//
//        public Spec(Picture picture){
//            this.picture = picture;
//        }
//
//        @Override
//        public Predicate toPredicate(Root<Picture> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
//
//            List<Predicate> list = new ArrayList<Predicate>();
//
//            if(!ObjectUtils.isEmpty(picture.getFilename())){
//                /**
//                 * 模糊
//                 */
//                list.add(cb.like(root.get("filename").as(String.class),"%"+picture.getFilename()+"%"));
//            }
//
//            Predicate[] p = new Predicate[list.size()];
//            return cb.and(list.toArray(p));
//        }
//    }
}
