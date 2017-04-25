package un.app1.pageModule.pageListrik.model;

import com.google.gson.annotations.SerializedName;

public class HistoryPayment {

    @SerializedName("product")
    public String product;
    @SerializedName("productDescription")
    public String productDescription;
    @SerializedName("currency")
    public String currency;
    @SerializedName("amount")
    public String amount;
    @SerializedName("adminFee")
    public String adminFee;
    @SerializedName("orderDate")
    public String orderDate;
    @SerializedName("statusPayment")
    public String statusPayment;
    @SerializedName("totalPayment")
    public String totalPayment;

}
