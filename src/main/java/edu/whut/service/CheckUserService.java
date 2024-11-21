package edu.whut.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whut.dto.CheckUserDTO;
import edu.whut.pojo.CheckUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author wunder
* @description 针对表【check_user(体检人信息表)】的数据库操作Service
* @createDate 2024-11-21 15:22:25
*/
public interface CheckUserService extends IService<CheckUser> {

    public IPage<CheckUserDTO> getCheckUserList(int page, int size, String name, String gender, String phone);
}
