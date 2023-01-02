package com.home_decoration.back_end.baiguo.domain;

import lombok.Data;

@Data
public class AnLi {
    private int id;
    private String title;
    private String date;
    private String content;
    private Designer designer;
    private String introduction;
    private String picture;
}
