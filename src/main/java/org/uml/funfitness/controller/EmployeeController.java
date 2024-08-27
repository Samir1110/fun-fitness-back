package org.uml.funfitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.uml.funfitness.pojo.Employee;
import org.uml.funfitness.service.EmployeeService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //查询员工
    @RequestMapping("/selEmployee")
    public List<Employee> selectEmployee(Model model) {
        List<Employee> employeeList = employeeService.findAll();
        return employeeList;
    }



    //新增员工
    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public String addEmployee(@RequestBody Employee employee) {
        // 工号随机生成
        Random random = new Random();
        String account1 = "1010";
        for (int i = 0; i < 5; i++) {
            account1 += random.nextInt(10);
        }
        Integer account = Integer.parseInt(account1);

        // 获取当前日期
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowDay = simpleDateFormat.format(date);

        employee.setEmployeeAccount(account);
        employee.setEntryTime(nowDay);

        employeeService.insertEmployee(employee);
        return "redirect:selEmployee";
    }

    // 删除员工
    @RequestMapping(value = "/delEmployee", method = RequestMethod.POST)
    public String deleteEmployee(@RequestParam("employeeAccount") Integer employeeAccount) {
        employeeService.deleteByEmployeeAccount(employeeAccount);
        return "redirect:selEmployee";
    }



    //修改员工信息
    @RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
    public String updateEmployee(@RequestBody Employee employee) {
        employeeService.updateMemberByEmployeeAccount(employee);
        return "redirect:selEmployee";
    }

}
