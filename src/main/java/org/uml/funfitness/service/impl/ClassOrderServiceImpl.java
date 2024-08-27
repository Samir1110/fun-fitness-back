package org.uml.funfitness.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uml.funfitness.mapper.ClassOrderMapper;
import org.uml.funfitness.pojo.ClassOrder;
import org.uml.funfitness.service.ClassOrderService;

import java.util.List;

@Service
public class ClassOrderServiceImpl implements ClassOrderService {

    @Autowired
    private ClassOrderMapper classOrderMapper;

    @Override
    public List<ClassOrder> findAll() {
        List<ClassOrder> classOrders = classOrderMapper.findAll();
        for (ClassOrder order : classOrders) {
            System.out.println(order);
        }
        return classOrders;
    }

    @Override
    public Boolean insertClassOrder(ClassOrder classOrder) {
        return classOrderMapper.insertClassOrder(classOrder);
    }

    @Override
    public List<ClassOrder> selectClassOrderByMemberAccount(Integer memberAccount) {
        List<ClassOrder> classOrders = classOrderMapper.selectClassOrderByMemberAccount(memberAccount);
        for (ClassOrder order : classOrders) {
            System.out.println(order);
        }
        return classOrders;
    }

    @Override
    public Boolean deleteByClassOrderId(Integer classOrderId) {
        return classOrderMapper.deleteByClassOrderId(classOrderId);
    }

    @Override
    public ClassOrder selectMemberByClassIdAndMemberAccount(Integer classId, Integer memberAccount) {
        ClassOrder order = classOrderMapper.selectMemberByClassIdAndMemberAccount(classId, memberAccount);
        System.out.println(order);
        return order;
    }

    @Override
    public List<ClassOrder> selectMemberOrderList(Integer classId) {
        List<ClassOrder> classOrders = classOrderMapper.selectMemberOrderList(classId);
        for (ClassOrder order : classOrders) {
            System.out.println(order);
        }
        return classOrders;
    }
}
