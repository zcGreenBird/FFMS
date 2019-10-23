package com.ffms.service;

import com.ffms.domain.Bill;
import lombok.Data;

/**
 * 账单导入接口  excl表格实现
 */
public interface  BillImportService {
    /**
     * 导入账单
     * @param bill
     * @return
     */
    public int  importExcel(Bill bill);
}
