package com.epita.parishotspot.Model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fields {

    @SerializedName("objectid")
    @Expose
    private Integer objectid;
    @SerializedName("arrondissement")
    @Expose
    private String arrondissement;
    @SerializedName("adresse")
    @Expose
    private String adresse;
    @SerializedName("geo_point_2d")
    @Expose
    private List<Double> geoPoint2d = new ArrayList<Double>();
    @SerializedName("nom_site")
    @Expose
    private String nomSite;
    @SerializedName("code_site")
    @Expose
    private String codeSite;
    @SerializedName("geo_shape")
    @Expose
    private GeoShape geoShape;

    /**
     *
     * @return
     * The objectid
     */
    public Integer getObjectid() {
        return objectid;
    }

    /**
     *
     * @param objectid
     * The objectid
     */
    public void setObjectid(Integer objectid) {
        this.objectid = objectid;
    }

    /**
     *
     * @return
     * The arrondissement
     */
    public String getArrondissement() {
        return arrondissement;
    }

    /**
     *
     * @param arrondissement
     * The arrondissement
     */
    public void setArrondissement(String arrondissement) {
        this.arrondissement = arrondissement;
    }

    /**
     *
     * @return
     * The adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     *
     * @param adresse
     * The adresse
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     *
     * @return
     * The geoPoint2d
     */
    public List<Double> getGeoPoint2d() {
        return geoPoint2d;
    }

    /**
     *
     * @param geoPoint2d
     * The geo_point_2d
     */
    public void setGeoPoint2d(List<Double> geoPoint2d) {
        this.geoPoint2d = geoPoint2d;
    }

    /**
     *
     * @return
     * The nomSite
     */
    public String getNomSite() {
        return nomSite;
    }

    /**
     *
     * @param nomSite
     * The nom_site
     */
    public void setNomSite(String nomSite) {
        this.nomSite = nomSite;
    }

    /**
     *
     * @return
     * The codeSite
     */
    public String getCodeSite() {
        return codeSite;
    }

    /**
     *
     * @param codeSite
     * The code_site
     */
    public void setCodeSite(String codeSite) {
        this.codeSite = codeSite;
    }

    /**
     *
     * @return
     * The geoShape
     */
    public GeoShape getGeoShape() {
        return geoShape;
    }

    /**
     *
     * @param geoShape
     * The geo_shape
     */
    public void setGeoShape(GeoShape geoShape) {
        this.geoShape = geoShape;
    }

}
