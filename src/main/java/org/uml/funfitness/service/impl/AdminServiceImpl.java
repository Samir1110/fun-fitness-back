package org.uml.funfitness.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uml.funfitness.mapper.AdminMapper;
import org.uml.funfitness.pojo.Admin;
import org.uml.funfitness.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin adminLogin(Admin admin) {
        return adminMapper.selectByAccountAndPassword(admin);
    }

}
