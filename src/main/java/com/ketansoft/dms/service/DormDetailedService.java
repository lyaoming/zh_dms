package com.ketansoft.dms.service;

import com.ketansoft.dms.entity.DormDetailedEntity;
import com.ketansoft.dms.entity.DormtableEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Date:2019-7-25
 * Author:MaKaicheng
 * Desc：
 */
public interface DormDetailedService {

    List<DormDetailedEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    List<DormDetailedEntity>excelList();

    List<DormDetailedEntity>selectList(Map<String, Object> map);

    List<DormDetailedEntity>queryTimeList(Map<String, Object> map);

    /**
     * 导出宿舍报表
     * @param response
     * @param request
     */
    void exportDormDetailedReport(HttpServletResponse response, HttpServletRequest request, List exportList) throws Exception;

}
