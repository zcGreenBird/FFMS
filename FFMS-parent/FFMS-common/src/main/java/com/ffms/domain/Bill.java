package com.ffms.domain;





import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 账单实体类
 *
 */
@Data
public class Bill  {
    /**
     *id
     */
    private Integer id ;
    /**
     *用户id
     */
    private  Integer userId;
    /**
     *消费名称
     */
    private String consumerName;
    /**
     *消费时间
     */
    private Date consumerNameTime;
    /**
     *消费金额
     */
    private  Double consumerAmount;
    /**
     *消费类类型
     */
    private Integer  categoryId;
    /**
     *交易对方
     */
    private String tradingParty;
    /**
     *交易类型 1.收入 2.支出 3.资金转账
     */
    private Integer type;
    /**
     *备注
     */
    private String remarks;


}
