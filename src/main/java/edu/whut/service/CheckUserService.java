package edu.whut.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whut.domain.dto.CheckUserAddDTO;
import edu.whut.domain.dto.CheckUserDTO;
import edu.whut.domain.pojo.CheckUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author wunder
* @description 针对表【check_user(体检人信息表)】的数据库操作Service
* @createDate 2024-11-21 15:22:25
*/
public interface CheckUserService extends IService<CheckUser> {

    public IPage<CheckUserDTO> getCheckUserList(int page, int size, String name, String gender, String phone);

    public boolean addCheckUser(CheckUserAddDTO checkUserDTO,Integer userId);

    public boolean deleteCheckUser(Integer id);

    public boolean updateCheckUser(CheckUserAddDTO user, Integer id);

    Map<String, Object> getCheckUserDetails(Long id);

    List<CheckUser> getCheckUsersByUserId(Long userId);
}
