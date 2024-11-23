package edu.whut.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whut.pojo.Records;
import edu.whut.response.PageResult;
import edu.whut.response.Result;
import edu.whut.service.RecordsService;
import edu.whut.vo.RecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/record")
public class RecordsController {
    @Autowired
    private RecordsService recordService;

    @GetMapping("/list")
    public Result listRecords(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String date,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageResult<RecordVO> recordPage = recordService.getRecordList(name, gender, phone, date, page, size);
        return Result.success(recordPage);
    }

    @GetMapping("/arrival")
    public Result arrivalRecords(@RequestParam Integer id){
        // 检查预约记录是否存在
        Records record = recordService.getById(id);
        if (record == null) {
            return Result.error("未找到该预约记录");
        }
        record.setStatus("已到店");
        boolean updated = recordService.updateById(record);

        // 返回更新结果
        if (updated) {
            return Result.success("预约状态已更新为已到店");
        } else {
            return Result.error("更新预约状态失败");
        }
    }

    @DeleteMapping("/delete")
    public Result deleteRecord(@RequestParam Long id) {
        boolean removed = recordService.removeById(id); // Remove by ID
        if (removed) {
            return Result.success();
        } else {
            return Result.error("删除失败");
        }
    }
}

