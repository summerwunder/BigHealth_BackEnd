package edu.whut.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whut.pojo.Orders;
import edu.whut.response.PageResult;
import edu.whut.service.OrdersService;
import edu.whut.mapper.OrdersMapper;
import edu.whut.vo.OrdersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author wunder
* @description 针对表【orders(订单表)】的数据库操作Service实现
* @createDate 2024-11-22 15:23:57
*/
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
    implements OrdersService{

    @Autowired
    private OrdersMapper orderMapper;
    @Override
    public PageResult<OrdersVO> getOrderList(int page, int size, String name, String gender, String phone, String date) {
        Page<OrdersVO> orderPage = new Page<>(page, size);

        // 查询三表联查结果
        List<OrdersVO> orders = orderMapper.getOrderList(orderPage, name, gender, phone, date);
        long total = orderPage.getTotal();

        // 封装分页结果
        return new PageResult<>(orders, total);
    }
}




