package com.triton.johnson_tap_app.responsepojo;

import java.util.List;

public class FilterPageInfoResponse {


    /**
     * Status : Success
     * Code : 200
     * price_range : [{"Display_text":"Under Rs.500","Count_value_start":0,"Count_value_end":500},{"Display_text":"Rs. 500 - Rs. 1,000","Count_value_start":500,"Count_value_end":1000},{"Display_text":"Rs. 1,000 - Rs. 2,000","Count_value_start":1000,"Count_value_end":2000},{"Display_text":"Rs. 2,000 - Rs. 3,000","Count_value_start":2000,"Count_value_end":3000},{"Display_text":"Rs. 3,000 - Above","Count_value_start":3000,"Count_value_end":1000000}]
     * condition : [{"Display_text":"New","value":"New"},{"Display_text":"Used","value":"Used"},{"Display_text":"Not Specified","value":"Not Specified"}]
     * buying_format : [{"Display_text":"All Listings","value":"All Listings"},{"Display_text":"Accepts Offers","value":"Accepts Offers"},{"Display_text":"Auction","value":"Auction"},{"Display_text":"Buy It Now","value":"Buy It Now"},{"Display_text":"Classified Ads","value":"Classified Ads"}]
     * discount : [{"Display_text":"10% or Below","Discount_value_start":0,"Discount_value_end":10},{"Display_text":"11% or More","Discount_value_start":11,"Discount_value_end":24},{"Display_text":"25% or More","Discount_value_start":25,"Discount_value_end":49},{"Display_text":"50% or More","Discount_value_start":50,"Discount_value_end":100}]
     * categories : [{"_id":"6198b507518ad4520ab14790","product_cate":"Skin & Body Care"},{"_id":"6198b572518ad4520ab14791","product_cate":"Hair Care"},{"_id":"6198b594518ad4520ab14792","product_cate":"Organic Products"},{"_id":"6198b5a2518ad4520ab14793","product_cate":"Men Care"},{"_id":"6198b5b5518ad4520ab14794","product_cate":"Beauty Products"},{"_id":"6198b5cc518ad4520ab14795","product_cate":"Baby Products"},{"_id":"6198b5e1518ad4520ab14796","product_cate":"Health Care"},{"_id":"61d7ec75fbeb866ffec47a9c","product_cate":"Women Care"},{"_id":"61d80023fbeb866ffec47f6d","product_cate":"Eye Care"},{"_id":"6200a8ccea548c12dd832e44","product_cate":"Herbs"},{"_id":"6220608a8012412f39953983","product_cate":"test"}]
     */

    private String Status;
    private int Code;

    private List<PriceRangeBean> price_range;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public List<PriceRangeBean> getPrice_range() {
        return price_range;
    }

    public void setPrice_range(List<PriceRangeBean> price_range) {
        this.price_range = price_range;
    }


    public static class PriceRangeBean {
        private String Display_text;
        private int Count_value_start;
        private int Count_value_end;

        public String getDisplay_text() {
            return Display_text;
        }

        public void setDisplay_text(String Display_text) {
            this.Display_text = Display_text;
        }

        public int getCount_value_start() {
            return Count_value_start;
        }

        public void setCount_value_start(int Count_value_start) {
            this.Count_value_start = Count_value_start;
        }

        public int getCount_value_end() {
            return Count_value_end;
        }

        public void setCount_value_end(int Count_value_end) {
            this.Count_value_end = Count_value_end;
        }


//        private String FA_BSD_UTRNO;
//        private String FA_BSD_BANKDT;
//        private String FA_BSD_AMOUNT;
//        private String FA_BSD_CUSACNM;
//        private String FA_BSD_IFSCCD;
//        private String FA_BSD_BALAMT;
//
//        public String getFA_BSD_UTRNO() {
//            return FA_BSD_UTRNO;
//        }
//
//        public void setFA_BSD_UTRNO(String FA_BSD_UTRNO) {
//            this.FA_BSD_UTRNO = FA_BSD_UTRNO;
//        }
//
//        public String getFA_BSD_BANKDT() {
//            return FA_BSD_BANKDT;
//        }
//
//        public void setFA_BSD_BANKDT(String FA_BSD_BANKDT) {
//            this.FA_BSD_BANKDT = FA_BSD_BANKDT;
//        }
//
//        public String getFA_BSD_AMOUNT() {
//            return FA_BSD_AMOUNT;
//        }
//
//        public void setFA_BSD_AMOUNT(String FA_BSD_AMOUNT) {
//            this.FA_BSD_AMOUNT = FA_BSD_AMOUNT;
//        }
//
//        public String getFA_BSD_CUSACNM() {
//            return FA_BSD_CUSACNM;
//        }
//
//        public void setFA_BSD_CUSACNM(String FA_BSD_CUSACNM) {
//            this.FA_BSD_CUSACNM = FA_BSD_CUSACNM;
//        }
//
//        public String getFA_BSD_IFSCCD() {
//            return FA_BSD_IFSCCD;
//        }
//
//        public void setFA_BSD_IFSCCD(String FA_BSD_IFSCCD) {
//            this.FA_BSD_IFSCCD = FA_BSD_IFSCCD;
//        }
//
//        public String getFA_BSD_BALAMT() {
//            return FA_BSD_BALAMT;
//        }
//
//        public void setFA_BSD_BALAMT(String FA_BSD_BALAMT) {
//            this.FA_BSD_BALAMT = FA_BSD_BALAMT;
//        }
    }
}
