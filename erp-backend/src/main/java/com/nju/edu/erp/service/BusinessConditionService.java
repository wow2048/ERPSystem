package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.BusinessConditionVO;

public interface BusinessConditionService {

    public BusinessConditionVO getBusinessCondition(String beginDateStr, String endDateStr);

}
