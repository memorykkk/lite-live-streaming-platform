package com.xinf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinf.dao.RoleDao;
import com.xinf.entity.Role;
import com.xinf.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * (Role)表服务实现类
 *
 * @author makejava
 * @since 2021-08-31 19:25:17
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {
    @Override
    public Map<String, Object> getSimpleInfo(int roleId) {
        return getMap(new QueryWrapper<Role>().select("role_name, role_identification").eq("role_id", roleId));
    }
}
