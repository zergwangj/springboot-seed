package com.company.system.service.impl;

import com.company.core.security.JwtUser;
import com.company.core.utils.JwtTokenUtil;
import com.company.system.mapper.UserMapper;
import com.company.system.service.mapstruct.UserMapStruct;
import com.company.common.exception.BadRequestException;
import com.company.common.exception.EntityExistException;
import com.company.common.exception.EntityNotFoundException;
import com.company.common.utils.ValidationUtil;
import com.company.system.entity.User;
import com.company.system.service.UserService;
import com.company.system.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
 * @author jim
 * @date 2018-11-23
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserMapStruct userMapStruct;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public UserDTO findById(long id) {
        Optional<User> user = Optional.ofNullable(userMapper.selectById(id));
        ValidationUtil.isNull(user,"User","id",id);
        return userMapStruct.toDto(user.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDTO create(User resources) {

        if(userMapper.findByUsername(resources.getUsername()) != null){
            throw new EntityExistException(User.class,"username",resources.getUsername());
        }

        if(userMapper.findByEmail(resources.getEmail())!=null){
            throw new EntityExistException(User.class,"email",resources.getEmail());
        }

        if(resources.getRoles() == null || resources.getRoles().size() == 0){
            throw new BadRequestException("角色不能为空");
        }

        // 默认密码 123456
        resources.setPassword("14e1b600b1fd579f47433b88e8d85291");
        resources.setAvatar("https://i.loli.net/2018/12/06/5c08894d8de21.jpg");
        userMapper.insert(resources);
        return userMapStruct.toDto(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(User resources) {

        Optional<User> userOptional = Optional.ofNullable(userMapper.selectById(resources.getId()));
        ValidationUtil.isNull(userOptional,"User","id", resources.getId());

        User user = userOptional.get();

        /**
         * 根据实际需求修改
         */
        if(user.getId().equals(1L)){
            throw new BadRequestException("该账号不能被修改");
        }

        User user1 = userMapper.findByUsername(user.getUsername());
        User user2 = userMapper.findByEmail(user.getEmail());

        if(resources.getRoles() == null || resources.getRoles().size() == 0){
            throw new BadRequestException("角色不能为空");
        }

        if(user1 !=null&&!user.getId().equals(user1.getId())){
            throw new EntityExistException(User.class,"username",resources.getUsername());
        }

        if(user2!=null&&!user.getId().equals(user2.getId())){
            throw new EntityExistException(User.class,"email",resources.getEmail());
        }

        user.setUsername(resources.getUsername());
        user.setEmail(resources.getEmail());
        user.setEnabled(resources.getEnabled());
        user.setRoles(resources.getRoles());

        userMapper.updateById(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {

        /**
         * 根据实际需求修改
         */
        if(id.equals(1L)){
            throw new BadRequestException("该账号不能被删除");
        }
        userMapper.deleteById(id);
    }

    @Override
    public User findByName(String userName) {
        User user = null;
        if(ValidationUtil.isEmail(userName)){
            user = userMapper.findByEmail(userName);
        } else {
            user = userMapper.findByUsername(userName);
        }

        if (user == null) {
            throw new EntityNotFoundException(User.class, "name", userName);
        } else {
            return user;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePass(JwtUser jwtUser, String pass) {
        userMapper.updatePass(jwtUser.getId(),pass);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAvatar(JwtUser jwtUser, String url) {
        userMapper.updateAvatar(jwtUser.getId(),url);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateEmail(JwtUser jwtUser, String email) {
        userMapper.updateEmail(jwtUser.getId(),email);
    }
}
