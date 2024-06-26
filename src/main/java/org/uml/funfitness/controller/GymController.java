package org.uml.funfitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uml.funfitness.pojo.Gym;
import org.uml.funfitness.service.GymService;

import java.util.List;

@RestController
@RequestMapping("/gym")
public class GymController {

    @Autowired
    private GymService gymService;

    //查询当前人数列表
    @RequestMapping("/selGymPeople")
    public String selectGym(Model model) {
        List<Gym> gymPeopleList = gymService.findAll();
        model.addAttribute("gymPeopleList", gymPeopleList);
        return "selectGym";
    }

    //跳转添加新人界面
    @RequestMapping("/toAddPeople")
    public String toAddPeople() {
        return "addPeople";
    }

    //添加新人(按照会员账号）
    @RequestMapping("/AddPeople")
    public String addPeople(Integer memberAccount){
        gymService.insertGymPeopleNumber(memberAccount);
        return "redirect:selGymPeople";
    }

    //删除人员
    @RequestMapping("/deletePeople")
    public String deletePeople(Integer memberAccount){
        gymService.deleteGymPeopleNumber(memberAccount);
        return "redirect:selPeople";
    }
}
