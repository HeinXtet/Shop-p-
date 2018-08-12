package com.heinhtet.deevd.shop.ui

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.heinhtet.deevd.shop.R
import com.heinhtet.deevd.shop.dataservice.AppViewModel
import android.arch.lifecycle.ViewModelProviders
import android.util.Log
import com.heinhtet.deevd.shop.model.entity.UserEntity


class HomeActivity : AppCompatActivity() {

    private lateinit var viewModel  : AppViewModel

    private val TAG = "HomeActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }


}
