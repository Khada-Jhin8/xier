package vip.zhguo.xier.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * [一句话描述该类的功能]
 *
 * @author : [zhenghg]
 * @version : [v1.0]
 * @createTime : [2022/10/17 16:35]
 */
@RestController
public class GetWxMsg {

    @PostMapping("/getwx")
    public String postwx(@RequestBody String msg ){
        System.out.println(msg);
        return "";
    }

    @GetMapping("/getwx")
    public String getwx(@RequestParam String echostr){
        System.out.println(echostr);
        return echostr;
    }


}


