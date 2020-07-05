package com.valorwide.xtranslation.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SurahModel {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("title_bang")
        @Expose
        private String titleBang;
        @SerializedName("description_bang")
        @Expose
        private String descriptionBang;

        /**
         * No args constructor for use in serialization
         *
         */
        public SurahModel() {
        }

        /**
         *
         * @param descriptionBang
         * @param id
         * @param title
         * @param titleBang
         * @param description
         */
        public SurahModel(Integer id, String title, String description, String titleBang, String descriptionBang) {
            super();
            this.id = id;
            this.title = title;
            this.description = description;
            this.titleBang = titleBang;
            this.descriptionBang = descriptionBang;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTitleBang() {
            return titleBang;
        }

        public void setTitleBang(String titleBang) {
            this.titleBang = titleBang;
        }

        public String getDescriptionBang() {
            return descriptionBang;
        }

        public void setDescriptionBang(String descriptionBang) {
            this.descriptionBang = descriptionBang;
        }

        @Override
        public String toString() {
            return "SurahModel{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", titleBang='" + titleBang + '\'' +
                    ", descriptionBang='" + descriptionBang + '\'' +
                    '}';
        }
    }
