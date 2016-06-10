package com.epita.parishotspot.Model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Parameters {

    @SerializedName("dataset")
    @Expose
    private List<String> dataset = new ArrayList<String>();
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("rows")
    @Expose
    private Integer rows;
    @SerializedName("format")
    @Expose
    private String format;

    /**
     *
     * @return
     * The dataset
     */
    public List<String> getDataset() {
        return dataset;
    }

    /**
     *
     * @param dataset
     * The dataset
     */
    public void setDataset(List<String> dataset) {
        this.dataset = dataset;
    }

    /**
     *
     * @return
     * The timezone
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     *
     * @param timezone
     * The timezone
     */
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    /**
     *
     * @return
     * The rows
     */
    public Integer getRows() {
        return rows;
    }

    /**
     *
     * @param rows
     * The rows
     */
    public void setRows(Integer rows) {
        this.rows = rows;
    }

    /**
     *
     * @return
     * The format
     */
    public String getFormat() {
        return format;
    }

    /**
     *
     * @param format
     * The format
     */
    public void setFormat(String format) {
        this.format = format;
    }

}
