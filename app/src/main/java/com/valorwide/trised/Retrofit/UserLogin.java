package com.valorwide.trised.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLogin {

@SerializedName("result")
@Expose
private Boolean result;

public Boolean getResult() {
return result;
}

public void setResult(Boolean result) {
this.result = result;
}

}