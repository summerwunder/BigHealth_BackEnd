package edu.whut.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whut.dto.ProductDTO;
import edu.whut.pojo.Product;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author wunder
* @description 针对表【product(商品表)】的数据库操作Service
* @createDate 2024-11-21 23:05:01
*/
public interface ProductService extends IService<Product> {

    IPage<Product> getProductList(int page, int size, String productName, String productType, Integer isListed);

    boolean addProduct(ProductDTO productDTO);

    boolean deleteProduct(Long id);

    Product searchProductById(Long id);

    boolean updateProduct(Product product);
}
