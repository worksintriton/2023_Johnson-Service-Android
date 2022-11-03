package com.triton.johnson_tap_app.responsepojo;

import java.util.List;

public class Material_DetailsResponseACK {

    private String Status;
    private String Message;
    private List<DataBean> Data;

    private int Code;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;

    }


    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;

    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;

    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;

    }

    public static class DataBean {
        private String part_name;
        private String part_quantity;
        private String mr_id;
        private String mrseq_no;

        public String getPart_name() {
            return part_name;
        }

        public void setPart_name(String part_name) {
            this.part_name = part_name;
        }

        public String getPart_quantity() {
            return part_quantity;
        }

        public void setPart_quantity(String part_quantity) {
            this.part_quantity = part_quantity;
        }

        public String getMr_id() {
            return mr_id;
        }

        public void setMr_id(String mr_id) {
            this.mr_id = mr_id;
        }

        public String getMrseq_no() {
            return mrseq_no;
        }

        public void setMrseq_no(String mrseq_no) {
            this.mrseq_no = mrseq_no;
        }
    }
}
