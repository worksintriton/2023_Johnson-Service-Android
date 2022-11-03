package com.triton.johnson_tap_app.responsepojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Auditcheckresponse {
    private String status;

    private String message;

    private List<Datum> data = null;

    private Integer code;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public List<Datum> getData() {
        return data;
    }


    public void setData(List<Datum> data) {
        this.data = data;
    }


    public Integer getCode() {
        return code;
    }


    public void setCode(Integer code) {
        this.code = code;
    }


    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public  class Datum {


        private List<String> dropDown = null;

        private List<Object> liftList = null;

        private String id;

        private String catId;

        private String groupId;

        private String subGroupId;

        private String fieldName;

        private String fieldType;

        private String fieldValue;

        private String fieldLength;

        private String fieldComments;

        private String fieldUpdateReason;

        private String dateOfCreate;

        private String dateOfUpdate;

        private String createdBy;

        private String updatedBy;

        private Integer v;

        private Boolean deleteStatus;

        private Map<String, Object> additionalProperties = new HashMap<String, Object>();


        public List<String> getDropDown() {
            return dropDown;
        }


        public void setDropDown(List<String> dropDown) {
            this.dropDown = dropDown;
        }


        public List<Object> getLiftList() {
            return liftList;
        }


        public void setLiftList(List<Object> liftList) {
            this.liftList = liftList;
        }


        public String getId() {
            return id;
        }


        public void setId(String id) {
            this.id = id;
        }


        public String getCatId() {
            return catId;
        }


        public void setCatId(String catId) {
            this.catId = catId;
        }


        public String getGroupId() {
            return groupId;
        }


        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }


        public String getSubGroupId() {
            return subGroupId;
        }


        public void setSubGroupId(String subGroupId) {
            this.subGroupId = subGroupId;
        }


        public String getFieldName() {
            return fieldName;
        }


        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }


        public String getFieldType() {
            return fieldType;
        }


        public void setFieldType(String fieldType) {
            this.fieldType = fieldType;
        }


        public String getFieldValue() {
            return fieldValue;
        }


        public void setFieldValue(String fieldValue) {
            this.fieldValue = fieldValue;
        }


        public String getFieldLength() {
            return fieldLength;
        }


        public void setFieldLength(String fieldLength) {
            this.fieldLength = fieldLength;
        }


        public String getFieldComments() {
            return fieldComments;
        }


        public void setFieldComments(String fieldComments) {
            this.fieldComments = fieldComments;
        }


        public String getFieldUpdateReason() {
            return fieldUpdateReason;
        }

        public void setFieldUpdateReason(String fieldUpdateReason) {
            this.fieldUpdateReason = fieldUpdateReason;
        }


        public String getDateOfCreate() {
            return dateOfCreate;
        }

        public void setDateOfCreate(String dateOfCreate) {
            this.dateOfCreate = dateOfCreate;
        }

        public String getDateOfUpdate() {
            return dateOfUpdate;
        }


        public void setDateOfUpdate(String dateOfUpdate) {
            this.dateOfUpdate = dateOfUpdate;
        }


        public String getCreatedBy() {
            return createdBy;
        }


        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }


        public String getUpdatedBy() {
            return updatedBy;
        }


        public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
        }


        public Integer getV() {
            return v;
        }


        public void setV(Integer v) {
            this.v = v;
        }


        public Boolean getDeleteStatus() {
            return deleteStatus;
        }


        public void setDeleteStatus(Boolean deleteStatus) {
            this.deleteStatus = deleteStatus;
        }


        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }


        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }
}
