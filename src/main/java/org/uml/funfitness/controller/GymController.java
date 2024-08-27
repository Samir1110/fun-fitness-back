package org.uml.funfitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.uml.funfitness.pojo.Gym;
import org.uml.funfitness.service.GymService;
import org.uml.funfitness.pojo.Member;
import org.uml.funfitness.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/gym")
public class GymController {

    @Autowired
    private GymService gymService;

    @Autowired
    private MemberService memberService;

    //查询当前人数列表
    @RequestMapping("/selGymPeople")
    public List<Gym> selectGym() {
        List<Gym> gymPeopleList = gymService.findAll();
        return gymPeopleList;
    }


    //添加新人(按照会员账号）
    @PostMapping("/AddPeople")
    public String addPeople(@RequestParam Integer memberAccount){
        gymService.insertGymPeopleNumber(memberAccount);
        List<Member> members = memberService.findByAccount(memberAccount);
        Member member = members.get(0);
        member.setMemberCredit(member.getMemberCredit() + 1);
        memberService.updateMemberByMemberAccount(member);
        return "redirect:selGymPeople";
    }

    //删除人员
    @RequestMapping("/deletePeople")
    public String deletePeople(Integer memberAccount){
        gymService.deleteGymPeopleNumber(memberAccount);
        return "redirect:selPeople";
    }
}
