package com.example.videocall

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.videocall.utils.PeerConnectionObserver
import org.webrtc.IceCandidate
import org.webrtc.MediaStream

class VideoCalling : AppCompatActivity() {

    private var socketManager: SocketManager? = null
    private var rtcclient: RTCClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_calling)

        socketManager = SocketManager()
        val roomID = intent.getStringExtra("roomId")!!
        rtcclient = RTCClient(
            application, socketManager!!,
            object : PeerConnectionObserver() {
                override fun onIceCandidate(p0: IceCandidate?) {
                    super.onIceCandidate(p0)
                }
                override fun onAddStream(p0: MediaStream?) {
                    super.onAddStream(p0)
                }
            }
        )

        rtcclient?.initializeSurfaceView(findViewById(R.id.localVideoView))
        rtcclient?.startLocalVideo(findViewById(R.id.localVideoView))
        rtcclient?.call(roomID)
    }
}