package edu.whut.service;

import edu.whut.domain.pojo.OrderDetails;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.whut.domain.pojo.Product;

import java.util.List;

/**
* @author wunder
* @description 针对表【order_details(订单详情表)】的数据库操作Service
* @createDate 2024-11-22 15:24:03
*/
public interface OrderDetailsService extends IService<OrderDetails> {

    Boolean approveRefund(Integer orderId);

    List<Product> getUnusedProducts(Long userId);
}
