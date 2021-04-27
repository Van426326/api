package com.van.wechat.api.controller;

import com.google.gson.Gson;
import com.van.wechat.api.dto.NewsResponse;
import com.van.wechat.api.dto.TodayResponse;
import com.van.wechat.api.dto.WeatherResponse;
import com.van.wechat.api.feign.ApiNewsFeign;
import com.van.wechat.api.feign.ApiTodayFeign;
import com.van.wechat.api.feign.ApiWeatherFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Api(tags = "接口")
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
    @ApiImplicitParam(name = "type",
            value = "新闻类型: top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)",
            required = true,
            paramType = "String")
    @ApiOperation(value = "获取今日新闻", httpMethod = "GET")
    public Object getTodayNews(@RequestParam(name = "type") String type) {
        NewsResponse news = apiNewsFeign.news(ApiNewsFeign.NEWS_KEY, type);
        return gson.toJson(news);
    }

    @GetMapping("/weather")
    @ApiImplicitParam(name = "city", value = "城市：南京", required = true)
    @ApiOperation(value = "获取当前天气")
    public Object getTodayWeather(@RequestParam(name = "city") String city) {
        WeatherResponse weather = apiWeatherFeign.weather(ApiWeatherFeign.WEATHER_KEY, city);
        return gson.toJson(weather);
    }

    @GetMapping("/today")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "month", value = "月份", required = true, paramType = "query"),
            @ApiImplicitParam(name = "day", value = "日期", required = true, paramType = "query")
    })
    @ApiOperation("获取历史上的今天")
    public Object getTodayHistory(@RequestParam(name = "month") String month, @RequestParam(name = "day") String day) {
        String str = apiTodayFeign.today(ApiTodayFeign.TODAY_KEY, "1.0", month, day);
        TodayResponse today = gson.fromJson(str, TodayResponse.class);
        return gson.toJson(today);
    }

}
