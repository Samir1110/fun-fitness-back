package org.uml.funfitness.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public String selectEquipment(Model model, HttpSession session) {
        List<Equipment> equipmentList = equipmentService.findAll();
        session.setAttribute("equipmentList", equipmentList);
        model.addAttribute("equipmentList", equipmentList);
        return "selectEquipment";
    }

    //删除器材
    @RequestMapping("/delEquipment")
    public String deleteEquipment(Integer equipmentId) {
        equipmentService.deleteByEquipmentId(equipmentId);
        return "redirect:selEquipment";
    }

    //跳转修改器材页面
    @RequestMapping("/toUpdateEquipment")
    public String toUpdateEquipment(Integer equipmentId, Model model) {
        List<Equipment> equipmentList = equipmentService.selectByEquipmentId(equipmentId);
        model.addAttribute("equipmentList", equipmentList);
        return "updateEquipment";
    }

    //修改器材
    @RequestMapping("/updateEquipment")
    public String updateEquipment(Equipment equipment) {
        equipmentService.updateEquipmentByEquipmentId(equipment);
        return "redirect:selEquipment";
    }

    //跳转新增器材页面
    @RequestMapping("/toAddEquipment")
    public String toAddEquipment() {
        return "addEquipment";
    }

    //新增器材
    @RequestMapping("/addEquipment")
    public String addEquipment(Equipment equipment) {
        equipmentService.insertEquipment(equipment);
        return "redirect:selEquipment";
    }
}
