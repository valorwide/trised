package com.valorwide.trised.Retrofit;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SurahDetailsModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("surah_id")
    @Expose
    private Integer surahId;
    @SerializedName("arabic_text")
    @Expose
    private String arabicText;
    @SerializedName("bangla_text")
    @Expose
    private String banglaText;
    @SerializedName("page_no")
    @Expose
    private Integer pageNo;

    /**
     * No args constructor for use in serialization
     *
     */
    public SurahDetailsModel() {
    }

    /**
     *
     * @param arabicText
     * @param banglaText
     * @param surahId
     * @param pageNo
     * @param id
     */
    public SurahDetailsModel(Integer id, Integer surahId, String arabicText, String banglaText, Integer pageNo) {
        super();
        this.id = id;
        this.surahId = surahId;
        this.arabicText = arabicText;
        this.banglaText = banglaText;
        this.pageNo = pageNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSurahId() {
        return surahId;
    }

    public void setSurahId(Integer surahId) {
        this.surahId = surahId;
    }

    public String getArabicText() {
        return arabicText;
    }

    public void setArabicText(String arabicText) {
        this.arabicText = arabicText;
    }

    public String getBanglaText() {
        return banglaText;
    }

    public void setBanglaText(String banglaText) {
        this.banglaText = banglaText;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public String toString() {
        return "SurahDetailsModel{" +
                "id=" + id +
                ", surahId=" + surahId +
                ", arabicText='" + arabicText + '\'' +
                ", banglaText='" + banglaText + '\'' +
                ", pageNo=" + pageNo +
                '}';
    }
}
