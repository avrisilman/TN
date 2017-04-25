package un.app1.pageModule.pageListrik.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class History {

    @SerializedName("message")
    public String message;

    @SerializedName("statusCode")
    public String statusCode;

    @SerializedName("historyPayment")
    public ArrayList<HistoryPayment> historyPayment = new ArrayList<HistoryPayment>();

}
