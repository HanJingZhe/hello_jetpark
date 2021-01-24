package com.qtgm.k210103_hello_navigation.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.qtgm.k210103_hello_navigation.MainActivity
import com.qtgm.k210103_hello_navigation.R
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.etName
import kotlinx.android.synthetic.main.fragment_register.etPwd

class LoginFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val name = arguments?.getString("username")
        val pwd = arguments?.getString("userpwd")

        etName.setText(name)
        etPwd.setText(pwd)

        btnEnter.setOnClickListener {
            startActivity(Intent(activity, MainActivity::class.java))
        }


    }
}