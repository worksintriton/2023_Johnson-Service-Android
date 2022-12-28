package com.triton.johnson_tap_app.responsepojo;

import java.util.List;

public class ViewStatusResponse {

    private String Status;
    private String Message;
    private Data Data;
    private Integer Code;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public ViewStatusResponse.Data getData() {
        return Data;
    }

    public void setData(ViewStatusResponse.Data data) {
        Data = data;
    }

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public class Data {

        private Integer pending_total;
        private List<PendingData> pending_data = null;
        private Integer completed_total;
        private List<CompletedData> completed_data = null;

        public Integer getPending_total() {
            return pending_total;
        }

        public void setPending_total(Integer pending_total) {
            this.pending_total = pending_total;
        }

        public List<PendingData> getPending_data() {
            return pending_data;
        }

        public void setPending_data(List<PendingData> pending_data) {
            this.pending_data = pending_data;
        }

        public Integer getCompleted_total() {
            return completed_total;
        }

        public void setCompleted_total(Integer completed_total) {
            this.completed_total = completed_total;
        }

        public List<CompletedData> getCompleted_data() {
            return completed_data;
        }

        public void setCompleted_data(List<CompletedData> completed_data) {
            this.completed_data = completed_data;
        }

        public class PendingData {

            private String service_name;

            private Integer count;

            private List<PendingList> data = null;

            public String getService_name() {
                return service_name;
            }

            public void setService_name(String service_name) {
                this.service_name = service_name;
            }

            public Integer getCount() {
                return count;
            }

            public void setCount(Integer count) {
                this.count = count;
            }

            public List<PendingList> getData() {
                return data;
            }

            public void setData(List<PendingList> data) {
                this.data = data;
            }

            public class PendingList {

                private String job_id;
                private String start_time;
                private String end_time;

                public String getJob_id() {
                    return job_id;
                }

                public void setJob_id(String job_id) {
                    this.job_id = job_id;
                }

                public String getStart_time() {
                    return start_time;
                }

                public void setStart_time(String start_time) {
                    this.start_time = start_time;
                }

                public String getEnd_time() {
                    return end_time;
                }

                public void setEnd_time(String end_time) {
                    this.end_time = end_time;
                }
            }
        }

        public class CompletedData {

            private String service_name;

            private Integer count;

            private List<CompletedList> data = null;

            public String getService_name() {
                return service_name;
            }

            public void setService_name(String service_name) {
                this.service_name = service_name;
            }

            public Integer getCount() {
                return count;
            }

            public void setCount(Integer count) {
                this.count = count;
            }

            public List<CompletedList> getData() {
                return data;
            }

            public void setData(List<CompletedList> data) {
                this.data = data;
            }

            public class CompletedList {

                private String job_id;
                private String start_time;
                private String end_time;

                public String getJob_id() {
                    return job_id;
                }

                public void setJob_id(String job_id) {
                    this.job_id = job_id;
                }

                public String getStart_time() {
                    return start_time;
                }

                public void setStart_time(String start_time) {
                    this.start_time = start_time;
                }

                public String getEnd_time() {
                    return end_time;
                }

                public void setEnd_time(String end_time) {
                    this.end_time = end_time;
                }
            }
        }

    }
}
