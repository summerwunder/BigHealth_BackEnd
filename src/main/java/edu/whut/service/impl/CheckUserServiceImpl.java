package edu.whut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whut.pojo.CheckUser;
import edu.whut.service.CheckUserService;
import edu.whut.mapper.CheckUserMapper;
import org.springframework.stereotype.Service;

/**
* @author wunder
* @description 针对表【check_user(体检人信息表)】的数据库操作Service实现
* @createDate 2024-11-21 15:22:25
*/
@Service
public class CheckUserServiceImpl extends ServiceImpl<CheckUserMapper, CheckUser>
    implements CheckUserService{

}




