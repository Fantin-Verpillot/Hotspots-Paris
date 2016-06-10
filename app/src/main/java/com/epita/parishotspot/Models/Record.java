package com.epita.parishotspot.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Record implements Serializable {

    @SerializedName("datasetid")
    @Expose
    private String datasetid;
    @SerializedName("recordid")
    @Expose
    private String recordid;
    @SerializedName("fields")
    @Expose
    private Fields fields;
    @SerializedName("geometry")
    @Expose
    private Geometry geometry;
    @SerializedName("record_timestamp")
    @Expose
    private String recordTimestamp;

    /**
     *
     * @return
     * The datasetid
     */
    public String getDatasetid() {
        return datasetid;
    }

    /**
     *
     * @param datasetid
     * The datasetid
     */
    public void setDatasetid(String datasetid) {
        this.datasetid = datasetid;
    }

    /**
     *
     * @return
     * The recordid
     */
    public String getRecordid() {
        return recordid;
    }

    /**
     *
     * @param recordid
     * The recordid
     */
    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    /**
     *
     * @return
     * The fields
     */
    public Fields getFields() {
        return fields;
    }

    /**
     *
     * @param fields
     * The fields
     */
    public void setFields(Fields fields) {
        this.fields = fields;
    }

    /**
     *
     * @return
     * The geometry
     */
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     *
     * @param geometry
     * The geometry
     */
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    /**
     *
     * @return
     * The recordTimestamp
     */
    public String getRecordTimestamp() {
        return recordTimestamp;
    }

    /**
     *
     * @param recordTimestamp
     * The record_timestamp
     */
    public void setRecordTimestamp(String recordTimestamp) {
        this.recordTimestamp = recordTimestamp;
    }

}