package org.uml.funfitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.uml.funfitness.pojo.Employee;
import org.uml.funfitness.pojo.Member;
import org.uml.funfitness.service.MemberService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    //查询会员
    @RequestMapping("/selMember")
    public List<Member> selectMember(Model model) {
        List<Member> memberList = memberService.findAll();
        return memberList;
    }


    //新增会员
    @RequestMapping(value = "/addMember", method = RequestMethod.POST)
    public String addMember(@RequestBody Member member) {
        //会员账号&卡号随机生成
        Random random = new Random();
        String account1 = "2024";
        for (int i = 0; i < 5; i++) {
            account1 += random.nextInt(10);
        }
        Integer account = Integer.parseInt(account1);

        //初始密码
        String password = "123456";

        //获取当前日期
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowDay = simpleDateFormat.format(date);

        Integer nextClass = member.getCardClass();

        member.setMemberAccount(account);
        member.setMemberPassword(password);
        member.setCardTime(nowDay);
        member.setCardNextClass(nextClass);
        System.out.println(member.getMemberCredit());
        memberService.insertMember(member);
        return "redirect:selMember";

    }

    //删除会员
    @RequestMapping(value = "/delMember", method = RequestMethod.POST)
    public String deleteMember(@RequestParam("memberAccount") Integer memberAccount) {
        memberService.deleteByMemberAccount(memberAccount);
        return "redirect:selMember";
    }


    //修改会员信息
    @RequestMapping(value = "/updateMember", method = RequestMethod.POST)
    public String updateMember(@RequestBody Member member) {
        memberService.updateMemberByMemberAccount(member);
        return "redirect:selMember";
    }


    //根据会员卡号查询
    @RequestMapping("/selByCard")
    public String selectByCardId(Model model, Integer memberAccount) {
        List<Member> memberList = memberService.selectByMemberAccount(memberAccount);
        if (memberList != null) {
            model.addAttribute("memberList", memberList);
        } else {
            String message = "会员卡号不存在！";
            model.addAttribute("noMessage", message);
        }
        return "selectByMemberAccount";
    }

}
