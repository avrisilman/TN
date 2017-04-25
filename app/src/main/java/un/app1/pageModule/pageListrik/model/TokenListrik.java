package un.app1.pageModule.pageListrik.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TokenListrik {

    @SerializedName("message")
    public String message;

    @SerializedName("statusCode")
    public String statusCode;

    @SerializedName("denomListrik")
    public ArrayList<DenomListrik> denomListrik = new ArrayList<DenomListrik>();

}
