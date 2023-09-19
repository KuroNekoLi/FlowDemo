package com.example.flowdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Producer 產生資料流
        val myFlow = flow<Int> {
            for (i in 1..100){
                emit(i)
                delay(1000L)
            }
        }

        val textView = findViewById<TextView>(R.id.tvResult)

        //Consumer
        CoroutineScope(Main).launch {
            //flow emit 的值
            myFlow.collect{
//                Log.i("LinLi", "Current index is $it: ");
                textView.text = "Current index is $it: "
            }
        }
    }
}