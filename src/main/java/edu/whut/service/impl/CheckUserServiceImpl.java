package edu.whut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whut.dto.CheckUserDTO;
import edu.whut.pojo.CheckUser;
import edu.whut.pojo.User;
import edu.whut.service.CheckUserService;
import edu.whut.mapper.CheckUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
* @author wunder
* @description 针对表【check_user(体检人信息表)】的数据库操作Service实现
* @createDate 2024-11-21 15:22:25
*/
@Service
public class CheckUserServiceImpl extends ServiceImpl<CheckUserMapper, CheckUser>
    implements CheckUserService{
    @Autowired
    private CheckUserMapper checkUserMapper;

    @Override
    public IPage<CheckUserDTO> getCheckUserList(int page, int size, String name, String gender, String phone){
        IPage<CheckUser> checkUserPage=new Page<>(page,size);
        // 构造查询条件
        LambdaQueryWrapper<CheckUser> queryWrapper = new LambdaQueryWrapper<>();
        if (name != null && !name.trim().isEmpty()) {
            queryWrapper.like(CheckUser::getName, name);
        }
        if (gender != null && !gender.trim().isEmpty()) {
            queryWrapper.eq(CheckUser::getGender, gender);
        }
        if (phone != null && !phone.trim().isEmpty()) {
            queryWrapper.like(CheckUser::getPhone, phone);
        }

        // 查询数据库
        IPage<CheckUser> userPage = checkUserMapper.selectPage(checkUserPage, queryWrapper);

        // 将实体类转换为 DTO
        IPage<CheckUserDTO> dtoPage = new Page<>();
        dtoPage.setCurrent(userPage.getCurrent());
        dtoPage.setSize(userPage.getSize());
        dtoPage.setTotal(userPage.getTotal());
        dtoPage.setRecords(
                userPage.getRecords().stream().map(this::convertToDTO).collect(Collectors.toList())
        );

        return dtoPage;
    }

    /**
     * 将 CheckUser 实体类转换为 CheckUserDTO
     * @param checkUser 实体类
     * @return DTO
     */
    private CheckUserDTO convertToDTO(CheckUser checkUser) {
        CheckUserDTO dto = new CheckUserDTO();
        dto.setId(checkUser.getId());
        dto.setName(checkUser.getName());
        dto.setGender(checkUser.getGender().toString());
        dto.setPhone(checkUser.getPhone());
        dto.setIdCard(checkUser.getIdCard());

        dto.setCheckCount(0); // 默认体检次数为 0，可根据业务逻辑调整
        return dto;
    }
}




