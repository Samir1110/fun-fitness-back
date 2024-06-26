package org.uml.funfitness.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.uml.funfitness.pojo.Gym;

import java.util.List;

@Mapper
public interface GymMapper {

    //增加健身房实时人员
    Boolean insertGymPeopleNumber (Integer memberAccount);

    //删除健身房实时人员
    Boolean deleteGymPeopleNumber (Integer memberAccount);

    //查询健身房人数
    Integer selectGymPeopleNumber ();

    List<Gym> findAll();
}
