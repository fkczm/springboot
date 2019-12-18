package com.abc.demo.mybatisPlus.controller;

        import com.abc.demo.mybatisPlus.domain.User;
        import com.abc.demo.mybatisPlus.service.UserService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created on 2019/12/18 in springboot 项目 .
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/saveBatch")
    public void saveBatch(){
        List<User> users = new ArrayList<>();
        User u1 = new User();
        u1.setAge(17);
        u1.setName("张飞");
        u1.setEmail("zhangfei@qq.com");
        u1.setId(14L);
        User u2 = new User();
        u2.setAge(18);
        u2.setName("关羽");
        u2.setEmail("guanyu@qq.com");
        u2.setId(13L);
        users.add(u1);
        users.add(u2);

        userService.saveBatch(users);
    }

    @RequestMapping("/list")
    public List<User> list (){
         return userService.list();
    }
}
