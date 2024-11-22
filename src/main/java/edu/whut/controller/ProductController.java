package edu.whut.controller;

import edu.whut.dto.ProductDTO;
import edu.whut.pojo.Product;
import edu.whut.response.Result;
import edu.whut.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public Result getProductList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) String productType,
            @RequestParam(required = false) Integer isListed) {

        return Result.success
                (productService.getProductList(page, size, productName, productType, isListed));
    }

    @PostMapping("/add")
    public Result addProduct(@RequestBody ProductDTO productDTO) {
        boolean isSuccess = productService.addProduct(productDTO);
        if (isSuccess) {
            return Result.success("商品添加成功！");
        } else {
            return Result.error("商品添加失败！");
        }
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteProduct(@PathVariable Long id) {
        boolean isSuccess = productService.deleteProduct(id);
        if (isSuccess) {
            return Result.success("商品删除成功！");
        } else {
            return Result.error("商品删除失败，可能商品不存在或已删除！");
        }
    }

    @GetMapping("/search/{id}")
    public Result searchProductById(@PathVariable Long id) {
        Product product = productService.searchProductById(id);
        if (product != null) {
            return Result.success(product);
        } else {
            return Result.error("未找到该商品或商品已被删除！");
        }
    }

    @PostMapping("/update")
    public Result updateProduct(@RequestBody Product product) {
        boolean isUpdated = productService.updateProduct(product);
        if (isUpdated) {
            return Result.success("商品更新成功！");
        } else {
            return Result.error("商品更新失败或商品不存在！");
        }
    }
}
