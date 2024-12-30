package edu.whut.service;

import edu.whut.domain.pojo.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.whut.domain.vo.RecordOrderDetailsVO;
import edu.whut.response.PageResult;
import edu.whut.domain.vo.OrdersVO;

import java.math.BigDecimal;
import java.util.List;

/**
* @author wunder
* @description 针对表【orders(订单表)】的数据库操作Service
* @createDate 2024-11-22 15:23:57
*/
public interface OrdersService extends IService<Orders> {

    public PageResult<OrdersVO> getOrderList(int page, int size, String name, String gender, String phone, String date);

    boolean createOrder(Integer userId, Integer productId, BigDecimal price);

    List<RecordOrderDetailsVO> getAllOrders(Long userId,String status);
}
