package edu.whut.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import edu.whut.mapper.UserMapper;
import edu.whut.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

/**
* @author wunder
* @description 针对表【user(用户信息表)】的数据库操作Service
* @createDate 2024-11-20 11:07:39
*/
public interface UserService extends IService<User> {

    public IPage<User> getAllUsers
            (int page, int size,String username,Object gender, String phone);
}
