package com.ffms.dao;

import com.ffms.domain.Bill;
import com.ffms.domain.Dump;
import com.ffms.service.BillImportService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportExcelDao implements BillImportService {
    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     *
     * @param file
     * @param num
     * @return
     */
    @Override
    public int importExcel(File file, int num,int userId) throws IOException {
        //测试

        List<Dump> dump=ImportExcel.exportListFromExcel(file,num);
        //对list进行处理
        List<Dump> dumpList= new ArrayList<>();
        List<Bill> BillList= new ArrayList<>();
        for (Dump d : dump) {

            if(d.getConsumerName() == null &&d.getConsumerAmount()==null) break;
            if(d.getConsumerNameTime()==null){
                continue;
            }
             String str=d.getConsumerNameTime();
             Bill  bill = new Bill();
             bill.setUserId(userId);
             DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD_FORMAT);
             DateTime dateTime = dateTimeFormatter.parseDateTime(str);
             bill.setConsumerNameTime(dateTime.toDate());
             bill.setConsumerName(d.getConsumerName());
             bill.setTradingParty(d.getTradingParty());
             bill.setConsumerAmount(Double.parseDouble(d.getConsumerAmount()));
             bill.setRemarks(d.getRemarks());
             if(d.getType().equals("收入")){
                 bill.setType(1);
             }
            if(d.getType().equals("支出")){
                bill.setType(2);
            }
             BillList.add(bill);
             dumpList.add(d);
        }
        System.out.println(BillList.toString());

        return 0;
    }

    public static void main(String[] args) throws IOException {
        new ImportExcelDao().importExcel(new File("e://asd.xls"),0,1);
    }

}
