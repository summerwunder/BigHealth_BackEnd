package edu.whut.controller;

import edu.whut.response.PageResult;
import edu.whut.response.Result;
import edu.whut.service.OrdersService;
import edu.whut.domain.vo.OrdersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    private OrdersService orderService;

    @GetMapping("/list")
    public Result getOrderList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String date) {
        // 调用服务层获取订单列表
        // 调用服务层获取订单列表
        PageResult<OrdersVO> orderList = orderService.getOrderList(page, size, name, gender, phone, date);
        return Result.success(orderList);
    }


    @PostMapping("/create")
    public Result createOrder(@RequestBody Map<String, Object> orderData) {
        try {
            Integer userId = (Integer) orderData.get("userId");
            Integer productId = (Integer) orderData.get("productId");
            BigDecimal price = new BigDecimal(orderData.get("price").toString());

            boolean success = orderService.createOrder(userId, productId, price);
            if (success) {
                return Result.success("订单创建成功");
            } else {
                return Result.error("订单创建失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("订单创建异常：" + e.getMessage());
        }
    }
}
