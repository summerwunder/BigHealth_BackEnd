package edu.whut.controller;


import edu.whut.domain.dto.UpdateStatusDTO;
import edu.whut.domain.pojo.Product;
import edu.whut.response.Result;
import edu.whut.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/unusedProducts")
    public Result getUnusedProducts(@RequestParam Long id) {
        List<Product> unusedProducts = orderDetailsService.getUnusedProducts(id);
        return Result.success(unusedProducts);
    }


    @PutMapping("/updateStatus")
    public Result updateOrderStatus(@RequestBody UpdateStatusDTO updateStatusDTO) {
        boolean isUpdated = orderDetailsService.updateOrderStatus(updateStatusDTO);
        if (isUpdated) {
            return Result.success("订单状态更新成功");
        } else {
            return Result.error("订单状态更新失败");
        }
    }
}
