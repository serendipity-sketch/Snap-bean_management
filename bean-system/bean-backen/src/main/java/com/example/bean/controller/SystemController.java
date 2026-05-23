package com.example.bean.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.bean.common.Result;
import com.example.bean.entity.Disease;
import com.example.bean.entity.Treatment;
import com.example.bean.mapper.DiseaseMapper;
import com.example.bean.mapper.TreatmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SystemController {

    @Autowired
    private DiseaseMapper diseaseMapper;
    @Autowired
    private TreatmentMapper treatmentMapper;

    // --- 病害管理 ---
    @GetMapping("/disease/list")
    public Result<List<Disease>> getDiseaseList(@RequestParam(defaultValue = "") String name) {
        QueryWrapper<Disease> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(name)) {
            wrapper.like("name", name);
        }
        wrapper.orderByDesc("create_time");
        return Result.success(diseaseMapper.selectList(wrapper));
    }

    @PostMapping("/disease/save")
    public Result<?> saveDisease(@RequestBody Disease disease) {
        if (disease.getId() == null) {
            diseaseMapper.insert(disease);
        } else {
            diseaseMapper.updateById(disease);
        }
        return Result.success(null);
    }

    @DeleteMapping("/disease/{id}")
    public Result<?> deleteDisease(@PathVariable Long id) {
        diseaseMapper.deleteById(id);
        return Result.success(null);
    }

    // --- 治疗信息管理 ---
    @GetMapping("/treatment/list")
    public Result<List<Treatment>> getTreatmentList(@RequestParam(defaultValue = "") String name) {
        QueryWrapper<Treatment> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(name)) {
            wrapper.like("disease_name", name);
        }
        wrapper.orderByDesc("create_time");
        return Result.success(treatmentMapper.selectList(wrapper));
    }

    @PostMapping("/treatment/save")
    public Result<?> saveTreatment(@RequestBody Treatment treatment) {
        if (treatment.getId() == null) {
            treatmentMapper.insert(treatment);
        } else {
            treatmentMapper.updateById(treatment);
        }
        return Result.success(null);
    }

    @DeleteMapping("/treatment/{id}")
    public Result<?> deleteTreatment(@PathVariable Long id) {
        treatmentMapper.deleteById(id);
        return Result.success(null);
    }
}