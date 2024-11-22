package edu.whut.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whut.pojo.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.whut.vo.OrdersVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author wunder
* @description 针对表【orders(订单表)】的数据库操作Mapper
* @createDate 2024-11-22 15:23:57
* @Entity edu.whut.pojo.Orders
*/
public interface OrdersMapper extends BaseMapper<Orders> {

    List<OrdersVO> getOrderList(
            @Param("page") Page<OrdersVO> page,
            @Param("name") String name,
            @Param("gender") String gender,
            @Param("phone") String phone,
            @Param("date") String date
    );
}




