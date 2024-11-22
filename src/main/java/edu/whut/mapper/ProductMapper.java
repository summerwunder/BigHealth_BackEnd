package edu.whut.mapper;

import edu.whut.pojo.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
* @author wunder
* @description 针对表【product(商品表)】的数据库操作Mapper
* @createDate 2024-11-21 23:05:01
* @Entity edu.whut.pojo.Product
*/
public interface ProductMapper extends BaseMapper<Product> {
    @Select("SELECT * FROM product WHERE id = #{id} AND is_deleted = 0")
    Product selectByIdAndNotDeleted(Long id);
}




