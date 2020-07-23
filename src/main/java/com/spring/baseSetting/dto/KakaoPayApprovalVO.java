package com.spring.baseSetting.dto;

import lombok.Data;

@Data
public class KakaoPayApprovalVO {
    private String tid;  //결제고유번호
    private String cid;  //가맹점코드
    private String partner_order_id;  //가맹점 주문번호
    private String partner_user_id;  //가맹점 회원ID
    private String item_name;
    private Integer quantity;       //상품수량
    private Amount amount;
}

@Data
class Amount{
    private Integer total;
    private Integer tax_free;
    private Integer vat;
    private Integer point;
    private Integer discount;
}