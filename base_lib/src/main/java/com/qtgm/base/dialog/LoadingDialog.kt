package com.qtgm.base.dialog

import android.app.Dialog
import android.content.Context
import com.qtgm.base.R

/**
 * @author peng_wang
 * @date 2021/6/4
 */
class LoadingDialog(ctx: Context) : Dialog(ctx, R.style.base_common_bottom_dialog) {

    init {
        setContentView(R.layout.base_dialog_loading_layout)
        setCancelable(false)
    }

}