package com.qtgm.base.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Typeface
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import androidx.annotation.ColorRes
import com.qtgm.base.R
import kotlinx.android.synthetic.main.base_dialog_common_layout.*

/**
 * @author peng.wang08
 * @date 2020/12/23
 ********/
class CommonDialog private constructor(var builder: Builder) : Dialog(
    builder.context, R.style.base_common_bottom_dialog
), View.OnClickListener {

    annotation class Style;

    companion object {
        const val STYLE_DEF = 1;
    }

    init {
        setContentView(R.layout.base_dialog_common_layout)
        initData()
    }

    private fun initData() {
        /******** title  ********/
        tvTitle.visibility = if (builder.title.isEmpty()) View.GONE else View.VISIBLE
        tvTitle.text = builder.title
        tvTitle.textSize = builder.titleSize
        tvTitle.setTextColor(builder.context.resources.getColor(builder.titleColor))
        tvTitle.typeface =
            Typeface.defaultFromStyle(if (builder.titleBold) Typeface.BOLD else Typeface.NORMAL)
        /******** content  ********/
        tvContent.visibility = if (builder.content.isEmpty()) View.GONE else View.VISIBLE
        tvContent.text = builder.content
        tvContent.textSize = builder.contentSize
        tvContent.setTextColor(builder.context.resources.getColor(builder.contentColor))
        tvContent.typeface =
            Typeface.defaultFromStyle(if (builder.contentBold) Typeface.BOLD else Typeface.NORMAL)
        tvContent.gravity = builder.gravity
        /******** start  ********/
        tvStart.visibility = if (builder.start.isEmpty()) View.GONE else View.VISIBLE
        tvStart.text = builder.start
        tvStart.textSize = builder.startSize
        tvStart.setTextColor(builder.context.resources.getColor(builder.startColor))
        tvStart.typeface =
            Typeface.defaultFromStyle(if (builder.startBold) Typeface.BOLD else Typeface.NORMAL)
        /******** end  ********/
        tvEnd.visibility = if (builder.end.isEmpty()) View.GONE else View.VISIBLE
        tvEnd.text = builder.end
        tvEnd.textSize = builder.endSize
        tvEnd.setTextColor(builder.context.resources.getColor(builder.endColor))
        tvEnd.typeface =
            Typeface.defaultFromStyle(if (builder.endBold) Typeface.BOLD else Typeface.NORMAL)
        /******** func  ********/
        tvStart.visibility = if (builder.isSingle) View.GONE else View.VISIBLE
        viewVertical.visibility = if (builder.isSingle) View.GONE else View.VISIBLE
        setCancelable(builder.isCancel)
        setCanceledOnTouchOutside(builder.isCancel)
        tvStart.setOnClickListener(this)
        tvEnd.setOnClickListener(this)
    }

    override fun show() {
        initData()
        super.show()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.tvStart) {
            dismiss()
            builder.startCallback?.onStartCallback()
        } else if (v.id == R.id.tvEnd) {
            dismiss()
            builder.endCallback?.onEndCallback()
        }
    }

    class Builder {
        var context: Context

        //title
        var title: String = ""
        var titleBold: Boolean = false
        var titleSize: Float = 14f
        var titleColor: Int = R.color.base_000

        //content
        var content: String = ""
        var contentBold: Boolean = false
        var contentSize: Float = 14f
        var contentColor: Int = R.color.base_000
        var gravity: Int = Gravity.CENTER


        //start btn
        var start: String = ""
        var startBold: Boolean = false
        var startSize: Float = 14f
        var startColor: Int = R.color.base_000
        var startCallback: OnStartClickCallback? = null

        //end/single btn
        var end: String = ""
        var endBold: Boolean = false
        var endSize: Float = 14f
        var endColor: Int = R.color.base_000
        var endCallback: OnEndClickCallback? = null

        //func
        var isCancel: Boolean = true
        var isSingle: Boolean = false
        private lateinit var commonDialog: CommonDialog

        /******** title  ********/
        fun setTitle(title: String): Builder {
            this.title = title
            return this
        }

        fun setTitleBold(titleBold: Boolean): Builder {
            this.titleBold = titleBold
            return this
        }

        fun setTitleSize(titleSize: Float): Builder {
            this.titleSize = titleSize
            return this
        }

        fun setTitleColor(@ColorRes titleColor: Int): Builder {
            this.titleColor = titleColor
            return this
        }

        /******** content  ********/
        fun setContent(content: String): Builder {
            this.content = content
            return this
        }

        fun setContentBold(contentBold: Boolean): Builder {
            this.contentBold = contentBold
            return this
        }

        fun setContentSize(contentSize: Float): Builder {
            this.contentSize = contentSize
            return this
        }

        fun setContentColor(@ColorRes contentColor: Int): Builder {
            this.contentColor = contentColor
            return this
        }

        fun setContentGravity(gravity: Int): Builder {
            this.gravity = gravity
            return this
        }

        /******** start  ********/
        fun setStart(start: String): Builder {
            this.start = start
            return this
        }

        fun setStart(start: String, startCallback: OnStartClickCallback?): Builder {
            this.start = start
            this.startCallback = startCallback
            return this
        }

        fun setStartCallback(startCallback: OnStartClickCallback?): Builder {
            this.startCallback = startCallback
            return this
        }

        fun setStartBold(startBold: Boolean): Builder {
            this.startBold = startBold
            return this
        }

        fun setStartSize(startSize: Float): Builder {
            this.startSize = startSize
            return this
        }

        fun setStartColor(@ColorRes startColor: Int): Builder {
            this.startColor = startColor
            return this
        }

        /******** end  ********/
        fun setEnd(end: String): Builder {
            this.end = end
            return this
        }

        fun setEnd(end: String, endCallback: OnEndClickCallback?): Builder {
            this.end = end
            this.endCallback = endCallback
            return this
        }

        fun setEndCallback(endCallback: OnEndClickCallback?): Builder {
            this.endCallback = endCallback
            return this
        }

        fun setEndBold(endBold: Boolean): Builder {
            this.endBold = endBold
            return this
        }

        fun setEndSize(endSize: Float): Builder {
            this.endSize = endSize
            return this
        }

        fun setEndColor(@ColorRes endColor: Int): Builder {
            this.endColor = endColor
            return this
        }

        /******** func  ********/
        constructor(context: Context) : this(context, STYLE_DEF)

        constructor(context: Context, @Style style: Int) {
            this.context = context
            build(style)
        }

        fun setIsCancel(isCancel: Boolean): Builder {
            this.isCancel = isCancel
            return this
        }

        fun setSingle(single: Boolean): Builder {
            isSingle = single
            return this
        }

        fun create(): CommonDialog {
            commonDialog = CommonDialog(this)
            return commonDialog
        }

        fun show(): CommonDialog {
            commonDialog = create()
            commonDialog.show()
            return commonDialog
        }

        private fun build(@Style style: Int) {
            when (style) {
                STYLE_DEF -> configDef()
            }
        }

        private fun configDef() {
            //title
            title = ""
            titleBold = true
            titleSize = 18f
            titleColor = R.color.base_000
            //content
            content = ""
            contentBold = false
            contentSize = 14f
            contentColor = R.color.base_000
            gravity = Gravity.CENTER
            //start btn
            start = "取消"
            startBold = false
            startSize = 14f
            startColor = R.color.base_000
            startCallback = null
            //end btn
            end = "确认"
            endBold = false
            endSize = 14f
            endColor = R.color.base_000
            endCallback = null
            //func
            isCancel = true
            isSingle = false
        }
    }

    interface OnStartClickCallback {
        fun onStartCallback()
    }

    interface OnEndClickCallback {
        fun onEndCallback()
    }

}