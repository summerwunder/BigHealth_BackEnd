package edu.whut.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whut.pojo.OrderDetails;
import edu.whut.service.OrderDetailsService;
import edu.whut.mapper.OrderDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author wunder
* @description 针对表【order_details(订单详情表)】的数据库操作Service实现
* @createDate 2024-11-22 15:24:03
*/
@Service
public class OrderDetailsServiceImpl extends ServiceImpl<OrderDetailsMapper, OrderDetails>
    implements OrderDetailsService{

    @Autowired
    private OrderDetailsMapper orderDetailsMapper;
    @Override
    public Boolean approveRefund(Integer orderId) {
        LambdaQueryWrapper<OrderDetails> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderDetails::getOrderId, orderId);
        OrderDetails orderDetails = orderDetailsMapper.selectOne(wrapper);
        if(ObjectUtil.isNull(orderDetails)){
            return false;
        }else{
            orderDetails.setStatus("退款成功");
            orderDetailsMapper.updateById(orderDetails);
            return true;
        }
    }
}




