package com.argusoft.abdmhackathon.labtest.dto;

/**
 * <p>
 * Add description here
 * </p>
 *
 * @author namrata
 * @since 16/07/2022 6:30 PM
 */
public class LabTestDto {
    private String name;
    private Integer code;
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
