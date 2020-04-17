package com.van.wechat.api.feign;

import com.van.wechat.api.dto.NewsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "api-news", url = "http://v.juhe.cn/")
public interface ApiNewsFeign {

    String NEWS_KEY = "9c0cfd2d811fba54e2d54bd0b92a0b5a";

    //top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
    @GetMapping("/toutiao/index")
    NewsResponse news(@RequestParam(name = "key") String key, @RequestParam(name = "type") String type);
}
