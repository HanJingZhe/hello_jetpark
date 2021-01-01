package com.qtgm.hello_coroutine

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.qtgm.hello_coroutine.entity.Repo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_day2.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@SuppressLint("SetTextI18n")
class Day2Activity : AppCompatActivity() {

    val TAG = Day2Activity::class.java.name

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()
    private val api = retrofit.create(MyApi::class.java)


    val scope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day2)

        //使用retrofit
//        original()
        //结合rx使用retrofit
//        rxFun()
        //集合coroutine使用retrofit
//        coroutineFun()

//        zip()

//        doHomeWork2()
        testMainScope()
    }

    private fun testMainScope() {

        scope.launch {
            MsLogUtils.e("thread = ${Thread.currentThread().name}")
        }
    }

    private fun doHomeWork2() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val repo1 = async { api.coroutineListRepos("rengwuxian") }
                val repo2 = async { api.coroutineListRepos("google") }
                tvShow.text = "${repo1.await()[0].name} - ${repo2.await()[0].name}"
            } catch (e: Exception) {
                tvShow.text = "exception = ${e.message}"
                // retrofit2.HttpException: HTTP 403 rate limit exceeded
                //这里网络请求报403 为什么捕获不了呢? 还是会崩溃
            }
        }
    }

    private fun zip() {

        //编译会爆红
        /* Single.zip(
             api.rxListRepos("rengwuxian"),
             api.rxListRepos("google"),
             { list1, list2 -> "${list1[0].name} - ${list2[0].name}" }
         ).observeOn(AndroidSchedulers.mainThread())
             .subscribe { combined -> tvShow.text = combined }*/


        GlobalScope.launch(Dispatchers.Main) {
            try {
                val repos1 = async { api.coroutineListRepos("HanJingZhe") }
                val repos2 = async { api.coroutineListRepos("google") }
//            val repos3 = async { api.coroutineListRepos("rengwuxian") }
                tvShow.text =
                    "${repos1.await()[0].name} - ${repos2.await()[0].name}"
            } catch (e: Exception) {
                MsLogUtils.e("exception = ${e.message}")
            }
        }

        /*scope.launch {
//            try {
                val repos1 = async { api.coroutineListRepos("HanJingZhe") }
                val repos2 = async { api.coroutineListRepos("rengwuxian") }
//                val repos3 = async { api.coroutineListRepos("google") }
                tvShow.text =
                    "${repos1.await()[0].name} - ${repos2.await()[0].name}"
//            } catch (e: Exception) {
//                tvShow.text = e.message
//            }
        }*/
    }

    private fun coroutineFun() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val listRepo = api.coroutineListRepos("HanJingZhe")
                tvShow.text = listRepo[0].name
            } catch (e: Exception) {
                MsLogUtils.e("get repo is error ${e.message}")
            }
        }
    }

    private fun rxFun() {
        api.rxListRepos("HanJingZhe")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<Repo>> {
                override fun onSuccess(t: List<Repo>?) {
                    tvShow.text = t?.get(0)?.name
                }

                override fun onSubscribe(d: Disposable?) {
                }

                override fun onError(e: Throwable?) {
                    tvShow.text = e?.message
                }
            })
    }

    private fun original() {
        api.listRepos("HanJingZhe")
            .enqueue(object : Callback<List<Repo>> {
                override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                    MsLogUtils.e(TAG, "failure ${t.message}")
                }

                override fun onResponse(
                    call: Call<List<Repo>>, response: Response<List<Repo>>
                ) {
                    val listRepo = response.body()
                    tvShow.text = StringBuilder().apply {
                        listRepo?.forEach {
                            append(it.name).append("\n")
                        }
                        toString()
                    }
                }
            })
    }


}


