package un.app1.pageModule.appHome.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Banner {

    @SerializedName("message")
    public String message;

    @SerializedName("statusCode")
    public String statusCode;

    @SerializedName("banner")
    public ArrayList<ArrayBanner> arrayBanners = new ArrayList<ArrayBanner>();

}