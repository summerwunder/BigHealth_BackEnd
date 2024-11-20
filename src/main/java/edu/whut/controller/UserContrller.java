package edu.whut.controller;


import edu.whut.pojo.User;
import edu.whut.response.Result;
import edu.whut.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
