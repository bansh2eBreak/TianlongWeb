package com.nvyao.controller;

import com.nvyao.anno.Log;
import com.nvyao.pojo.Result;
import com.nvyao.pojo.Sect;
import com.nvyao.service.SectsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 门派管理Controller
 */
@RestController
@Slf4j
public class SectsController {
    @Autowired
    private SectsService sectsService;

    /**
     * 查询所有门派接口
     *
     * @return
     */
    @GetMapping("/sects")
    public Result getAllSects() {
        log.info("调用了查询所有部门接口：/sects");
        List<Sect> sects = sectsService.getAllSects();
        return Result.success(sects);
    }

    /**
     * 新增门派接口
     *
     * @return
     */
    @Log
    @PostMapping("/sects")
    public Result addSect(@RequestBody Sect sect) {
        log.info("新增门派：{}", sect.getName());
        sectsService.addSect(sect);
        return Result.success();
    }

    /**
     * 根据门派id删除门派
     *
     * @param id 门派id
     * @return
     */
    @Log
    @DeleteMapping("/sects/{id}")
    public Result deleteSect(@PathVariable Integer id) {
        log.info("根据门派id删除部门：{}", id);
        sectsService.deleteSect(id);
        return Result.success();
    }

    /**
     * 根据门派id查询单个门派信息
     *
     * @param id
     * @return
     */
    @GetMapping("/sects/{id}")
    public Result selectSectById(@PathVariable Integer id) {
        log.info("根据门派id查询：{}", id);
        Sect sect = sectsService.selectSectById(id);
        log.info("查询的门派数据是：{}", sect);
        return Result.success(sect);
    }

    /**
     * 根据门派id修改门派信息
     *
     * @param sect
     * @return
     */
    @Log
    @PutMapping("/sects")
    public Result updateSectById(@RequestBody Sect sect) {
        log.info("更新部门信息，门派id：{} -- 门派名称：{}", sect.getId(), sect.getName());
        sectsService.updateSect(sect);
        return Result.success();
    }
}
