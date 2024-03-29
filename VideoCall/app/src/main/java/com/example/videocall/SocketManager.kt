package com.example.videocall

import com.example.videocall.utils.Constants.SOCKET_URL
import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONException
import org.json.JSONObject
import org.webrtc.SessionDescription

class SocketManager {

    var socket:Socket? = null

    init {
        socket = IO.socket(SOCKET_URL)
    }
    fun sendOffer(offer: SessionDescription, roomId: String) {
        val jsonObject = JSONObject()
        try {
            val sanitizedSdp = JSONObject.quote(offer.description)
            jsonObject.put("sdp", sanitizedSdp)
            jsonObject.put("roomId", roomId)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        socket?.emit("offer", jsonObject)
    }

    fun connect(){
        socket?.connect()
    }
    fun disconnect() {
        socket?.disconnect()
        socket?.off()
    }
}