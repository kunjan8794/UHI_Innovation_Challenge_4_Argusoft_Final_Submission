package com.argusoft.abdmhackathon.labtest.dto;

import java.util.Date;

/**
 * Defines methods for LabDataDto
 *
 * @author prateek
 * @since 16/07/22 1:53 PM
 */
public class LabDataDto {

    private Integer patientId;
    private Date onDate;
    private Float ratio;
    private Float vldl;
    private Float nonHdl;
    private Float tri;
    private Float total;

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Date getOnDate() {
        return onDate;
    }

    public void setOnDate(Date onDate) {
        this.onDate = onDate;
    }

    public Float getRatio() {
        return ratio;
    }

    public void setRatio(Float ratio) {
        this.ratio = ratio;
    }

    public Float getVldl() {
        return vldl;
    }

    public void setVldl(Float vldl) {
        this.vldl = vldl;
    }

    public Float getNonHdl() {
        return nonHdl;
    }

    public void setNonHdl(Float nonHdl) {
        this.nonHdl = nonHdl;
    }

    public Float getTri() {
        return tri;
    }

    public void setTri(Float tri) {
        this.tri = tri;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
