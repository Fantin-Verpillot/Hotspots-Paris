package com.epita.parishotspot.Models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hotspot {

    @SerializedName("nhits")
    @Expose
    private Integer nhits;
    @SerializedName("parameters")
    @Expose
    private Parameters parameters;
    @SerializedName("records")
    @Expose
    private List<Record> records = new ArrayList<Record>();

    /**
     *
     * @return
     * The nhits
     */
    public Integer getNhits() {
        return nhits;
    }

    /**
     *
     * @param nhits
     * The nhits
     */
    public void setNhits(Integer nhits) {
        this.nhits = nhits;
    }

    /**
     *
     * @return
     * The parameters
     */
    public Parameters getParameters() {
        return parameters;
    }

    /**
     *
     * @param parameters
     * The parameters
     */
    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    /**
     *
     * @return
     * The records
     */
    public List<Record> getRecords() {
        return records;
    }

    /**
     *
     * @param records
     * The records
     */
    public void setRecords(List<Record> records) {
        this.records = records;
    }

}