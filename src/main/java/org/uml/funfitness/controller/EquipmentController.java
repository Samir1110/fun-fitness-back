package org.uml.funfitness.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.uml.funfitness.pojo.Employee;
import org.uml.funfitness.pojo.Equipment;
import org.uml.funfitness.service.EquipmentService;

import java.util.List;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    //查询器材
    @RequestMapping("/selEquipment")
    public List<Equipment> selectEquipment(Model model, HttpSession session) {
        List<Equipment> equipmentList = equipmentService.findAll();
        return equipmentList;
    }

    //删除器材
    @RequestMapping(value = "/delEquipment", method = RequestMethod.POST)
    public String deleteEmployee(@RequestParam("equipmentId") Integer equipmentId) {
        equipmentService.deleteByEquipmentId(equipmentId);
        return "redirect:selEquipment";
    }



    //修改器材
    @RequestMapping(value = "/updateEquipment", method = RequestMethod.POST)
    public String updateEmployee(@RequestBody Equipment equipment) {
        equipmentService.updateEquipmentByEquipmentId(equipment);
        return "redirect:selEquipment";
    }


    //新增器材
    @RequestMapping(value = "/addEquipment", method = RequestMethod.POST)
    public String addEquipment(@RequestBody Equipment equipment) {
        equipmentService.insertEquipment(equipment);
        return "redirect:selEquipment";
    }
}
