package edu.whut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whut.pojo.Records;
import edu.whut.service.RecordsService;
import edu.whut.mapper.RecordsMapper;
import org.springframework.stereotype.Service;

/**
* @author wunder
* @description 针对表【records(预约记录表)】的数据库操作Service实现
* @createDate 2024-11-23 09:27:05
*/
@Service
public class RecordsServiceImpl extends ServiceImpl<RecordsMapper, Records>
    implements RecordsService{

}




