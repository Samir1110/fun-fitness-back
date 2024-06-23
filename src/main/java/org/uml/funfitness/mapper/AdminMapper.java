package org.uml.funfitness.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.uml.funfitness.pojo.Admin;

@Mapper
public interface AdminMapper {

    Admin selectByAccountAndPassword(Admin admin);
}
