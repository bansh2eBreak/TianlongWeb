package com.nvyao.service;

import com.nvyao.pojo.Sect;
import java.util.List;

/**
 * 门派管理的Service接口
 */
public interface SectsService {

    List<Sect> getAllSects();

    void addSect(Sect sect);

    void deleteSect(Integer id);

    Sect selectSectById(Integer id);

    void updateSect(Sect sect);

}
