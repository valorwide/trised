package com.shakiba.xtranslation.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

@SerializedName("result")
@Expose
private String result;

public String getResult() {
return result;
}

public void setResult(String result) {
this.result = result;
}

    @Override
    public String toString() {
        return "Response{" +
                "result='" + result + '\'' +
                '}';
    }
}