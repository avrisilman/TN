package un.app1.appNetwork.service;

import android.content.Context;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import un.app1.MainApp;
import un.app1.appCommon.common.CommonUtils;
import un.app1.appConfig.AppData;

public class MainService {

    @Inject
    AppData appData;

    private ApiService apiService;

    public MainService(ApiService apiService, Context context) {
        this.apiService = apiService;
        ((MainApp) context).providesAppComponents().inject(this);
    }

    public Disposable requestDenomPulsa(String deviceId, String token, MyCallBack.CallDenomPulsa callback) {
        callback.onStartRequest();
        return apiService.reqDenomPulsa(deviceId, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(throwable -> {
                    callback.onNotFound();
                })
                .doOnError(throwable -> {
                    if (CommonUtils.isUnauthorized(throwable)) {
                        callback.onUnauthorized();
                    } else {
                        callback.onError();
                    }
                })
                .doOnNext(callback::onSuccess)
                .subscribe();
    }

    public Disposable requestBanner(String deviceId, MyCallBack.CallBanner callback) {
        callback.onStartRequest();
        return apiService.reqBanner(deviceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(throwable -> {
                    callback.onNotFound();
                })
                .doOnError(throwable -> {
                    if (CommonUtils.isUnauthorized(throwable)) {
                        callback.onUnauthorized();
                    } else {
                        callback.onError();
                    }
                })
                .doOnNext(callback::onSuccess)
                .subscribe();

    }

    public Disposable requestHistoryListrik(String deviceId, String token, MyCallBack.CallHistoryListrik callback) {
        callback.onStartRequest();
        return apiService.reqHistoryListrik(deviceId, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(throwable -> {
                    callback.onNotFound();
                })
                .doOnError(throwable -> {
                    if (CommonUtils.isUnauthorized(throwable)) {
                        callback.onUnauthorized();
                    } else {
                        callback.onError();
                    }
                })
                .doOnNext(callback::onSuccess)
                .subscribe();
    }

    public Disposable requestTokenListrik(String deviceId, String token, MyCallBack.CallTokenListrik callback) {
        callback.onStartRequest();
        return apiService.reqTokenListrik(deviceId, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(throwable -> {
                    callback.onNotFound();
                })
                .doOnError(throwable -> {
                    if (CommonUtils.isUnauthorized(throwable)) {
                        callback.onUnauthorized();
                    } else {
                        callback.onError();
                    }
                })
                .doOnNext(callback::onSuccess)
                .subscribe();
    }

    public Disposable requestQuickPreview(String deviceId, String token, MyCallBack.CallQuickPreview callback) {
        callback.onStartRequest();
        return apiService.reqUserPreview(deviceId, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(throwable -> {
                    callback.onNotFound();
                })
                .doOnError(throwable -> {
                    if (CommonUtils.isUnauthorized(throwable)) {
                        callback.onUnauthorized();
                    } else {
                        callback.onError();
                    }
                })
                .doOnNext(callback::onSuccess)
                .subscribe();
    }

    public Disposable requestLogin(String deviceId, String userName, String password, MyCallBack.CallLogin callback) {
        callback.onStartRequest();
        return apiService.reqLogin(deviceId, userName, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(throwable -> {
                    callback.onNotFound();
                })
                .doOnError(throwable -> {
                    if (CommonUtils.isUnauthorized(throwable)) {
                        callback.onUnauthorized();
                    } else {
                        callback.onError();
                    }
                })
                .doOnNext(callback::onSuccess)
                .subscribe();
    }

}