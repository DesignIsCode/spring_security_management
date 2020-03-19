package org.zzq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zzq.entities.SysRole;
import org.zzq.service.RoleService;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAll")
    public String findAll(Model model){
        List<SysRole> list = roleService.findAll();
        model.addAttribute("list", list);
        return "role-list";
    }

    @RequestMapping("/save")
    public String save(SysRole role){
        System.out.println(role.toString());
        roleService.save(role);
        return "redirect:findAll";
    }
}
