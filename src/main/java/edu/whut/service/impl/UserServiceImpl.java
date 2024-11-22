package edu.whut.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whut.dto.UserDTO;
import edu.whut.pojo.User;
import edu.whut.service.UserService;
import edu.whut.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
* @author wunder
* @description 针对表【user(用户信息表)】的数据库操作Service实现
* @createDate 2024-11-20 11:07:39
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Override
    public IPage<User> getAllUsers(int page, int size,String username,Object gender, String phone) {
        IPage<User> userPage=new Page<>(page,size);
        // 构造查询条件
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        if (username != null && !username.isEmpty()) {
            queryWrapper.like(User::getRealName, username); // 模糊查询
        }
        if (gender != null && ObjectUtil.isNotEmpty(gender)) {
            queryWrapper.eq(User::getGender, gender); // 精确匹配
        }
        if (phone != null && !phone.isEmpty()) {
            queryWrapper.like(User::getPhone, phone); // 模糊查询
        }

        // 分页查询
        return userMapper.selectPage(userPage, queryWrapper);
    }

    @Override
    public boolean addUser(UserDTO userDTO) {
        // 将 DTO 转换为实体类
        User user = new User();
        user.setNickname(userDTO.getNickname());
        user.setRealName(userDTO.getRealName());
        user.setGender(userDTO.getGender());
        user.setPhone(userDTO.getPhone());
        user.setBirthday(userDTO.getBirthday());
        user.setIdCard(userDTO.getIdCard());
        user.setAddress(userDTO.getAddress());
        user.setIsMember(userDTO.getIsMember());
        user.setIsRecommender(userDTO.getIsRecommender());
        user.setPoints(0); // 初始积分为 0

        // 插入数据库
        return userMapper.insert(user) > 0;
    }

    @Override
    public User searchUser(Integer id){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId, id);
        return userMapper.selectOne(queryWrapper);
    }

    public boolean updateUser(UserDTO user,Integer id) {
        // 校验用户是否存在
        User existingUser = userMapper.selectById(id);
        if (existingUser == null) {
            return false; // 用户不存在
        }
        // 拷贝属性到现有用户对象
        BeanUtil.copyProperties(user, existingUser);
        // 设置更新时间
        existingUser.setUpdateTime(LocalDateTime.now());
        return userMapper.updateById(existingUser) > 0; // 返回更新结果
    }
}




