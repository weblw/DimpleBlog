package com.dimple.project.system.service.impl;

import com.dimple.common.constant.UserConstants;
import com.dimple.common.utils.SecurityUtils;
import com.dimple.common.utils.StringUtils;
import com.dimple.project.system.domain.Config;
import com.dimple.project.system.mapper.ConfigMapper;
import com.dimple.project.system.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @className: ConfigServiceImpl
 * @description: 参数配置 服务层实现
 * @author: Dimple
 * @date: 10/22/19
 */
@Service
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    private ConfigMapper configMapper;

    @Override
    public Config selectConfigById(Long configId) {
        Config config = new Config();
        config.setId(configId);
        return configMapper.selectConfig(config);
    }

    @Override
    public Config selectConfigByKey(String configKey) {
        Config config = new Config();
        config.setConfigKey(configKey);
        Config retConfig = configMapper.selectConfig(config);
        return Objects.isNull(retConfig) ? new Config() : retConfig;
    }

    @Override
    public List<Config> selectConfigList(Config config) {
        return configMapper.selectConfigList(config);
    }

    @Override
    public int insertConfig(Config config) {
        return configMapper.insertConfig(config);
    }

    @Override
    public int updateConfig(Config config) {
        return configMapper.updateConfig(config);
    }

    @Override
    public int updateConfigByConfigKey(Config config) {
        return configMapper.updateConfigByConfigKey(config);
    }

    @Override
    public int deleteConfigById(Long id) {
        String loginUsername = SecurityUtils.getUsername();
        return configMapper.deleteConfigById(id, loginUsername);
    }

    @Override
    public String checkConfigKeyUnique(Config config) {
        Long id = StringUtils.isNull(config.getId()) ? -1L : config.getId();
        Config info = configMapper.checkConfigKeyUnique(config.getConfigKey());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != id.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}