package org.uml.funfitness.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.uml.funfitness.pojo.Admin;
import org.uml.funfitness.pojo.Equipment;
import org.uml.funfitness.pojo.Member;
import org.uml.funfitness.service.*;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private GymService gymService;

    //主界面即管理员登录
    @RequestMapping("/")
    public String toAdminLogin() {
        return "adminLogin";
    }

    //成员登录
    @RequestMapping("/toUserLogin")
    public String toUserLogin() {
        return "userLogin";
    }

    //管理员登录验证与数据
    @RequestMapping("/adminLogin")
    public String adminLogin(Admin admin, Model model, HttpSession session) {
        Admin admin1 = adminService.adminLogin(admin);
        if (admin1 != null) {
            //会员人数
            Integer memberTotal = memberService.selectTotalCount();
            model.addAttribute("memberTotal", memberTotal);
            session.setAttribute("memberTotal", memberTotal);

            //员工人数
            Integer employeeTotal = employeeService.selectTotalCount();
            model.addAttribute("employeeTotal", employeeTotal);
            session.setAttribute("employeeTotal", employeeTotal);

            //健身房总人数
            Integer humanTotal = memberTotal + employeeTotal;
            model.addAttribute("humanTotal", humanTotal);
            session.setAttribute("humanTotal", humanTotal);

            //器材数
            Integer equipmentTotal = equipmentService.selectTotalCount();
            model.addAttribute("equipmentTotal", equipmentTotal);
            session.setAttribute("equipmentTotal", equipmentTotal);

            //健身房当前人数
            Integer gymTotal = gymService.selectGymPeopleNumber();
            model.addAttribute("gymTotal", gymTotal);
            session.setAttribute("gymTotal", gymTotal);
            return "adminMain";
        }
        model.addAttribute("msg", "您输入的账号或密码有误，请重新输入！");
        return "adminLogin";
    }

    //会员登录
    @RequestMapping("/userLogin")
    public String userLogin(Member member, Model model, HttpSession session) {
        Member member1 = memberService.userLogin(member);
        if (member1 != null) {
            model.addAttribute("member", member1);
            session.setAttribute("user", member1);
            //健身房当前人数
            Integer gymTotal = gymService.selectGymPeopleNumber();
            model.addAttribute("gymTotal", gymTotal);
            session.setAttribute("gymTotal", gymTotal);
            return "userMain";
        }
        model.addAttribute("msg", "您输入的账号或密码有误，请重新输入！");
        return "userLogin";
    }

    //跳转管理员主页
    @RequestMapping("/toAdminMain")
    public String toAdminMain(Model model, HttpSession session) {
        Integer memberTotal = (Integer) session.getAttribute("memberTotal");
        Integer employeeTotal = (Integer) session.getAttribute("employeeTotal");
        Integer humanTotal = (Integer) session.getAttribute("humanTotal");
        Integer equipmentTotal = (Integer) session.getAttribute("equipmentTotal");
        Integer gymTotal = (Integer) session.getAttribute("gymTotal");
        model.addAttribute("memberTotal", memberTotal);
        model.addAttribute("employeeTotal", employeeTotal);
        model.addAttribute("humanTotal", humanTotal);
        model.addAttribute("equipmentTotal", equipmentTotal);
        model.addAttribute("gymTotal", gymTotal);
        return "adminMain";
    }

    //跳转会员主页
    @RequestMapping("/toUserMain")
    public String toUserMain(Model model, HttpSession session) {
        Member member = (Member) session.getAttribute("user");
        Integer gymTotal = (Integer) session.getAttribute("gymTotal");
        model.addAttribute("member", member);
        model.addAttribute("gymTotal", gymTotal);
        return "userMain";
    }
}
