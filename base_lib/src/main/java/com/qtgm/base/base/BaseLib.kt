package com.qtgm.base.base

import android.content.Context
import com.qtgm.base.utils.FileUtils
import com.qtgm.base.utils.SPUtils
import com.qtgm.base.utils.ToastUtils

/**
 * @author peng_wang
 * @date 2021/6/5
 */
object BaseLib {

    fun init(ctx: Context) {
        FileUtils.init(ctx.applicationContext)
        ToastUtils.init(ctx.applicationContext)
        SPUtils.init(ctx.applicationContext)
    }

}