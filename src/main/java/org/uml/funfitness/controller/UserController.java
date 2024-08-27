package org.uml.funfitness.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.uml.funfitness.pojo.ClassOrder;
import org.uml.funfitness.pojo.ClassTable;
import org.uml.funfitness.pojo.Member;
import org.uml.funfitness.service.ClassOrderService;
import org.uml.funfitness.service.ClassTableService;
import org.uml.funfitness.service.MemberService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ClassTableService classTableService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ClassOrderService classOrderService;


    // 跳转个人信息页面
    @GetMapping("/toUserInfo")
    public ResponseEntity<Map<String, Object>> toUserInformation(@RequestParam Integer memberAccount) {
        Map<String, Object> result = new HashMap<>();
        System.out.println(memberAccount);
        try {
            List<Member> members = memberService.findByAccount(memberAccount);
            if (!members.isEmpty()) {
                result.put("success", true);
                result.put("members", members);
                return ResponseEntity.ok(result);
            } else {
                result.put("success", false);
                result.put("message", "用户未找到");
                System.out.println("用户未找到");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
        } catch (Exception e) {
            // 打印异常信息
            e.printStackTrace();
            result.put("success", false);
            result.put("message", "服务器内部错误");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }


    // 修改个人信息
    @PostMapping("/updateInfo")
    public ResponseEntity<Map<String, Object>> updateUserInformation(@RequestBody Member member) {
        Map<String, Object> result = new HashMap<>();
        try {
            // 通过memberAccount查找用户
            List<Member> members = memberService.findByAccount(member.getMemberAccount());
            if (members.isEmpty()) {
                result.put("success", false);
                result.put("message", "用户未找到");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }

            Member existingMember = members.get(0);

            // 只更新可以修改的字段
            existingMember.setMemberAge(member.getMemberAge());
            existingMember.setMemberHeight(member.getMemberHeight());
            existingMember.setMemberWeight(member.getMemberWeight());
            existingMember.setMemberPhone(member.getMemberPhone());

            memberService.updateMemberByMemberAccount(existingMember);
            result.put("success", true);
            result.put("message", "信息更新成功");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("message", "服务器内部错误");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }
    //跳转我的课程页面
    @RequestMapping("/toUserClass")
    public List<ClassOrder> toUserClass(@RequestParam Integer memberAccount) {
        List<ClassOrder> classOrderList = classOrderService.selectClassOrderByMemberAccount(memberAccount);
        return classOrderList;
    }

    // 退课
    @RequestMapping("/delUserClass")
    public ResponseEntity<Map<String, Object>> deleteUserClass(@RequestParam Integer classOrderId) {
        System.out.println("Received classOrderId: " + classOrderId); // 添加日志输出

        Map<String, Object> result = new HashMap<>();

        Boolean affectedRows = classOrderService.deleteByClassOrderId(classOrderId);
        System.out.println("Affected rows: " + affectedRows); // 添加日志输出

        if (affectedRows == true) {
            result.put("success", true);
            result.put("message", "退课成功");
        } else {
            result.put("success", false);
            result.put("message", "退课失败，未找到对应的课程订单");
        }

        return ResponseEntity.ok(result);
    }


    //报名选课
    @PostMapping("/applyClass")
    public ResponseEntity<Map<String, Object>> userApplyClass(@RequestParam Integer classId, @RequestParam Integer memberAccount) {
        System.out.println( classId);
        System.out.println( memberAccount);
        Map<String, Object> result = new HashMap<>();

        List<Member> members = memberService.findByAccount(memberAccount);
        if (members.isEmpty()) {
            result.put("success", false);
            result.put("message", "用户未找到");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        Member member = members.get(0);

        ClassTable classTable = classTableService.selectByClassId(classId);
        if (classTable == null) {
            result.put("success", false);
            result.put("message", "课程未找到");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        Integer classId1 = classTable.getClassId();
        String className = classTable.getClassName();
        String coach = classTable.getCoach();
        String classBegin = classTable.getClassBegin();
        String memberName = member.getMemberName();
        Integer memberAccount1 = member.getMemberAccount();

        ClassOrder classOrder = new ClassOrder(classId1, className, coach, memberName, memberAccount1, classBegin);
        ClassOrder classOrder1 = classOrderService.selectMemberByClassIdAndMemberAccount(classId1, memberAccount1);

        if (classOrder1 == null) {
            classOrderService.insertClassOrder(classOrder);
            result.put("success", true);
            result.put("message", "选课成功");
            return ResponseEntity.ok(result);
        } else {
            result.put("success", false);
            result.put("message", "已经选过此课程");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
    }
}
