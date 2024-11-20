package edu.whut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whut.pojo.User;
import edu.whut.service.UserService;
import edu.whut.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author wunder
* @description 针对表【user(用户信息表)】的数据库操作Service实现
* @createDate 2024-11-20 11:07:39
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




