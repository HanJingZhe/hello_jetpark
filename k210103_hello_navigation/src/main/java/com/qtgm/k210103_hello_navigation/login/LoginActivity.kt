package com.qtgm.k210103_hello_navigation.login

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.qtgm.base.permission.PermissionX
import com.qtgm.base.utils.MsLog
import com.qtgm.base.utils.MsToast
import com.qtgm.k210103_hello_navigation.R

class LoginActivity : AppCompatActivity() {

    /*val permissions = String
        Manifest.permission.WRITE_EXTERNAL_STORAGE
        Manifest.permission.READ_EXTERNAL_STORAGE
        Manifest.permission.RECORD_AUDIO
    }*/

    private val permissions =
        arrayOf(Manifest.permission.CALL_PHONE, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        PermissionX.requestPermission(
            this, *permissions
        ) { all, list ->
            if (all) {
                MsToast.toastL("is all gent")
            } else {
                MsLog.e(list.toString())
            }
        }


    }

}

