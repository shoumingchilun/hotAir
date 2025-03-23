package com.chilun.hotAir.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chilun.hotAir.mapper.ExperimentMapper;
import com.chilun.hotAir.model.entity.Experiment;
import com.chilun.hotAir.service.ExperimentService;
import org.springframework.stereotype.Service;

/**
* @author 齿轮
* @description 针对表【experiment(实验记录表，记录实验的基本信息)】的数据库操作Service实现
* @createDate 2025-03-23 13:48:56
*/
@Service
public class ExperimentServiceImpl extends ServiceImpl<ExperimentMapper, Experiment>
    implements ExperimentService {

}




