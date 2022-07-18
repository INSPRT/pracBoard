package com.pracboard.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
    private String id;
    private String pwd;
    private String name;
    private String email;
    private Date birth;
    private String sns;
    private Date reg_date;

}