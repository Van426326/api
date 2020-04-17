package com.van.wechat.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class NewsResponse {

    private String reason;
    private ResultBean result;
    private int error_code;

    @Data
    public static class ResultBean {

        private String stat;
        private List<DataBean> data;

        @Data
        public static class DataBean {
            private String uniquekey;
            private String title;
            private String date;
            private String category;
            private String author_name;
            private String url;
            private String thumbnail_pic_s;
            private String thumbnail_pic_s02;
            private String thumbnail_pic_s03;
        }
    }
}
