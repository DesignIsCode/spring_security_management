package org.zzq.service;

import org.zzq.entities.SysRole;

import java.util.List;

public interface RoleService {

    List<SysRole> findAll();

    void save(SysRole role);
}
