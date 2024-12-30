package edu.whut.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whut.domain.dto.UpdateStatusDTO;
import edu.whut.mapper.ProductMapper;
import edu.whut.domain.pojo.OrderDetails;
import edu.whut.domain.pojo.Product;
import edu.whut.service.OrderDetailsService;
import edu.whut.mapper.OrderDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private ProductMapper productMapper;
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

    @Override
    public List<Product> getUnusedProducts(Long userId) {
        LambdaQueryWrapper<OrderDetails> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderDetails::getUserId, userId);
        wrapper.eq(OrderDetails::getIsUsed,0);
        wrapper.eq(OrderDetails::getStatus,"已支付");
        List<OrderDetails> orderDetailsList = orderDetailsMapper.selectList(wrapper);
        List<Integer> productIds = orderDetailsList.stream()
                .map(OrderDetails::getProductId)
                .distinct()
                .collect(Collectors.toList());

        // If no products found, return an empty list
        if (productIds.isEmpty()) {
            return Collections.emptyList();
        }
        // Query product details based on the extracted product IDs
        LambdaQueryWrapper<Product> productWrapper = new LambdaQueryWrapper<>();
        productWrapper.in(Product::getId, productIds);
        return productMapper.selectList(productWrapper);
    }

    @Override
    /**
     * 修改订单状态
     *
     * @param updateStatusDTO 包含订单详情ID和新的状态
     * @return 是否更新成功
     */
    public boolean updateOrderStatus(UpdateStatusDTO updateStatusDTO) {
        LambdaUpdateWrapper<OrderDetails> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(OrderDetails::getOrderId, updateStatusDTO.getOrderDetailsId())
                .set(OrderDetails::getStatus, updateStatusDTO.getNewStatus());

        return orderDetailsMapper.update(null, updateWrapper) > 0;
    }
}




