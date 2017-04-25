package un.app1.pageModule.pagePulsa.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Pulsa {

    @SerializedName("message")
    public String message;

    @SerializedName("statusCode")
    public String statusCode;

    @SerializedName("denomPulsa")
    public ArrayList<DenomPulsa> denomPulsa = new ArrayList<DenomPulsa>();

}
