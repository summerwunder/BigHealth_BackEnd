package edu.whut.controller;


import edu.whut.domain.dto.UserDTO;
import edu.whut.response.Result;
import edu.whut.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserContrller {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Result getUserList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String phone) {
        return Result.success(userService.getAllUsers(page, size, username, gender, phone));
    }

    @PostMapping("/create")
    public Result addUser(@RequestBody UserDTO userDTO) {
        boolean isAdded = userService.addUser(userDTO);
        if (isAdded) {
            return Result.success("用户新增成功");
        } else {
            return Result.error("用户新增失败");
        }
    }
    @GetMapping("/search")
    public Result searchUser(@RequestParam Integer id){
        return Result.success(userService.searchUser(id));
    }

    @PostMapping("/update")
    public Result updateUser(@RequestBody UserDTO user,@RequestParam Integer id) {
        try {
            boolean isUpdated = userService.updateUser(user,id);
            if (isUpdated) {
                return Result.success("用户信息更新成功");
            } else {
                return Result.error("更新失败，用户可能不存在");
            }
        } catch (Exception e) {
            return Result.error("更新失败: " + e.getMessage());
        }
    }
}
