package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.SalesDetailsDao;
import com.nju.edu.erp.model.po.SalesDetailsPO;
import com.nju.edu.erp.model.vo.SalesDetailsVO;
import com.nju.edu.erp.service.SalesDetailsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SalesDetailsServiceImpl implements SalesDetailsService {

    private SalesDetailsDao salesDetailsDao;

    @Autowired
    public SalesDetailsServiceImpl(SalesDetailsDao salesDetailsDao) {
        this.salesDetailsDao = salesDetailsDao;
    }

    @Override
    @Transactional
    public List<SalesDetailsVO> saleDetailsCounting(String beginDateStr, String endDateStr, String productName, String customer, String salesman) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate = null, endDate = null;
        try {
            beginDate = sf.parse(beginDateStr);
            endDate = sf.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(beginDate.after(endDate)) return null;

        List<SalesDetailsPO> salesDetailsPOS = salesDetailsDao.findSalesDetails(beginDate,endDate,productName,customer,salesman);
        List<SalesDetailsVO> salesDetailsVOS = new ArrayList<>();
        for(SalesDetailsPO po : salesDetailsPOS) {
            SalesDetailsVO vo = new SalesDetailsVO();
            BeanUtils.copyProperties(po, vo);
            vo.setTime(new SimpleDateFormat("yyyy-MM-dd").format(po.getDate()));
            salesDetailsVOS.add(vo);
        }
        return salesDetailsVOS;
    }
}
