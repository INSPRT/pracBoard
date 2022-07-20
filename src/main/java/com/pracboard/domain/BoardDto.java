package com.pracboard.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class BoardDto {
    private Integer bno;
    private String title;
    private int like_cnt;
    private Date report_date;
    private String reporter;
    private String content;
    private String email;
    private String mobile;
    private int is_pinned;


}