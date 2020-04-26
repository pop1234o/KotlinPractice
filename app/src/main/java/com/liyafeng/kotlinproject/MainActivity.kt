package com.liyafeng.kotlinproject

//import com.tinkerpatch.sdk.TinkerPatch
//import com.tinkerpatch.sdk.server.callback.ConfigRequestCallback
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    /**
     * tinker使用
     * https://itimetraveler.github.io/2016/11/17/【Android】微信热修复%20Tinker%20的集成和使用/
     *
     * https://github.com/Tencent/tinker/wiki/Tinker-常见问题
     *
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        tv_cotent.text = "dddd"

//        button1.setOnClickListener { TinkerPatch.with().fetchPatchUpdate(true); }
//        button2.setOnClickListener {
//            TinkerPatch.with().fetchDynamicConfig(object : ConfigRequestCallback {
//
//                override fun onSuccess(configs: HashMap<String, String>) {
//                    Log.w("test", "request config success, config:$configs")
//                }
//
//                override fun onFail(e: Exception) {
//                    Log.w("test", "request config failed, exception:$e")
//                }
//            }, true)
//        }

    }
}
