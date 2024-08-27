package org.uml.funfitness;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.uml.funfitness.mapper.GymMapper;
import org.uml.funfitness.mapper.MemberMapper;
import org.uml.funfitness.pojo.Gym;

import java.util.List;

@SpringBootTest
class FunFitnessApplicationTests {

	@Autowired
	MemberMapper memberMapper;
	@Autowired
	GymMapper gymMapper;
	@Test
	void contextLoads() {

//		gymMapper.insertGymPeopleNumber(202009867);
//		gymMapper.insertGymPeopleNumber(202100788);
//		gymMapper.insertGymPeopleNumber(202106725);
//		gymMapper.deleteGymPeopleNumber(202176587);

		List<Gym> list = gymMapper.findAll();

		for (Gym member : list) {
			System.out.println(member);
		}
		System.out.println(gymMapper.selectGymPeopleNumber());
	}

}
