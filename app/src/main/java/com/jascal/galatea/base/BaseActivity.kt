package com.jascal.galatea.base

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.jascal.galatea.base.I.IView

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/12/26 10:30 AM
 * @email jascal@163.com
 * */

abstract class BaseActivity : AppCompatActivity(), IView {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(layoutID())
        initData()
        initView()
    }

    abstract fun layoutID(): Int

    abstract fun initData()

    abstract fun initView()

}