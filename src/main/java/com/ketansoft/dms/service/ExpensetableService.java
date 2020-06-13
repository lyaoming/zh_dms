package com.ketansoft.dms.service;
import com.ketansoft.dms.entity.ExpensetableEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Date:2019-7-25
 * Author:MaKaicheng
 * Desc：
 */
public interface ExpensetableService {

    List<ExpensetableEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    List<ExpensetableEntity>excelList(Map<String, Object> map);

    List<ExpensetableEntity>selectList(Map<String, Object> map);

    /**
     * 导出收费报表
     * @param response
     * @param request
     */
    void exportCostReport(HttpServletResponse response, HttpServletRequest request,List exportList) throws Exception;

}
