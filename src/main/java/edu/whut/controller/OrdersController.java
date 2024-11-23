package edu.whut.controller;

import edu.whut.response.PageResult;
import edu.whut.response.Result;
import edu.whut.service.OrdersService;
import edu.whut.domain.vo.OrdersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
