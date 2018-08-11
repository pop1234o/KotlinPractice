package com.liyafeng.kotlinproject

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import android.util.Log
import com.tinkerpatch.sdk.TinkerPatch
import com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Log.i("test", "oncreate")

        initTinkerPatch()
    }


    /**
     * 我们需要确保至少对主进程跟patch进程初始化 TinkerPatch
     */
    private fun initTinkerPatch() {
        // 我们可以从这里获得Tinker加载过程的信息
        if (BuildConfig.TINKER_ENABLE) {
            var tinkerApplicationLike = TinkerPatchApplicationLike.getTinkerPatchApplicationLike()
            // 初始化TinkerPatch SDK
            TinkerPatch.init(
                    tinkerApplicationLike
                    //                new TinkerPatch.Builder(tinkerApplicationLike)
                    //                    .requestLoader(new OkHttp3Loader())
                    //                    .build()
            )
                    .reflectPatchLibrary()
                    .setPatchRollbackOnScreenOff(true)
                    .setPatchRestartOnSrceenOff(true)
                    .setFetchPatchIntervalByHours(1)
            // 获取当前的补丁版本
            Log.d("test", "Current patch version is " + TinkerPatch.with().getPatchVersion())

            // fetchPatchUpdateAndPollWithInterval 与 fetchPatchUpdate(false)
            // 不同的是，会通过handler的方式去轮询
            TinkerPatch.with().fetchPatchUpdateAndPollWithInterval()
        }
    }


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        //you must install multiDex whatever tinker is installed!
        MultiDex.install(base)

    }

}