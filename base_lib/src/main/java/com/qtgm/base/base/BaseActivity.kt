package com.qtgm.base.base

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.qtgm.base.dialog.MsLoadingDialog

/**
 * @author peng.wang08
 * @date 2021/1/29
 */
abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var mContext: Context
    protected lateinit var TAG: String

    protected val msLoadingDialog: MsLoadingDialog by lazy { MsLoadingDialog(mContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        TAG = this.javaClass.simpleName
        setContentView(setLayoutId())
        initView()
        initData()
    }

    abstract fun setLayoutId(): Int
    abstract fun initView()
    abstract fun initData()

    override fun onDestroy() {
        msLoadingDialog.dismiss()
        super.onDestroy()
    }

    /**
     * 处理点击缩回软键盘
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            val view = currentFocus
            if (view != null && view is EditText) {
                val leftTop = intArrayOf(0, 0)
                view.getLocationInWindow(leftTop)
                val left = leftTop[0]
                val top = leftTop[1]
                val bottom = top + view.height
                val right = left + view.width
                if (ev.x > left && ev.x < right && ev.y > top && ev.y < bottom) {
                    //点击的是正在操作的EditText,保留点击事件
                } else {
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                }
            }
        }
        if (window.superDispatchTouchEvent(ev)) {
            return true
        }
        return super.dispatchTouchEvent(ev)
    }

}