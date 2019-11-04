package com.abc.demo._19interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lihj on 2019/10/26.
 */
@Controller
public class InterceptorController {
    @ResponseBody
    @RequestMapping("/interceptor")
    public String dataSource() throws Exception {
        return "interceptor hello";
    }
}
