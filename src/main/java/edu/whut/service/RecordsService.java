package edu.whut.service;

import edu.whut.domain.dto.RecordAddDTO;
import edu.whut.domain.pojo.Records;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.whut.response.PageResult;
import edu.whut.domain.vo.RecordVO;

/**
* @author wunder
* @description 针对表【records(预约记录表)】的数据库操作Service
* @createDate 2024-11-23 09:27:05
*/
public interface RecordsService extends IService<Records> {

    PageResult<RecordVO> getRecordList(String name, String gender, String phone, String date, int page, int size);

    boolean addRecord(RecordAddDTO recordAddDTO);
}
