package org.uml.funfitness.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uml.funfitness.mapper.AdminMapper;
import org.uml.funfitness.pojo.Admin;
import org.uml.funfitness.service.AdminService;

//@Service
//public class AdminServiceImpl implements AdminService {
//
//    @Autowired
//    private AdminMapper adminMapper;
//
//    @Override
//    public Admin adminLogin(Admin admin) {
//        return adminMapper.selectByAccountAndPassword(admin);
//    }
//}
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AdminServiceImpl implements AdminService {
    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin adminLogin(Admin admin) {
        logger.info("Attempting login with account: {}", admin.getAdminAccount());
        Admin result = adminMapper.selectByAccountAndPassword(admin);
        if (result == null) {
            logger.warn("Login failed for account: {}", admin.getAdminAccount());
        }
        return result;
    }
}
