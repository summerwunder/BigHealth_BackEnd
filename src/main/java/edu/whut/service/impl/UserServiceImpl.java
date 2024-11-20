package edu.whut.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whut.pojo.User;
import edu.whut.service.UserService;
import edu.whut.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            queryWrapper.like(User::getNickname, username); // 模糊查询
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
}




