package edu.whut.mapper;

import edu.whut.pojo.Records;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.whut.vo.RecordVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
* @author wunder
* @description 针对表【records(预约记录表)】的数据库操作Mapper
* @createDate 2024-11-23 09:27:05
* @Entity edu.whut.pojo.Records
*/
public interface RecordsMapper extends BaseMapper<Records> {
    List<RecordVO> queryRecords(@Param("name") String name,
                                @Param("gender") String gender,
                                @Param("phone") String phone,
                                @Param("date") String date,
                                @Param("pageSize") int pageSize,
                                @Param("offset") int offset);

    long countRecords(String name, String gender, String phone, String date);
}




