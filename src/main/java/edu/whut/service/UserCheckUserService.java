package edu.whut.service;

import edu.whut.pojo.UserCheckUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author wunder
* @description 针对表【user_check_user(用户与体检人对应关系表)】的数据库操作Service
* @createDate 2024-11-23 16:27:54
*/
public interface UserCheckUserService extends IService<UserCheckUser> {

    Long getUserIdByCheckUserId(Long id);
}
