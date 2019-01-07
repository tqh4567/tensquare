package com.tensquare.friend.controller;

import com.tensquare.friend.service.FriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private FriendService friendService;
    @RequestMapping(value = "/like/{friendid}/{type}",method = RequestMethod.PUT)
    public Result addFriend(@PathVariable String friendid,@PathVariable String type){
        //验证用户是否登陆
        Claims claims = (Claims) request.getAttribute("claims_user");
        if(claims==null){
            return new Result(false, StatusCode.ACCESSERROR,"权限不足");
        }
        //添加好友
        if(type!=null) {
            if (type.equals("1")) {
                //添加好友
                if ((friendService.selectCount(claims.getId(), friendid)) == 0) {
                    return new Result(false, StatusCode.REPERROR, "你已经添加过该好友");
                }
                if ((friendService.selectCount(claims.getId(), friendid)) == 1){
                    return new Result(true,StatusCode.OK,"添加成功");
                }
            }
            else if(type.equals("2")){
                //添加非好友
            }
        }
        else {
            return new Result(false,StatusCode.ERROR,"参数异常！");
        }
        return new Result(false,StatusCode.ERROR,"参数异常！");
    }
}
