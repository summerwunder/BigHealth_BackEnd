package edu.whut.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whut.dto.RecordAddDTO;
import edu.whut.mapper.OrderDetailsMapper;
import edu.whut.pojo.OrderDetails;
import edu.whut.pojo.Records;
import edu.whut.response.PageResult;
import edu.whut.service.RecordsService;
import edu.whut.mapper.RecordsMapper;
import edu.whut.vo.OrdersVO;
import edu.whut.vo.RecordVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author wunder
* @description 针对表【records(预约记录表)】的数据库操作Service实现
* @createDate 2024-11-23 09:27:05
*/
@Service
@Slf4j
public class RecordsServiceImpl extends ServiceImpl<RecordsMapper, Records>
    implements RecordsService{

    @Autowired
    private  RecordsMapper recordsMapper;

    @Autowired
    private OrderDetailsMapper orderDetailsMapper;
    @Override
    public PageResult<RecordVO> getRecordList(String name, String gender, String phone, String date, int page, int size) {
        int offset = (page - 1) * size;
        if(ObjectUtil.isNotEmpty(date)){
            date = date.split("T")[0];
            // 获取最后一位字符并加1
            char lastChar = date.charAt(date.length() - 1);
            int incrementedChar = Character.getNumericValue(lastChar) + 1;
            // 拼接字符串
            date = date.substring(0, date.length() - 1) + incrementedChar;
        }
        List<RecordVO> records = recordsMapper.queryRecords(name, gender, phone, date, size, offset);
        long total = recordsMapper.countRecords(name, gender, phone, date);
        return new PageResult<>(records, total);
    }

    @Override
    public boolean addRecord(RecordAddDTO recordAddDTO) {
        // Step 1: 校验订单详情记录是否存在并未被使用
        LambdaQueryWrapper<OrderDetails> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderDetails::getUserId, recordAddDTO.getCheckUserId());
        queryWrapper.eq(OrderDetails::getProductId, recordAddDTO.getProductId());
        queryWrapper.eq(OrderDetails::getIsUsed, 0); // 未使用
        queryWrapper.eq(OrderDetails::getStatus, "已支付"); // 已支付状态

        OrderDetails orderDetails = orderDetailsMapper.selectOne(queryWrapper);

        if (orderDetails == null) {
            throw new RuntimeException("未找到符合条件的订单详情记录，无法创建预约记录！");
        }

        // Step 2: 更新 OrderDetails 的 is_used 字段为 1
        orderDetails.setIsUsed(1);
        int updated = orderDetailsMapper.updateById(orderDetails);

        if (updated <= 0) {
            throw new RuntimeException("更新订单详情状态失败！");
        }

        // Step 3: 插入预约记录
        Records record = new Records();
        record.setCheckUserId(recordAddDTO.getCheckUserId());
        record.setProductId(recordAddDTO.getProductId());
        record.setAppointmentTime(recordAddDTO.getAppointmentTime());
        record.setStatus("已预约");
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        record.setIsDeleted(0);
        record.setVersion(1);

        int inserted = recordsMapper.insert(record);

        if (inserted <= 0) {
            throw new RuntimeException("新增预约记录失败！");
        }

        return true;
    }
}




