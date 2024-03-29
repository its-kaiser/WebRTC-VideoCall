package com.example.videocall

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.videocall.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var socketManager: SocketManager

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        socketManager = SocketManager()

        socketManager.connect()
        binding.Joinroom.setOnClickListener {
            val roomId = binding.roomId.text.toString()
            socketManager.socket?.emit("join room", roomId)
            val intent = Intent(this, VideoCalling::class.java)
                .putExtra("roomId", roomId)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        socketManager.disconnect()
    }

}