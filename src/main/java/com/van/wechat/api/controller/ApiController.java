package com.van.wechat.api.controller;

import com.google.gson.Gson;
import com.van.wechat.api.dto.NewsResponse;
import com.van.wechat.api.dto.TodayResponse;
import com.van.wechat.api.dto.WeatherResponse;
import com.van.wechat.api.feign.ApiNewsFeign;
import com.van.wechat.api.feign.ApiTodayFeign;
import com.van.wechat.api.feign.ApiWeatherFeign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    private Gson gson = new Gson();
    private final ApiNewsFeign apiNewsFeign;
    private final ApiTodayFeign apiTodayFeign;
    private final ApiWeatherFeign apiWeatherFeign;

    public ApiController(ApiNewsFeign apiNewsFeign, ApiTodayFeign apiTodayFeign, ApiWeatherFeign apiWeatherFeign) {
        this.apiNewsFeign = apiNewsFeign;
        this.apiTodayFeign = apiTodayFeign;
        this.apiWeatherFeign = apiWeatherFeign;
    }

    @GetMapping("/news")
    public Object getTodayNews(@RequestParam(name = "type") String type) {
        NewsResponse news = apiNewsFeign.news(ApiNewsFeign.NEWS_KEY, type);
        return gson.toJson(news);
    }

    @GetMapping("/weather")
    public Object getTodayWeather(@RequestParam(name = "city") String city) {
        WeatherResponse weather = apiWeatherFeign.weather(ApiWeatherFeign.WEATHER_KEY, city);
        return gson.toJson(weather);
    }

    @GetMapping("/today")
    public Object getTodayHistory(@RequestParam(name = "month") String month, @RequestParam(name = "day") String day) {
        String str = apiTodayFeign.today(ApiTodayFeign.TODAY_KEY, "1.0", month, day);
        TodayResponse today = gson.fromJson(str, TodayResponse.class);
        return gson.toJson(today);
    }

}
