package com.liyafeng.kotlinproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.tinkerpatch.sdk.TinkerPatch
import com.tinkerpatch.sdk.server.callback.ConfigRequestCallback
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        tv_cotent.text = "dddd"

        button1.setOnClickListener { TinkerPatch.with().fetchPatchUpdate(true); }
        button2.setOnClickListener {
            TinkerPatch.with().fetchDynamicConfig(object : ConfigRequestCallback {

                override fun onSuccess(configs: HashMap<String, String>) {
                    Log.w("test", "request config success, config:$configs")
                }

                override fun onFail(e: Exception) {
                    Log.w("test", "request config failed, exception:$e")
                }
            }, true)
        }

    }
}
