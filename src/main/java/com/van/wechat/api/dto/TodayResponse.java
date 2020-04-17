package com.van.wechat.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class TodayResponse {
    private String reason;
    private int error_code;
    private List<ResultBean> result;

    @Data
    public static class ResultBean {
        private String _id;
        private String title;
        private String pic;
        private int year;
        private int month;
        private int day;
        private String des;
        private String lunar;
    }
}
