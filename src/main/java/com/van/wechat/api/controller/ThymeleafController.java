package com.van.wechat.api.controller;

import com.google.gson.Gson;
import com.van.wechat.api.dto.NewsResponse;
import com.van.wechat.api.dto.TodayResponse;
import com.van.wechat.api.dto.WeatherResponse;
import com.van.wechat.api.feign.ApiNewsFeign;
import com.van.wechat.api.feign.ApiTodayFeign;
import com.van.wechat.api.feign.ApiWeatherFeign;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ThymeleafController {
    private Gson gson = new Gson();
    private final ApiNewsFeign apiNewsFeign;
    private final ApiTodayFeign apiTodayFeign;
    private final ApiWeatherFeign apiWeatherFeign;

    public ThymeleafController(ApiNewsFeign apiNewsFeign, ApiTodayFeign apiTodayFeign, ApiWeatherFeign apiWeatherFeign) {
        this.apiNewsFeign = apiNewsFeign;
        this.apiTodayFeign = apiTodayFeign;
        this.apiWeatherFeign = apiWeatherFeign;
    }

    @RequestMapping("/hello")
    public String hello(Model model) {
        String name = "van";
        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping("/news")
    public Object getTodayNews(@RequestParam(name = "type") String type, Model model) {
        NewsResponse news = apiNewsFeign.news(ApiNewsFeign.NEWS_KEY, type);
        System.out.println(gson.toJson(news));
        model.addAttribute(news);
        return "news";
    }

    @GetMapping("/weather")
    public Object getTodayWeather(@RequestParam(name = "city") String city, Model model) {
        WeatherResponse weather = apiWeatherFeign.weather(ApiWeatherFeign.WEATHER_KEY, city);
        System.out.println(gson.toJson(weather));
        model.addAttribute(weather);
        return "weather";
    }

    @GetMapping("/today")
    public Object getTodayHistory(@RequestParam(name = "month") String month, @RequestParam(name = "day") String day, Model model) {
        String str = apiTodayFeign.today(ApiTodayFeign.TODAY_KEY, "1.0", month, day);
        TodayResponse today = gson.fromJson(str, TodayResponse.class);
        System.out.println(gson.toJson(today));
        model.addAttribute(today);
        return "today";
    }
}
