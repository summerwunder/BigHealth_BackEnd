package edu.whut.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whut.pojo.Records;
import edu.whut.response.PageResult;
import edu.whut.service.RecordsService;
import edu.whut.mapper.RecordsMapper;
import edu.whut.vo.OrdersVO;
import edu.whut.vo.RecordVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author wunder
* @description 针对表【records(预约记录表)】的数据库操作Service实现
* @createDate 2024-11-23 09:27:05
*/
@Service
@Slf4j
public class RecordsServiceImpl extends ServiceImpl<RecordsMapper, Records>
    implements RecordsService{

    @Autowired
    private  RecordsMapper recordsMapper;


    @Override
    public PageResult<RecordVO> getRecordList(String name, String gender, String phone, String date, int page, int size) {
        int offset = (page - 1) * size;
        if(ObjectUtil.isNotEmpty(date)){
            date = date.split("T")[0];
            // 获取最后一位字符并加1
            char lastChar = date.charAt(date.length() - 1);
            int incrementedChar = Character.getNumericValue(lastChar) + 1;
            // 拼接字符串
            date = date.substring(0, date.length() - 1) + incrementedChar;
        }
        List<RecordVO> records = recordsMapper.queryRecords(name, gender, phone, date, size, offset);
        long total = recordsMapper.countRecords(name, gender, phone, date);
        return new PageResult<>(records, total);
    }
}




