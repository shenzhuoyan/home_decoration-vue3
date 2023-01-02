package com.home_decoration.back_end.baiguo.domain;

import lombok.Data;

@Data
public class Notice {
    private int id;
    private String date;
    private String title;
    private String content;
    private int status;//0删除，1发布，2为置顶，3为最顶
}
