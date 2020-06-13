package com.ketansoft.dms.service;

import com.ketansoft.dms.entity.DormtableEntity;
import com.ketansoft.dms.entity.DprelationshipEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Date:2019-7-25
 * Author:MaKaicheng
 * Desc：
 */
public interface DormtableService {

    List<DormtableEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    List<DormtableEntity>excelList();

    List<DormtableEntity>selectList(Map<String, Object> map);

    List<DormtableEntity>ControlList1(Map<String, Object> map);

    List<DormtableEntity>ControlList2(Map<String, Object> map);

    int ControlTotal1(Map<String, Object> map);

    int ControlTotal2(Map<String, Object> map);

    List<DormtableEntity> queryTimeList(Map<String, Object> map);

    /**
     * 导出宿舍报表
     * @param response
     * @param request
     */
    void exportDormReport(HttpServletResponse response, HttpServletRequest request,List exportList) throws Exception;

}
