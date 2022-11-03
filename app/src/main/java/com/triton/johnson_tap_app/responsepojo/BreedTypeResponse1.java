package com.triton.johnson_tap_app.responsepojo;

import com.triton.johnson_tap_app.responsepojo.LoginResponse;

import java.util.List;

public class BreedTypeResponse1 {

    /**
     * Status : Success
     * Message : breed type List
     * Data : [{"_id":"5fbf61fa2af5cc11f61a1bf6","pet_type_id":"5fb8b160e0ba3b0e6268dd2e","pet_breed":"Testing - 4","date_and_time":"11/26/2020, 1:36:18 PM","__v":0}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    private List<DataBean> Data;


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


    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;

    }


    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;

    }

    public static class DataBean  {
        /**
         * _id : 5fbf61fa2af5cc11f61a1bf6
         * pet_type_id : 5fb8b160e0ba3b0e6268dd2e
         * pet_breed : Testing - 4
         * date_and_time : 11/26/2020, 1:36:18 PM
         * __v : 0
         */

        private String EMPNO;
        private String EMPNAME;
        private String MOBILE;

        private boolean isSelected ;
        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public String getEMPNO() {
            return EMPNO;
        }

        public void setEMPNO(String EMPNO) {
            this.EMPNO = EMPNO;
        }


        public String getEMPNAME() {
            return EMPNAME;
        }

        public void setEMPNAME(String EMPNAME) {
            this.EMPNAME = EMPNAME;

        }

        public String getMOBILE() {
            return MOBILE;
        }

        public void setMOBILE(String MOBILE) {
            this.MOBILE = MOBILE;

        }

    }
}
