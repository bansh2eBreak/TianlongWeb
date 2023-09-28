package com.nvyao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean {

    private Long total;  //总记录数
    private List<Hero> rows;    //数据列表

}
