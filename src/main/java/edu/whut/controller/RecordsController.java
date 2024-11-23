package edu.whut.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whut.response.PageResult;
import edu.whut.response.Result;
import edu.whut.service.RecordsService;
import edu.whut.vo.RecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}

