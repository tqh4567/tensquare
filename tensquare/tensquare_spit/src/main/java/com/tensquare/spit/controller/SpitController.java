package com.tensquare.spit.controller;
import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping(value = "/spit")
public class SpitController {
    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private HttpServletRequest request;
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findAll());
    }
    @RequestMapping(value = "/{spitId}",method = RequestMethod.GET)
    public Result findById(@PathVariable String spitId){
        return new Result(true,StatusCode.OK,"查询成功",spitService.findById(spitId));
    }
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Spit spit){
        String claims = (String) request.getAttribute("claims_user");
        if(claims==null){
            return new Result(false,StatusCode.ACCESSERROR,"权限不足");
        }
        spitService.save(spit);
        return new Result(true,StatusCode.OK,"保存成功");

    }
    @RequestMapping(value = "/{spitId}",method = RequestMethod.PUT)
    public Result update(@PathVariable String spitId,@RequestBody Spit spit){
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true,StatusCode.OK,"修改成功");

    }
    @RequestMapping(value = "/{spitId}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String spitId){
        spitService.delete(spitId);
        return new Result(true,StatusCode.OK,"删除成功");

    }
    @RequestMapping(value = "/comment/{parentid}/{page}/{size}",method = RequestMethod.GET)
    public  Result findSpitByParentId(@PathVariable String parentid,@PathVariable int page,@PathVariable int size){
        Page<Spit> pagelist=spitService.findByParentId(parentid,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Spit>(pagelist.getTotalElements(),pagelist.getContent()));
    }
    @RequestMapping(value = "/thumbup/{spitId}",method = RequestMethod.PUT)
    public Result thumbup(@PathVariable String spitId){
        //判断当前id是否已经登陆，由于没有做认证，先把用户给协定
        String userId="111";
        if(redisTemplate.opsForValue().get("thumbup"+userId)!=null){
            return new Result(false,StatusCode.REPERROR,"您已经点过赞了");

        }
        redisTemplate.opsForValue().get("thumbup"+userId);
        spitService.thumbup(spitId);
        redisTemplate.opsForValue().set("thumbup"+userId,1);
        return new Result(true,StatusCode.OK,"点赞成功");

    }
    @RequestMapping(value = "/visits/{spitId}",method = RequestMethod.PUT)
    public Result visits(@PathVariable String spitId){

        spitService.visits(spitId);
        return new Result(true,StatusCode.OK,"访问成功");

    }
    @RequestMapping(value = "/share/{spitId}",method = RequestMethod.PUT)
    public Result share(@PathVariable String spitId){

        spitService.share(spitId);
        return new Result(true,StatusCode.OK,"分享成功");

    }
}
