package edu.whut.controller;


import edu.whut.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserContrller {
    @GetMapping("/list")
    public Result getUserList() {
        return Result.success("good");
    }
}
