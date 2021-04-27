package com.van.wechat.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "api-today", url = "http://api.juheapi.com/")
public interface ApiTodayFeign {

    String TODAY_KEY = "560b37578c8ab3c6b63b38f384009120";

    @GetMapping("/japi/toh")
    String today(@RequestParam(name = "key") String key,
                        @RequestParam(name = "v") String version,
                        @RequestParam(name = "month") String month,
                        @RequestParam(name = "day") String day);
}
