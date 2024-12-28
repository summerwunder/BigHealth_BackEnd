package edu.whut.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whut.domain.dto.ProductDTO;
import edu.whut.domain.pojo.Product;
import edu.whut.service.ProductService;
import edu.whut.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    public boolean addProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setType(productDTO.getType());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setDetails(productDTO.getDetails());
        product.setIsListed(productDTO.getIsListed());
        product.setIsRecommended(productDTO.getIsRecommended());
        product.setUpdateTime(new Date());
        product.setIsDeleted(0); // 默认未删除
        product.setVersion(0); // 乐观锁版本初始为0
        return productMapper.insert(product) > 0;
    }

    @Override
    public boolean deleteProduct(Long id) {
        return productMapper.deleteById(id) > 0;
    }

    @Override
    public Product searchProductById(Long id) {
        // Query the product based on ID and ensure it's not logically deleted
        return productMapper.selectById(id);
    }

    @Override
    public boolean updateProduct(Product product) {
        // Check if the product exists and is not logically deleted
        Product existingProduct = productMapper.selectByIdAndNotDeleted(product.getId());
        if (existingProduct == null) {
            return false; // Product does not exist or has been deleted
        }
        // Update fields and set the updated time
        product.setUpdateTime(new Date());
        return productMapper.updateById(product) > 0;
    }

    @Override
    public Product getProductById(Long id) {
        return productMapper.selectById(id);
    }
}




