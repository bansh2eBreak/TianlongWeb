package com.nvyao.service.impl;

import com.nvyao.mapper.SectsMapper;
import com.nvyao.pojo.Sect;
import com.nvyao.service.SectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SectsServiceImpl implements SectsService {

    @Autowired
    private SectsMapper sectsMapper;

    @Override
    public List<Sect> getAllSects() {
        List<Sect> sects =  sectsMapper.selectAll();
        return sects;
    }

    @Override
    public void addSect(Sect sect) {
        sect.setCreateTime(LocalDateTime.now());
        sect.setUpdateTime(LocalDateTime.now());
        sectsMapper.insert(sect);
    }

    @Override
    public void deleteSect(Integer id) {
        sectsMapper.delete(id);
    }

    @Override
    public Sect selectSectById(Integer id) {
        Sect sect = sectsMapper.selectById(id);
        return sect;
    }

    @Override
    public void updateSect(Sect sect) {
        sect.setUpdateTime(LocalDateTime.now());
        sectsMapper.update(sect);
    }

}
