package com.ketansoft.dms.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ExportDpRecordService {

    void exportDpRecordReport(HttpServletResponse response, HttpServletRequest request, List exportList) throws Exception;
}
