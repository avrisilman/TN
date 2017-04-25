package un.app1.pageModule.pagePulsa.model;

import com.google.gson.annotations.SerializedName;

public class DenomPulsa {

    @SerializedName("denom")
    public String denom;

    @SerializedName("currency")
    public String currency;

    @SerializedName("adminFee")
    public String adminFee;

    @SerializedName("available")
    public String available;

}
