package com.qtgm.k210103_hello_navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * 学习使用Navigation
 * 1.MainActivity添加 <fragment> 通过name属性标识为一个容器
 *      同时为容器指定一个视图管理 <navigation>
 *      注:所体现出的新东西,通过视图来清晰明白页面跳转关系
 * 2.容器添加视图,通常为fragment,并设置默认启动的页面,
 *      通过根节点 startDestination指定
 * 3.可以使用容器的视图界面来指定页面跳转关系
 *      或通过fragment具体节点来指定<action>
 * 4.使用fragment通过点击监听触发指定跳转的action具体页面
 *      1).Navigation.findNavController(view).navigate(actionId)
 *
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
