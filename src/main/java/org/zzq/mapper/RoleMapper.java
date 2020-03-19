package org.zzq.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.zzq.entities.SysRole;

import java.util.List;

@Mapper
public interface RoleMapper {

    @Select("SELECT r.id, r.role_name roleName, r.role_desc roleDesc " +
            "FROM sys_role r, sys_user_role ur " +
            "WHERE r.id=ur.rid AND ur.uid=#{uid}")
    List<SysRole> findByUid(Integer uid);

    @Select("select id, role_name roleName, role_desc roleDesc from sys_role")
    public List<SysRole> findAll();

    @Insert("insert into sys_role (role_name, role_desc) values (#{roleName}, #{roleDesc})")
    public void save(SysRole role);

}
