package com.example.popcat

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var count:TextView
    //var count:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e(MainActivity::class.java.simpleName, "onCreate()")
        count = findViewById<TextView>(R.id.count).apply {
            text = savedInstanceState?.getString("count") ?: "0"
        }

        var number = count.text.toString().toInt()

        val soundPool = SoundPool.Builder().build()
        var soundId : Int = 0
        soundId = soundPool.load(this, R.raw.sound3, 1)

        var imageView:ImageView = findViewById(R.id.imageView)
        imageView.setImageResource(R.drawable.popcat1)

        imageView.setOnTouchListener(object : View.OnTouchListener{
            @SuppressLint("ClickableViewAccessibility")
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                if (p1 != null) {
                    if(p1.action == MotionEvent.ACTION_DOWN) {
                        imageView.setImageResource(R.drawable.popcat2)
                        number++
                        count.setText(number.toString())
                        soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)

                    } else {
                        imageView.setImageResource(R.drawable.popcat1)
                    }
                }

                return true

            }
        })
    }


    override fun onStop() {

        super.onStop()

        Log.e(MainActivity::class.java.simpleName, "onStop()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("count", count.text.toString())
        Log.e(MainActivity::class.java.simpleName, "onSaveInstanceState()")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.e(MainActivity::class.java.simpleName, "onRestoreInstanceState()")
        if(this::count.isInitialized){
            count.text = savedInstanceState.getString("count") ?: "0"
        }
    }

}