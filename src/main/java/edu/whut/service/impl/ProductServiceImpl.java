package edu.whut.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whut.pojo.Product;
import edu.whut.pojo.User;
import edu.whut.service.ProductService;
import edu.whut.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author wunder
* @description 针对表【product(商品表)】的数据库操作Service实现
* @createDate 2024-11-21 23:05:01
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService{

    @Autowired
    private ProductMapper productMapper;

    @Override
    public IPage<Product> getProductList(int page, int size, String productName, String productType, Integer isListed) {
        IPage<Product> userPage=new Page<>(page,size);
        // 构造查询条件
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        if (productName != null && !productName.isEmpty()) {
            queryWrapper.like(Product::getName, productName); // 模糊查询
        }
        if (productType != null && ObjectUtil.isNotEmpty(productType)) {
            queryWrapper.eq(Product::getType, productType); // 精确匹配
        }
        if (isListed != null ) {
            queryWrapper.eq(Product::getIsListed, isListed); // 模糊查询
        }

        // 分页查询
        return productMapper.selectPage(userPage, queryWrapper);
    }
}




