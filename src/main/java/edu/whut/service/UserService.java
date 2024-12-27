package edu.whut.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import edu.whut.domain.dto.LoginUserDTO;
import edu.whut.domain.dto.UserDTO;
import edu.whut.domain.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author wunder
* @description 针对表【user(用户信息表)】的数据库操作Service
* @createDate 2024-11-20 11:07:39
*/
public interface UserService extends IService<User> {

    public IPage<User> getAllUsers
            (int page, int size,String username,Object gender, String phone);

    public boolean addUser(UserDTO userDTO);

    public User searchUser(Integer id);

    public boolean updateUser(UserDTO user,Integer id);

    public User getUserInfo(LoginUserDTO userLoginDTO);
}
