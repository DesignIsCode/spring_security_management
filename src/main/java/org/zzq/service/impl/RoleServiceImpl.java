package org.zzq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zzq.entities.SysRole;
import org.zzq.mapper.RoleMapper;
import org.zzq.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public List<SysRole> findAll() {
        return roleMapper.findAll();
    }

    public void save(SysRole role) {
        roleMapper.save(role);
    }
}
