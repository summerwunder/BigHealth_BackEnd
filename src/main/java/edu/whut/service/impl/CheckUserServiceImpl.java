package edu.whut.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whut.domain.dto.CheckUserAddDTO;
import edu.whut.domain.dto.CheckUserDTO;
import edu.whut.mapper.RecordsMapper;
import edu.whut.mapper.UserCheckUserMapper;
import edu.whut.domain.pojo.CheckUser;
import edu.whut.domain.pojo.UserCheckUser;
import edu.whut.service.CheckUserService;
import edu.whut.mapper.CheckUserMapper;
import edu.whut.domain.vo.RecordDetailsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author wunder
* @description 针对表【check_user(体检人信息表)】的数据库操作Service实现
* @createDate 2024-11-21 15:22:25
*/
@Service
@Slf4j
public class CheckUserServiceImpl extends ServiceImpl<CheckUserMapper, CheckUser>
    implements CheckUserService{
    @Autowired
    private CheckUserMapper checkUserMapper;

    @Autowired
    private RecordsMapper recordsMapper;

    @Autowired
    private UserCheckUserMapper userCheckUserMapper;
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

        List<CheckUserDTO> dtoList = userPage.getRecords().stream().map(user -> {
            CheckUserDTO dto = convertToDTO(user);
            // 查询该用户的预约次数
            Integer checkCount = recordsMapper.countByCheckUserId(user.getId());
            dto.setCheckCount(checkCount != null ? checkCount : 0);
            return dto;
        }).collect(Collectors.toList());

        dtoPage.setRecords(dtoList);
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

    /**
     * 新增体检人
     * @param checkUserDTO 前端传递的 DTO
     * @return 是否新增成功
     */
    public boolean addCheckUser(CheckUserAddDTO checkUserDTO,Integer userId) {
        CheckUser checkUser = new CheckUser();
        BeanUtils.copyProperties(checkUserDTO, checkUser); // DTO 转实体类
        checkUser.setCreateTime(new Date());
        checkUser.setUpdateTime(new Date());
        int inserted = checkUserMapper.insert(checkUser);
        if(inserted < 0){
            return false;
        }
        // 获取生成的主键 ID（MyBatis 会将生成的 ID 设置到实体类的主键字段中）
        Integer checkUserId = checkUser.getId();
        UserCheckUser userCheckUser = new UserCheckUser();
        userCheckUser.setCheckUserId(Long.valueOf(checkUserId));
        userCheckUser.setUserId(Long.valueOf(userId));

        userCheckUserMapper.insert(userCheckUser);
        return inserted > 0;
    }

    public boolean deleteCheckUser(Integer id) {
        CheckUser existingUser = checkUserMapper.selectById(id);
        if (existingUser == null) {
            return false; // 用户不存在
        }
        // 使用逻辑删除
        return checkUserMapper.deleteById(id) > 0;
    }

    @Override
    public boolean updateCheckUser(CheckUserAddDTO user, Integer id) {
        CheckUser checkUser = checkUserMapper.selectById(id);
        if(ObjectUtil.isNotNull(checkUser)){
            // 拷贝属性到现有用户对象
            checkUser.setName(user.getName());
            checkUser.setGender(user.getGender());
            checkUser.setPhone(user.getPhone());
            checkUser.setIdCard(user.getIdCard());
            checkUser.setUpdateTime(new Date());
            log.info("{}",checkUser);
            return checkUserMapper.updateById(checkUser) > 0;
        }
        return false;
    }

    @Override
    public Map<String, Object> getCheckUserDetails(Long id) {
        CheckUser userInfo = checkUserMapper.selectById(id);

        // Fetch associated records
        List<RecordDetailsVO> details = recordsMapper.fetchDetailsByCheckUserId(id);
        // Calculate total records
        int total = details.size();

        // Prepare result
        Map<String, Object> result = new HashMap<>();
        result.put("userInfo", userInfo);
        result.put("details", details);
        result.put("total", total);

        return result;
    }

    @Override
    public List<CheckUser> getCheckUsersByUserId(Long userId) {
        // Step 1: 查询 user_check_user 表中所有关联体检人ID
        LambdaQueryWrapper<UserCheckUser> userCheckUserQuery = new LambdaQueryWrapper<>();
        userCheckUserQuery.eq(UserCheckUser::getUserId, userId)
                .eq(UserCheckUser::getIsDeleted, 0); // 逻辑未删除
        List<Long> checkUserIds = userCheckUserMapper.selectList(userCheckUserQuery)
                .stream()
                .map(UserCheckUser::getCheckUserId)
                .collect(Collectors.toList());

        if (checkUserIds.isEmpty()) {
            return List.of(); // 无关联体检人，返回空列表
        }

        // Step 2: 查询 check_user 表中的体检人详细信息
        LambdaQueryWrapper<CheckUser> checkUserQuery = new LambdaQueryWrapper<>();
        checkUserQuery.in(CheckUser::getId, checkUserIds)
                .eq(CheckUser::getIsDeleted, 0); // 逻辑未删除
        return checkUserMapper.selectList(checkUserQuery);
    }

}




