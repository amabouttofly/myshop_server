package com.project.shop.service.inter.role;

import com.project.shop.pojo.role.Admin;

public interface AdminService {

    public Admin queryAdminByUsername(String username,String password);
}
