package com.qtgm.k210103_hello_navigation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.qtgm.base.utils.MyLog

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

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration.Builder(navController.graph).build()
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        //listener
        navController.addOnDestinationChangedListener(object :
            NavController.OnDestinationChangedListener {
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                //收到切换监听
                MyLog.e("Navigation switch destination ")
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            navController
        ) || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            navController,
            appBarConfiguration
        ) || super.onSupportNavigateUp()
    }


}
