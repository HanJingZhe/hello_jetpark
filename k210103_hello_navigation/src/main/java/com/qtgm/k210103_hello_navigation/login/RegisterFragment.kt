package com.qtgm.k210103_hello_navigation.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.qtgm.k210103_hello_navigation.R
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnGET.setOnClickListener {
            val userName = etName.text.toString()
            val userPwd = etPwd.text.toString()

            if (userName.isNotEmpty() && userPwd.isNotEmpty()) {
                activity?.onBackPressed()
                val bundle = Bundle()
                bundle.putString("username", userName)
                bundle.putString("userpwd", userPwd)
                findNavController().navigate(R.id.loginFragment, bundle)
            }
        }

    }

}

