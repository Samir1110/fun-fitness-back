package org.uml.funfitness.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uml.funfitness.mapper.GymMapper;
import org.uml.funfitness.pojo.Gym;
import org.uml.funfitness.service.GymService;

import java.util.List;

@Service
public class GymServiceImpl implements GymService {

    @Autowired
    private GymMapper gymMapper;

    @Override
    public Boolean insertGymPeopleNumber(Integer id) {
        return gymMapper.insertGymPeopleNumber(id);
    }

    @Override
    public Boolean deleteGymPeopleNumber(Integer id) {
        return gymMapper.deleteGymPeopleNumber(id);
    }

    @Override
    public Integer selectGymPeopleNumber() {
        return gymMapper.selectGymPeopleNumber();
    }

    @Override
    public List<Gym> findAll() {
        return gymMapper.findAll();
    }
}
