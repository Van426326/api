package com.van.wechat.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class WeatherResponse {

    private String reason;
    private ResultBean result;
    private int error_code;

    @Data
    public static class ResultBean {

        private String city;
        private RealtimeBean realtime;
        private List<FutureBean> future;

        @Data
        public static class RealtimeBean {
            private String temperature;
            private String humidity;
            private String info;
            private String wid;
            private String direct;
            private String power;
            private String aqi;
        }

        @Data
        public static class FutureBean {
            private String date;
            private String temperature;
            private String weather;
            private WidBean wid;
            private String direct;

            @Data
            public static class WidBean {
                private String day;
                private String night;
            }
        }
    }
}
