package un.app1.appNetwork.service;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import un.app1.pageModule.appHome.model.Banner;
import un.app1.pageModule.appHome.model.QuickPreview;
import un.app1.pageModule.pageListrik.model.History;
import un.app1.pageModule.pageListrik.model.TokenListrik;
import un.app1.pageModule.pageLogin.model.DataLogin;
import un.app1.pageModule.pagePulsa.model.Pulsa;

public interface ApiService {

    @FormUrlEncoded
    @POST("denomPulsa")
    @Headers({"Accept: application/json", "No-Authentication: true"})
    Observable<Pulsa> reqDenomPulsa(@Field("deviceId") String deviceId, @Field("token") String token);

    @FormUrlEncoded
    @POST("historylistrik")
    @Headers({"Accept: application/json", "No-Authentication: true"})
    Observable<History> reqHistoryListrik(@Field("deviceId") String deviceId, @Field("token") String token);

    @FormUrlEncoded
    @POST("tokenlistrik")
    @Headers({"Accept: application/json", "No-Authentication: true"})
    Observable<TokenListrik> reqTokenListrik(@Field("deviceId") String deviceId, @Field("token") String token);

    @FormUrlEncoded
    @POST("getbannser")
    @Headers({"Accept: application/json", "No-Authentication: true"})
    Observable<Banner> reqBanner(@Field("deviceId") String deviceId);

    @FormUrlEncoded
    @POST("usrpreview")
    @Headers({"Accept: application/json", "No-Authentication: true"})
    Observable<QuickPreview> reqUserPreview(@Field("deviceId") String deviceId, @Field("token") String token);

    @FormUrlEncoded
    @POST("userlogin")
    @Headers({"Accept: application/json", "No-Authentication: true"})
    Observable<DataLogin> reqLogin(@Field("deviceId") String deviceId, @Field("userName") String userName, @Field("userName") String password);

}
