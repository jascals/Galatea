package com.jascal.galatea.mvvm.play

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.jascal.galatea.IMusicPlayer
import com.jascal.galatea.R
import com.jascal.galatea.base.BaseActivity
import com.jascal.galatea.remote.Config

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2019/1/9 4:55 PM
 * @email jascal@163.com
 * */

class PlayerActivity : BaseActivity() {
    private lateinit var musicPlayer: IMusicPlayer
    private var songID: Int = -1

    override fun layoutID(): Int {
        return R.layout.activity_player
    }

    override fun initData() {
        songID = intent.getIntExtra("songID", 0)
        Log.d("aidl-galatea", "song id is $songID")
        initService()
        // https://music.163.com/song/media/outer/url?id=$songID.mp3
        // retrofit GET /song/url?id=$songID
    }

    override fun initView() {
//        musicPlayer.play("")
    }

    private fun initService() {
        val intent = Intent()
        intent.action = Config.ACTION
        intent.`package` = Config.PACKAGE
        startService(intent)
        bindService(intent, conn, Context.BIND_AUTO_CREATE)
    }

    private val conn = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
            musicPlayer = IMusicPlayer.Stub.asInterface(iBinder)
            Toast.makeText(this@PlayerActivity, "ready", Toast.LENGTH_SHORT).show()
            Log.d("aidl-galatea", "in playerActivity, and process is ${android.os.Process.myPid()}")
            musicPlayer.play("https://music.163.com/song/media/outer/url?id=$songID.mp3")
        }

        override fun onServiceDisconnected(componentName: ComponentName) {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(conn)
    }

}