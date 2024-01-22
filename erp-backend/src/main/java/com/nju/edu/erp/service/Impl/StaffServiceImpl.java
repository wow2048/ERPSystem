package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.CheckInDao;
import com.nju.edu.erp.dao.StaffDao;
import com.nju.edu.erp.enums.StaffType;
import com.nju.edu.erp.exception.MyServiceException;
import com.nju.edu.erp.model.po.CheckInPO;
import com.nju.edu.erp.model.po.CheckInRecordPO;
import com.nju.edu.erp.model.vo.CheckInVO;
import com.nju.edu.erp.model.po.StaffPO;
import com.nju.edu.erp.model.vo.StaffVO;
import com.nju.edu.erp.service.Impl.SalaryStrategy.GetSalary;
import com.nju.edu.erp.service.StaffService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class StaffServiceImpl implements StaffService {
    private final StaffDao staffDao;
    private final CheckInDao checkInDao;


    @Autowired
    public StaffServiceImpl(StaffDao staffDao,CheckInDao checkInDao) {
        this.staffDao = staffDao;
        this.checkInDao = checkInDao;
    }


    /**
     * 增加客户
     *
     * @param inputVO 输入信息
     */
    @Override
    public StaffVO createStaff(StaffVO inputVO){
        StaffPO staff = staffDao.findOneById(inputVO.getId());
        if (staff != null){
            throw new MyServiceException("D0000", "员工已存在");
        }
        StaffPO staffPO = new StaffPO();
        BeanUtils.copyProperties(inputVO, staffPO);
        staffDao.createStaff(staffPO);
        return inputVO;
    }

    /**
     * 根据id更新员工信息
     *
     * @param staffPO 员工信息
     */
    @Override
    public void updateStaff(StaffPO staffPO) {
        staffDao.updateOne(staffPO);
    }

//    /**
//     * 根据type查找对应类型的员工
//     *
//     * @param type 员工类型
//     * @return 员工列表
//     */
//    @Override
//    public List<StaffPO> getStaffsByType(StaffType type) {
//
//        return staffDao.findAllByType(type.getValue());
//    }

    @Override
    public StaffPO findStaffById(Integer supplier) {
        return staffDao.findOneById(supplier);
    }

    /**
     * 根据id删除对应员工
     *
     * @param id 员工id
     */
    @Override
    public void deleteById(Integer id){
        staffDao.deleteById(id);
    }

    /**
     * 返回所有员工的列表，按照ID排序
     * @return 员工列表
     */
    @Override
    public List<StaffPO> getStaffsById(){
        return staffDao.findAllById();
    }

    /**
     *
     * @param id 员工id
     * @return 员工的打卡列表
     */
    @Override
    public List<CheckInPO> getCheckInById(Integer id){
        return checkInDao.findAllById(id);
    }

    /**
     *
     * @param inputVO 为当前id的用户创建一条打卡记录
     *
     */
    @Override
    public CheckInVO createCheck(CheckInVO inputVO){
        CheckInPO checkInPO = new CheckInPO();
        BeanUtils.copyProperties(inputVO, checkInPO);
        checkInDao.addCheckIn(checkInPO);
        return inputVO;
    }

    /**
     * 所有员工的最迟打卡记录
     * @return 最迟打卡记录列表
     */
    @Override
    public List<CheckInPO> getAllLatest(){
        return checkInDao.findAll();
    }

    /**
     * 当前id员工的最迟打卡记录
     * @param id 员工id
     * @return 最迟打卡记录
     */
    @Override
    public CheckInPO findIdLatest(Integer id){
        return checkInDao.findById(id);
    }

    /**
     * 查询所有员工本月打卡次数
     * @return 打卡次数列表
     */
    @Override
    public List<CheckInRecordPO> findAllCheckInThisMonth(){
        return checkInDao.findAllCheckInThisMonth();
    }

    /**
     * 查询所有员工上个月的打卡次数
     * @return 打卡次数列表
     */
    @Override
    public List<CheckInRecordPO> findAllCheckInLastMonth(){
        return checkInDao.findAllCheckInLastMonth();
    }

    /**
     * 查询当前员工的上个月打卡次数
     * @param id 员工id
     * @return 员工打卡次数
     */
    @Override
    public Integer findCheckInLastMonthById(Integer id){
        return checkInDao.findCheckInLastMonthById(id);
    }

    /**
     * 删除员工的打卡记录
     * @param id 员工的id
     */
    @Override
    public void deleteCheckInById(Integer id){
        checkInDao.deleteById(id);
    }

    /**
     * 根据员工职位返回对应的工资策略
     * @param position 岗位
     * @return 工资策略
     */
    @Override
    public String findStrategyByPosition(String position){
        return staffDao.findStrategyByPosition(position);
    }

    /**
     * 根据员工职位返回对应的岗位工资
     * @param position 岗位
     * @return 岗位工资
     */
    @Override
    public Integer findPositionSalary(String position){
        return staffDao.findPositionSalary(position);
    }

    @Override
    public Integer findBaseSalary(Integer id){
        return staffDao.findBaseSalaryById(id);
    }


    /**
     * 根据员工的姓名返回员工的岗位
     * @param name 员工姓名
     * @return 员工的岗位
     */
    @Override
    public String findStaffTypeByName(String name){
        return staffDao.findTypeByName(name);
    }

}
