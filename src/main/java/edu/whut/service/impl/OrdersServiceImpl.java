package edu.whut.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whut.domain.pojo.*;
import edu.whut.domain.vo.RecordOrderDetailsVO;
import edu.whut.mapper.*;
import edu.whut.response.PageResult;
import edu.whut.service.OrdersService;
import edu.whut.domain.vo.OrdersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    @Autowired
    private OrderDetailsMapper orderDetailsMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private RecordsMapper recordsMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CheckUserMapper checkUserMapper;
    @Autowired
    private UserCheckUserMapper userCheckUserMapper;

    @Override
    public PageResult<OrdersVO> getOrderList(int page, int size, String name, String gender, String phone, String date) {
        Page<OrdersVO> orderPage = new Page<>(page, size);
        if(ObjectUtil.isNotEmpty(date)){
            date = date.split("T")[0];
            // 获取最后一位字符并加1
            char lastChar = date.charAt(date.length() - 1);
            int incrementedChar = Character.getNumericValue(lastChar) + 1;
            // 拼接字符串
            date = date.substring(0, date.length() - 1) + incrementedChar;
        }
        // 查询三表联查结果
        List<OrdersVO> orders = orderMapper.getOrderList(orderPage, name, gender, phone, date);
        long total = orderPage.getTotal();

        // 封装分页结果
        return new PageResult<>(orders, total);
    }

    @Override
    public boolean createOrder(Integer userId, Integer productId, BigDecimal price) {
        // 创建订单
        Orders order = new Orders();
        order.setOrderNumber('F'+UUID.randomUUID().toString()); // 生成订单编号
        order.setIsDeleted(0);
        order.setVersion(1);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        int orderResult = orderMapper.insert(order);

        if (orderResult > 0) {
            // 创建订单详情
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrderId(order.getId());
            orderDetails.setUserId(userId);
            orderDetails.setProductId(productId);
            orderDetails.setStatus("已支付"); // 默认订单状态
            orderDetails.setOrderTime(new Date());
            orderDetails.setTotalPrice(price);
            orderDetails.setIsDeleted(0);
            orderDetails.setIsUsed(0);
            int detailResult = orderDetailsMapper.insert(orderDetails);

            return detailResult > 0;
        }

        return false;
    }



    @Override
    public List<RecordOrderDetailsVO> getAllOrders(Long userId,String status) {
        LambdaQueryWrapper<OrderDetails> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderDetails::getUserId, userId); // 匹配用户ID
        if("全部".equals(status)){

        }else if("待预约".equals(status)){
            queryWrapper.eq(OrderDetails::getStatus,"已支付");
            queryWrapper.eq(OrderDetails::getIsUsed,0);
        }else if("已完成".equals(status)){
            queryWrapper.eq(OrderDetails::getStatus,"已支付");
            queryWrapper.eq(OrderDetails::getIsUsed,1);
        }else if ("退款/售后".equals(status)) {
            queryWrapper.in(OrderDetails::getStatus, "退款成功", "申请退款");
        }else{
            // 已完成
            queryWrapper.eq(OrderDetails::getStatus,"已支付");
            queryWrapper.eq(OrderDetails::getIsUsed,1);
        }
        List<OrderDetails> orderDetailsList = orderDetailsMapper.selectList(queryWrapper);
        return mapToRecordOrderDetailsVO(orderDetailsList,userId);
    }

    private List<RecordOrderDetailsVO> mapToRecordOrderDetailsVO(List<OrderDetails> orderDetailsList,Long userId) {

        User user = userMapper.selectById(userId);
        // 遍历 OrderDetails 列表，将数据组装成 RecordOrderDetailsVO 列表
        return orderDetailsList.stream().map(orderDetails -> {
            RecordOrderDetailsVO vo = new RecordOrderDetailsVO();
            Long orderId = orderDetails.getOrderId();
            Orders orders = orderMapper.selectById(orderId);
            // 填补信息
            vo.setOrderNumber(orders.getOrderNumber());
            vo.setCreateTime(orders.getCreateTime());
            vo.setUserName(user.getRealName());
            vo.setPhone(user.getPhone());
            vo.setAddress(user.getAddress());
            vo.setId(orders.getId());
            String status =null;
            if ("退款成功".equals(orderDetails.getStatus())||
                    "申请退款".equals(orderDetails.getStatus()))
                status =orderDetails.getStatus();
            else if("已支付".equals(orderDetails.getStatus())&& orderDetails.getIsUsed() == 0)
                status = "待预约";
            else
                status = "已完成";
            vo.setStatus(status);
            vo.setPrice(orderDetails.getTotalPrice());
            // 从 productMapper 获取商品信息
            Product product = productMapper.selectById(orderDetails.getProductId());
            vo.setProductName(product.getName());
            vo.setProductId(product.getId());
            vo.setProductDescription(product.getDescription());
            // 从 recordsMapper 获取预约信息
            vo.setAppointmentTime(DateTime.now());

            return vo;
        }).toList();
    }

    private List<RecordOrderDetailsVO> mapToRecordOrderDetailsVOFromRecords(List<Records> recordsList) {
        // 遍历 Records 列表，将数据组装成 RecordOrderDetailsVO 列表
        return recordsList.stream().map(record -> {
            RecordOrderDetailsVO vo = new RecordOrderDetailsVO();
            vo.setAppointmentTime(record.getAppointmentTime());

            // 从 orderDetailsMapper 获取订单详情
            Long orderId = record.getId();
            OrderDetails orderDetails = orderDetailsMapper.selectById(orderId);
            vo.setOrderNumber("F1234");
            vo.setStatus(orderDetails.getStatus());
            vo.setPrice(orderDetails.getTotalPrice());

            // 从 productMapper 获取商品信息
            Product product = productMapper.selectById(orderDetails.getProductId());
            vo.setProductName(product.getName());

            return vo;
        }).toList();
    }


}




