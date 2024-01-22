package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.StaffType;
import com.nju.edu.erp.model.po.CheckInPO;
import com.nju.edu.erp.model.po.CheckInRecordPO;
import com.nju.edu.erp.model.vo.CheckInVO;
import com.nju.edu.erp.model.po.StaffPO;
import com.nju.edu.erp.model.vo.StaffVO;

import java.util.List;

public interface StaffService {
    /**
     * 增加员工
     * @param inputVO 输入信息
     */

    StaffVO createStaff(StaffVO inputVO);

    /**
     * 根据id更新员工信息
     * @param staffPO 员工信息
     */
    void updateStaff(StaffPO staffPO);

//    /**
//     * 根据type查找对应类型的员工
//     * @param type 员工类型
//     * @return 员工列表
//     */
//    List<StaffPO> getStaffsByType(StaffType type);


    StaffPO findStaffById(Integer supplier);

    /**
     * 根据id删除对应员工
     * @param id 员工id
     */
    void deleteById(Integer id);

    /**
     * 按照id大小顺序获取所用员工
     * @return 员工列表
     */
    List<StaffPO> getStaffsById();

    /**
     * 获取用户打卡列表
     * @param id 员工id
     * @return 一个id员工的所有打卡记录
     */
    List<CheckInPO> getCheckInById(Integer id);

    /**
     * 为用户打卡
     * @param inputVO 为当前id的用户创建一条打卡记录
     *
     */
    CheckInVO createCheck(CheckInVO inputVO);

    /**
     * 获取所有id用户的最迟打卡记录
     * @return 最迟打卡记录列表
     */
    List<CheckInPO> getAllLatest();

    /**
     * 获取当前id号的员工的最迟打卡记录
     * @param id 员工id
     * @return 员工的最迟打卡记录
     */
    CheckInPO findIdLatest(Integer id);

    /**
     * 查询所有员工本月的打卡次数
     * @return 打卡次数列表
     */
    List<CheckInRecordPO> findAllCheckInThisMonth();

    /**
     * 查询所有员工上个月的打卡次数
     * @return 打卡次数列表
     */
    List<CheckInRecordPO> findAllCheckInLastMonth();

    /**
     * 查询当前员工的上个月打卡次数
     * @param id 员工id
     * @return 员工打卡次数
     */
    Integer findCheckInLastMonthById(Integer id);

    /**
     * 删除员工的打卡记录
     * @param id 员工id
     */
    void deleteCheckInById(Integer id);

    /**
     * 根据员工职位返回对应的工资策略
     * @param position 岗位
     * @return 工资策略
     */
    String findStrategyByPosition(String position);

    /**
     * 根据员工职位返回对应的岗位工资
     * @param position 岗位
     * @return 岗位工资
     */
    Integer findPositionSalary(String position);


    /**
     * 根据员工的姓名返回员工的岗位
     * @param name 员工姓名
     * @return 员工的岗位
     */
    String findStaffTypeByName(String name);

    /**
     * 根据id查找员工的基本工资
     * @param id 员工id
     * @return 员工的基本工资
     */
    Integer findBaseSalary(Integer id);




}
