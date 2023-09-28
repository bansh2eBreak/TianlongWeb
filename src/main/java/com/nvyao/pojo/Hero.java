package com.nvyao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hero {

    private Integer id;
    private String username;
    private String password;
    private String name;
    private String kungfu;
    private Short gender;
    private String image;
    private Short level;
    private LocalDate birthdate;
    private Integer sectId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
