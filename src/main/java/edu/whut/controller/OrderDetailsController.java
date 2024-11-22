package edu.whut.controller;


import edu.whut.response.Result;
import edu.whut.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderDetail")
public class OrderDetailsController {
    @Autowired
    private OrderDetailsService orderDetailsService;

    @GetMapping("/approveRefund")
    public Result approveRefund(@RequestParam("id") Integer orderId) {
        Boolean isUpdated = orderDetailsService.approveRefund(orderId);
        if (isUpdated) {
            return Result.success("退款成功！");
        } else {
            return Result.error("退款失败！");
        }
    }
}
