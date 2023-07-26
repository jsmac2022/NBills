package com.nictbills.app.activities.health_id_abdm.dto.banner;

import androidx.annotation.Keep;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Keep
public class Banner {

@SerializedName("result")
@Expose
private List<Result> result = null;
@SerializedName("code")
@Expose
private String code;
@SerializedName("status")
@Expose
private String status;

public List<Result> getResult() {
return result;
}

public void setResult(List<Result> result) {
this.result = result;
}

public String getCode() {
return code;
}

public void setCode(String code) {
this.code = code;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

    @Override
    public String toString() {
        return "Banner{" +
                "result=" + result +
                ", code='" + code + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}