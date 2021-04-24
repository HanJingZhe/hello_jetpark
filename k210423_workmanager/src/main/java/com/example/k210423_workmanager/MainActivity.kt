package com.example.k210423_workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.work.*
import com.example.k210423_workmanager.worker.SimpleWorker
import com.qtgm.base.utils.MsLog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val request = OneTimeWorkRequest.Builder(SimpleWorker::class.java)
//            .setInitialDelay(5, TimeUnit.MINUTES)//延迟执行
            .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS) //重新执行任务不能少于10秒
            .addTag("print log")
            .build()
        val build =
            PeriodicWorkRequest.Builder(SimpleWorker::class.java, 15, TimeUnit.MINUTES).build()
        btnSingleWork.setOnClickListener {

            MsLog.d("send onetime worker=========")
            WorkManager.getInstance(this).enqueue(request)


        }


        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id)
            .observe(this) {
                if (it.state == WorkInfo.State.SUCCEEDED) {
                    MsLog.d("${request.id} do work succeeded")
                } else if (it.state == WorkInfo.State.FAILED) {
                    MsLog.d("${request.id} do work failed")
                }
            }

    }
}