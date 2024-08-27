package org.uml.funfitness.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.uml.funfitness.pojo.ClassOrder;
import org.uml.funfitness.pojo.ClassTable;
import org.uml.funfitness.pojo.Equipment;
import org.uml.funfitness.service.ClassOrderService;
import org.uml.funfitness.service.ClassTableService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassTableService classTableService;

    @Autowired
    private ClassOrderService classOrderService;

    //查询课程
    @RequestMapping("/selClass")
    public List<ClassTable> selectClass(Model model) {
        List<ClassTable> classList = classTableService.findAll();
        return classList;
    }


    //新增课程
    @RequestMapping(value = "/addClass", method = RequestMethod.POST)
    public String addClass(@RequestBody ClassTable classTable) {
        classTableService.insertClass(classTable);
        return "redirect:selClass";
    }

    //删除课程
    @RequestMapping(value = "/delClass", method = RequestMethod.POST)
    public String deleteClass(@RequestParam("classId") Integer classId) {
        classTableService.deleteClassByClassId(classId);
        classTableService.deleteOrderByClassId(classId);
        return "redirect:selClass";
    }


    //查询课程报名信息
    @GetMapping("/selClassOrder")
    public ResponseEntity<Map<String, Object>> selectClassOrder(@RequestParam Integer classOrderId) {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println("Received classOrderId: " + classOrderId);
            List<ClassOrder> classOrderList = classOrderService.selectMemberOrderList(classOrderId);

            // 将用户信息单独列出
            List<Map<String, Object>> memberList = new ArrayList<>();
            for (ClassOrder order : classOrderList) {
                Map<String, Object> memberInfo = new HashMap<>();
                memberInfo.put("memberName", order.getMemberName());
                memberInfo.put("memberAccount", order.getMemberAccount());
                memberList.add(memberInfo);
            }

            if (!classOrderList.isEmpty()) {
                ClassOrder firstOrder = classOrderList.get(0);
                response.put("classId", firstOrder.getClassId());
                response.put("className", firstOrder.getClassName());
                response.put("coach", firstOrder.getCoach());
                response.put("classBegin", firstOrder.getClassBegin());
            }
            response.put("members", memberList);

            System.out.println("Response Data: " + response);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace(); // 打印异常堆栈
            response.put("error", "Internal Server Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //修改课程
    @PostMapping("/updateClass")
    public ResponseEntity<String> updateClass(@RequestBody ClassTable classTable) {
        Boolean success = classTableService.updateClassTable(classTable);
        if (success) {
            return ResponseEntity.ok("课程更新成功");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("课程更新失败");
        }
    }

}
