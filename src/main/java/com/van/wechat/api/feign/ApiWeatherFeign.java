package com.van.wechat.api.feign;

import com.van.wechat.api.dto.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "api-weather", url = "http://apis.juhe.cn/")
public interface ApiWeatherFeign {

    String WEATHER_KEY = "b5ad4e20c5147eab06f1f59d08ad014c";

    @GetMapping("/simpleWeather/query")
    WeatherResponse weather(@RequestParam(name = "key") String key, @RequestParam(name = "city") String city);
}
