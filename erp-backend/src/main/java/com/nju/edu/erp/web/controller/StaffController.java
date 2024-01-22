package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.enums.StaffType;
import com.nju.edu.erp.model.vo.CheckInVO;
import com.nju.edu.erp.model.po.StaffPO;
import com.nju.edu.erp.model.vo.StaffVO;
import com.nju.edu.erp.service.SalaryService;
import com.nju.edu.erp.service.StaffService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/staff")
public class StaffController {
    private StaffService staffService;
    private SalaryService salaryService;

    @Autowired
    public StaffController(StaffService staffService,SalaryService salaryService) {
        this.staffService = staffService;
        this.salaryService = salaryService;
    }

//    @GetMapping("/findByType")
//    public Response findByType(@RequestParam StaffType type) {
//        return Response.buildSuccess(staffService.getStaffsByType(type));
//    }

    @PostMapping("/createStaff")
    public Response addStaff(@RequestBody StaffVO staffVO) {
        return Response.buildSuccess(staffService.createStaff(staffVO));
    }

    @PostMapping("/updateOne")
    public Response updateStaff(@RequestBody StaffVO staffVO) {
        //将传入的VO转为PO
        StaffPO staffPO = new StaffPO();
        BeanUtils.copyProperties(staffVO, staffPO);
        staffService.updateStaff(staffPO);
        return Response.buildSuccess();
    }

    @GetMapping("/deleteById")
    public Response deleteById(@RequestParam(value = "id") int id) {
        staffService.deleteById(id);
        return Response.buildSuccess();
    }

    @GetMapping("findById")
    public Response findById(){
        return Response.buildSuccess(staffService.getStaffsById());
    }

    @PostMapping("addCheckIn")
    public Response addCheckIn(@RequestBody CheckInVO checkInVO) {
        return Response.buildSuccess(staffService.createCheck(checkInVO));
    }

    @GetMapping("findCheckById")
    public Response findCheckById(@RequestParam Integer id){
        return Response.buildSuccess(staffService.getCheckInById(id));
    }

    @GetMapping("getAllLatest")
    public Response getAllLatest(){
        return Response.buildSuccess(staffService.getAllLatest());
    }

    @GetMapping("findIdLatest")
    public Response findIdLatest(@RequestParam(value = "id") Integer id){
        return Response.buildSuccess(staffService.findIdLatest(id));
    }

    @GetMapping("findAllCheckInThisMonth")
    public Response findAllCheckInThisMonth(){
        return Response.buildSuccess(staffService.findAllCheckInThisMonth());
    }

    @GetMapping("findAllCheckInLastMonth")
    public Response findAllCheckInLastMonth(){
        return Response.buildSuccess(staffService.findAllCheckInLastMonth());
    }

    @GetMapping("deleteCheckInById")
    public Response deleteCheckInById(@RequestParam(value = "id") int id) {
        staffService.deleteCheckInById(id);
        return Response.buildSuccess();
    }


    @GetMapping("findStrategyByPosition")
    public Response findStrategyByPosition(String position){
        return Response.buildSuccess(staffService.findStrategyByPosition(position));
    }

    @GetMapping("findPositionSalary")
    public Response findPositionSalary(String position){

        return Response.buildSuccess(staffService.findPositionSalary(position));
    }

    @GetMapping("findCommission")
    public Response findCommission(String name){
        return Response.buildSuccess(salaryService.findCommission(name));
    }


}
