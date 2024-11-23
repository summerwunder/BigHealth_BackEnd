package edu.whut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whut.domain.pojo.UserCheckUser;
import edu.whut.service.UserCheckUserService;
import edu.whut.mapper.UserCheckUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author wunder
* @description 针对表【user_check_user(用户与体检人对应关系表)】的数据库操作Service实现
* @createDate 2024-11-23 16:27:54
*/
@Service
public class UserCheckUserServiceImpl extends ServiceImpl<UserCheckUserMapper, UserCheckUser>
    implements UserCheckUserService{

    @Autowired
    private UserCheckUserMapper userCheckUserMapper;
    @Override
    public Long getUserIdByCheckUserId(Long id) {
        LambdaQueryWrapper<UserCheckUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserCheckUser::getCheckUserId, id);
        return userCheckUserMapper.selectOne(queryWrapper).getUserId();
    }
}




