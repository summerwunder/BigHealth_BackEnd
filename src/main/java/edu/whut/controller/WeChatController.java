package edu.whut.controller;


import edu.whut.domain.dto.LoginUserDTO;
import edu.whut.domain.dto.UserDTO;
import edu.whut.domain.pojo.User;
import edu.whut.response.Result;
import edu.whut.service.CheckUserService;
import edu.whut.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/wechat")
public class WeChatController {

    @Autowired
    private UserService userService;
    @Autowired
    private CheckUserService checkUserService;
    @PostMapping("/login")
    public Result userLogin(@RequestBody LoginUserDTO userLoginDTO) {
        log.info("good");
        User userDTO= userService.getUserInfo(userLoginDTO);
        if(userDTO==null){
            return Result.error("登陆失败,未找到用户");
        }
        return Result.success(userDTO);
    }

    /**
     * 根据用户ID获取所有体检人信息
     *
     * @param userId 用户ID
     * @return 体检人信息列表
     */
    @GetMapping("/checkUserList")
    public Result getCheckUsersByUserId(@RequestParam("userId") Long userId) {
        return Result.success(checkUserService.getCheckUsersByUserId(userId));
    }
}
