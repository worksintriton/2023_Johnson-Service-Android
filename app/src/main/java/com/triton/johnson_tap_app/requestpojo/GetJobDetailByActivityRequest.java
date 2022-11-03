package com.triton.johnson_tap_app.requestpojo;

public class GetJobDetailByActivityRequest {

    /**
     * SMU_TECHMOBNO : 9043456963
     * SMU_DWNFLAG : Y
     * SMU_UKEY : ESPD-ACT1
     */

    private String SMU_TECHMOBNO;
    private String SMU_DWNFLAG;
    private String SMU_UKEY;

    public String getSMU_TECHMOBNO() {
        return SMU_TECHMOBNO;
    }

    public void setSMU_TECHMOBNO(String SMU_TECHMOBNO) {
        this.SMU_TECHMOBNO = SMU_TECHMOBNO;
    }

    public String getSMU_DWNFLAG() {
        return SMU_DWNFLAG;
    }

    public void setSMU_DWNFLAG(String SMU_DWNFLAG) {
        this.SMU_DWNFLAG = SMU_DWNFLAG;
    }

    public String getSMU_UKEY() {
        return SMU_UKEY;
    }

    public void setSMU_UKEY(String SMU_UKEY) {
        this.SMU_UKEY = SMU_UKEY;
    }
}
