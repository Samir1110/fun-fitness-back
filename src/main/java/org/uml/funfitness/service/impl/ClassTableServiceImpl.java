package org.uml.funfitness.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uml.funfitness.mapper.ClassTableMapper;
import org.uml.funfitness.pojo.ClassTable;
import org.uml.funfitness.service.ClassTableService;

import java.util.List;

@Service
public class ClassTableServiceImpl implements ClassTableService {

    @Autowired
    private ClassTableMapper classTableMapper;

    @Override
    public List<ClassTable> findAll() {
        return classTableMapper.findAll();
    }

    @Override
    public Boolean deleteClassByClassId(Integer classId) {
        return classTableMapper.deleteClassByClassId(classId);
    }

    @Override
    public Boolean insertClass(ClassTable classTable) {
        return classTableMapper.insertClass(classTable);
    }

    @Override
    public ClassTable selectByClassId(Integer classId) {
        return classTableMapper.selectByClassId(classId);
    }

    @Override
    public Boolean deleteOrderByClassId(Integer classId) {
        return classTableMapper.deleteOrderByClassId(classId);
    }
}
