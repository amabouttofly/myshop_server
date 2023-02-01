package com.project.shop.service.impl.role;

import com.project.shop.dao.role.AdminMapper;
import com.project.shop.pojo.role.Admin;
import com.project.shop.service.inter.role.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AdminServiceImpl implements AdminService {
    private AdminMapper adminMapper;

    @Override
    public Admin queryAdminByUsername(String username,String password) {
        return adminMapper.queryAdminByUsername(username,password);
    }

    @Autowired
    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }
}
