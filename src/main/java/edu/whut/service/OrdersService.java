package edu.whut.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whut.pojo.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.whut.response.PageResult;
import edu.whut.vo.OrdersVO;

/**
* @author wunder
* @description 针对表【orders(订单表)】的数据库操作Service
* @createDate 2024-11-22 15:23:57
*/
public interface OrdersService extends IService<Orders> {

    public PageResult<OrdersVO> getOrderList(int page, int size, String name, String gender, String phone, String date);
}
