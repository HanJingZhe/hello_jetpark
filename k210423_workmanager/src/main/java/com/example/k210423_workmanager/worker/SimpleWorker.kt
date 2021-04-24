package com.example.k210423_workmanager.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.qtgm.base.utils.MsLog

class SimpleWorker(ctx: Context, param: WorkerParameters) : Worker(ctx, param) {

    val TAG = this.javaClass.simpleName

    override fun doWork(): Result {
        MsLog.e(TAG, "do work in $TAG")
        return Result.success()
    }


}