package org.zzq.mapper;

import org.apache.ibatis.annotations.*;
import org.zzq.entities.SysUser;
import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from sys_user where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", column = "id", javaType = List.class,
                    many = @Many(select = "org.zzq.mapper.RoleMapper.findByUid"))
    })
    SysUser findByName(String username);

    @Insert("insert into sys_user (username, password) values (#{username}, #{password})")
    public void save(SysUser user);

    @Select("select * from sys_user")
    public List<SysUser> findAll();

    @Delete("delete from sys_user_role where uid=#{userId}")
    public void removeRoles(Integer userId);

    @Insert("insert into sys_user_role(uid,rid) values (#{uid}, #{rid})")
    public void addRoles(@Param("uid") Integer userId, @Param("rid") Integer rid);

    @Select("SELECT r.id FROM sys_role r, sys_user_role ur " +
            "WHERE ur.rid=r.id AND ur.uid=#{uid}")
    public List<Integer> findRolesByUid(Integer id);

}
