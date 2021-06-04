package com.qtgm.base.base

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.qtgm.base.dialog.LoadingDialog

/**
 * @author peng.wang08
 * @date 2021/1/29
 */
abstract class BaseActivity : AppCompatActivity() {

    protected val TAG: String = this.javaClass.simpleName
    protected lateinit var mContext: Context
    protected val mLoadingDialog: LoadingDialog by lazy { LoadingDialog(mContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        setContentView(setLayoutId())
        setStatusBar()
        initView()
        initData()
    }

    abstract fun setLayoutId(): Int
    abstract fun initView()
    abstract fun initData()


    /**
     * 是否预留出状态栏高度
     */
    protected open fun isPadding(): Boolean = true

    /**
     * 设置状态栏颜色
     */
    protected open fun setStatusBarColor(): Int = Color.TRANSPARENT

    /**
     * 是否设置状态栏字体颜色为暗色
     */
    protected open fun setStatusDark(): Boolean = true

    private fun setStatusBar(
        color: Int = setStatusBarColor(),
        dark: Boolean = setStatusDark(),
        isPadding: Boolean = isPadding()
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = color
        }
        //6.0以上 android本身支持状态栏字体颜色设置
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or if (dark) View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR else 0
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        if (isPadding) {
            val rootView: ViewGroup =
                window.decorView.findViewById(android.R.id.content) as ViewGroup
            rootView.setPadding(0, getStatusBarHeight(), 0, 0)
        }
    }

    private fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    override fun onDestroy() {
        mLoadingDialog.dismiss()
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