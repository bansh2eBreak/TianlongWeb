package com.nvyao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sect {

    private Integer id;
    private String name;
    private String introduction;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
