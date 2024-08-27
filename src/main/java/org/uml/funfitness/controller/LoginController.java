package org.uml.funfitness.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.uml.funfitness.pojo.Admin;
import org.uml.funfitness.pojo.Member;
import org.uml.funfitness.service.AdminService;
import org.uml.funfitness.service.MemberService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@RestController
public class LoginController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private MemberService memberService;

    @PostMapping("/adminLogin")
    @ResponseBody
    public Map<String, Object> adminLogin(@RequestBody Admin admin, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        System.out.println("Received admin login request: " + admin);
        Admin admin1 = adminService.adminLogin(admin);
        if (admin1 != null) {
            session.setAttribute("admin", admin1);
            // Mock a token for the logged-in user
            String token = "editor-token";
            Map<String, String> data = new HashMap<>();
            data.put("token", token);
            result.put("code", 20000);
            result.put("data", data);
        } else {
            result.put("code", 60204);
            result.put("message", "Account and password are incorrect.");
        }
        return result;
    }



    @PostMapping("/userLogin")
    @ResponseBody
    public Map<String, Object> userLogin(@RequestBody Member member, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        System.out.println("Received member login request: " + member);
        Member member1 = memberService.userLogin(member);
        if (member1 != null) {
            session.setAttribute("user", member1);
            result.put("success", true);
        } else {
            result.put("success", false);
            result.put("message", "您输入的账号或密码有误，请重新输入！");
        }
        return result;
    }
}
