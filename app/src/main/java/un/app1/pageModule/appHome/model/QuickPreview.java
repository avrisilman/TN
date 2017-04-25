package un.app1.pageModule.appHome.model;

import com.google.gson.annotations.SerializedName;

public class QuickPreview {

    @SerializedName("message")
    public String message;

    @SerializedName("statusCode")
    public String statusCode;

    @SerializedName("imageUrl")
    public String imageUrl;

    @SerializedName("user")
    public String user;

    @SerializedName("balance")
    public String balance;

    @SerializedName("lastLogin")
    public String lastLogin;

}