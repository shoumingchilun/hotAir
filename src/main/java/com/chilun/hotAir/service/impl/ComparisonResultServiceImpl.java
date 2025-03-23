package com.chilun.hotAir.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chilun.hotAir.mapper.ComparisonResultMapper;
import com.chilun.hotAir.model.entity.ComparisonResult;
import com.chilun.hotAir.service.ComparisonResultService;
import org.springframework.stereotype.Service;

/**
* @author 齿轮
* @description 针对表【comparison_result(实验对比结果表，存储 PSO 与人工优化的对比数据)】的数据库操作Service实现
* @createDate 2025-03-23 13:48:56
*/
@Service
public class ComparisonResultServiceImpl extends ServiceImpl<ComparisonResultMapper, ComparisonResult>
    implements ComparisonResultService {

}




