package com.example.a6floor

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val forwardButton: Button = findViewById(R.id.forwardButton)
        val backwardButton: Button = findViewById(R.id.backwardButton)
        val statusTextView: TextView = findViewById(R.id.statusTextView)

        forwardButton.setOnClickListener {
            controlMotor("forward", statusTextView)
        }

        backwardButton.setOnClickListener {
            controlMotor("backward", statusTextView)
        }
    }

    private fun controlMotor(direction: String, statusTextView: TextView) {
        // NetworkModule을 통해 service 인스턴스를 가져옵니다.
        val call = NetworkModule.motorControlService.controlMotor(direction)
        call.enqueue(object : Callback<MotorStatus> {
            override fun onResponse(call: Call<MotorStatus>, response: Response<MotorStatus>) {
                if (response.isSuccessful) {
                    statusTextView.text = "모터 상태: ${response.body()?.status}"
                } else {
                    statusTextView.text = "모터 상태: 요청 실패"
                }
            }

            override fun onFailure(call: Call<MotorStatus>, t: Throwable) {
                statusTextView.text = "모터 상태: 네트워크 오류"
            }
        })
    }
}