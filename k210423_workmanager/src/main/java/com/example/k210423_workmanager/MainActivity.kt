package com.example.k210423_workmanager

import androidx.work.*
import com.example.k210423_workmanager.worker.SimpleWorker
import com.qtgm.base.base.BaseActivity
import com.qtgm.base.utils.MyLog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity() {

    override fun setLayoutId(): Int = R.layout.activity_main

    override fun initView() {
    }

    override fun initData() {
        val request = OneTimeWorkRequest.Builder(SimpleWorker::class.java)
//            .setInitialDelay(5, TimeUnit.MINUTES)//延迟执行
            .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS) //重新执行任务不能少于10秒
            .addTag("print log")
            .build()
        val build =
            PeriodicWorkRequest.Builder(SimpleWorker::class.java, 15, TimeUnit.MINUTES).build()
        btnSingleWork.setOnClickListener {

            MyLog.d("send onetime worker=========")
            WorkManager.getInstance(this).enqueue(request)
        }


        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id)
            .observe(this) {
                if (it.state == WorkInfo.State.SUCCEEDED) {
                    MyLog.d("${request.id} do work succeeded")
                } else if (it.state == WorkInfo.State.FAILED) {
                    MyLog.d("${request.id} do work failed")
                }
            }
    }

}