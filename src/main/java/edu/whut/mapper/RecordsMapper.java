package edu.whut.mapper;

import edu.whut.domain.pojo.Records;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.whut.domain.vo.RecordDetailsVO;
import edu.whut.domain.vo.RecordVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    List<RecordDetailsVO> fetchDetailsByCheckUserId(@Param("id") Long id);

    @Select("SELECT COUNT(*) FROM records WHERE check_user_id = #{checkUserId} AND is_deleted = 0")
    Integer countByCheckUserId(@Param("checkUserId") Integer checkUserId);
}




