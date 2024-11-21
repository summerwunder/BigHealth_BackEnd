package edu.whut.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whut.dto.CheckUserDTO;
import edu.whut.response.Result;
import edu.whut.service.CheckUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkUser")
public class CheckUserController {
    @Autowired
    private CheckUserService checkUserService;

    /**
     * 获取体检人列表
     * @param page 当前页码
     * @param size 每页条数
     * @param name 姓名
     * @param gender 性别
     * @param phone 手机号
     * @return 分页结果
     */
    @GetMapping("/list")
    public Result getCheckUserList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String phone) {

        IPage<CheckUserDTO> checkUserList = checkUserService.getCheckUserList(page, size, name, gender, phone);
        return Result.success(checkUserList);
    }
}
