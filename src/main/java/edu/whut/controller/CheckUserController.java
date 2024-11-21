package edu.whut.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whut.dto.CheckUserAddDTO;
import edu.whut.dto.CheckUserDTO;
import edu.whut.response.Result;
import edu.whut.service.CheckUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 新增体检人
     * @param checkUserDTO 前端传递的数据
     * @return 操作结果
     */
    @PostMapping("/add")
    public Result addCheckUser(@RequestBody CheckUserAddDTO checkUserDTO) {
        boolean success = checkUserService.addCheckUser(checkUserDTO);
        if (success) {
            return Result.success("新增成功");
        } else {
            return Result.error("添加失败");
        }
    }
}
