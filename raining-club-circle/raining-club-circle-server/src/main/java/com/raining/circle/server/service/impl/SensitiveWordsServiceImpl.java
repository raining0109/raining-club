package com.raining.circle.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.raining.circle.server.dao.SensitiveWordsMapper;
import com.raining.circle.server.entity.po.SensitiveWords;
import com.raining.circle.server.service.SensitiveWordsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 敏感词表 服务实现类
 * </p>
 *
 * @author raining
 * @since 2024/05/17
 */
@Service
public class SensitiveWordsServiceImpl extends ServiceImpl<SensitiveWordsMapper, SensitiveWords> implements SensitiveWordsService {

}
