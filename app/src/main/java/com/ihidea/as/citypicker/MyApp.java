package com.ihidea.as.citypicker;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.lljjcoder.style.citylist.utils.CityListLoader;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.tinker.lib.service.PatchResult;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tinkerpatch.sdk.TinkerPatch;
import com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike;
import com.tinkerpatch.sdk.server.callback.RollbackCallBack;
import com.tinkerpatch.sdk.tinker.callback.ResultCallBack;

/**
 * 作者：liji on 2017/12/15 10:55
 * 邮箱：lijiwork@sina.com
 * QQ ：275137657
 */

public class MyApp extends Application {

    private String TAG="tinker_patch_log";
    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * 预先加载一级列表所有城市的数据
         */
        CityListLoader.getInstance().loadCityData(this);

        /**
         * 预先加载三级列表显示省市区的数据
         */
        CityListLoader.getInstance().loadProData(this);

        refWatcher = LeakCanary.install(this);

        // 我们可以从这里获得Tinker加载过程的信息
        ApplicationLike tinkerApplicationLike = TinkerPatchApplicationLike.getTinkerPatchApplicationLike();
        // 初始化TinkerPatch SDK, 更多配置可参照API章节中的,初始化SDK
        TinkerPatch.init(tinkerApplicationLike)
                .reflectPatchLibrary()
                .fetchPatchUpdate(true)
                .setPatchRollbackOnScreenOff(true)
                .setPatchRestartOnSrceenOff(true)
                .setFetchPatchIntervalByHours(3)
                //我们可以通过ResultCallBack设置对合成后的回调,例如弹框什么
                .setPatchResultCallback(new ResultCallBack() {
                    @Override public void onPatchResult(PatchResult patchResult) {
                        Log.i(TAG, "onPatchResult callback here");
                    }
                })
                //设置收到后台回退要求时,锁屏清除补丁,默认是等主进程重启时自动清除
                .setPatchRollbackOnScreenOff(true)
                //我们可以通过RollbackCallBack设置对回退时的回调
                .setPatchRollBackCallback(new RollbackCallBack() {
                    @Override public void onPatchRollback() {
                        Log.i(TAG, "onPatchRollback callback here");
                    }
                });;
        // 每隔3个小时(通过setFetchPatchIntervalByHours设置)去访问后台时候有更新,通过handler实现轮训的效果
        TinkerPatch.with().fetchPatchUpdateAndPollWithInterval();


    }


    //在自己的Application中添加如下代码
    private RefWatcher refWatcher;

    //在自己的Application中添加如下代码
    public static RefWatcher getRefWatcher(Context context) {
        MyApp application = (MyApp) context.getApplicationContext();
        return application.refWatcher;
    }
}
