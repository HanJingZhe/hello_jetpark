package com.qtgm.expand

import android.Manifest
import com.qtgm.base.base.BaseActivity
import com.qtgm.base.permission.PermissionX
import com.qtgm.base.utils.MsLog
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : BaseActivity() {

    val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

    override fun setLayoutId(): Int = R.layout.activity_main

    override fun initView() {
        MsLog.d( "initView")
        PermissionX.requestPermission(this, *permissions) { b, list ->

        }
    }

    override fun initData() {
        MsLog.d(TAG, "initData")
        requestOkHttp()
    }

    private fun requestOkHttp() {
        MsLog.e(TAG, "request")
        val okHttpClient = OkHttpClient.Builder()
            .build()
        val request = Request.Builder().url("http://10.0.2.2:8080/peng/user.json")
            .build()

        okHttpClient.newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    MsLog.e(TAG, "fail:${e.message}")
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()
                    MsLog.e(TAG, "body:$body")
                    runOnUiThread {
                        tvShow.text = body
                    }
                }
            })

        //post
        val formBody = FormBody.Builder()
            .add("username", "peng.wang")
            .add("password", "123456")
            .build()

        val postRequest = Request.Builder()
            .url("https://www.baidu.com")
            .post(formBody)
            .build()
        okHttpClient.newCall(postRequest).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                MsLog.e(TAG, "post fail:${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                MsLog.e(TAG, "post body:$body")
            }
        })
    }


}
