package com.example.canvasjava

import androidx.appcompat.app.AppCompatActivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var mainLayout: ViewGroup? = null
    private var image: ImageView? = null

    private var xDelta: Int = 0
    private var yDelta: Int = 0


    private var updatedLocationTextValue: TextView? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainLayout = findViewById(R.id.mainimage)
        image = findViewById(R.id.imageview)
        updatedLocationTextValue = findViewById(R.id.text)

        image!!.setOnTouchListener(onTouchListener())
    }

    private fun onTouchListener(): View.OnTouchListener {
        return View.OnTouchListener { view, event ->
            val x = event.rawX.toInt()
            val y = event.rawY.toInt()

            when (event.action and MotionEvent.ACTION_MASK) {

                MotionEvent.ACTION_DOWN -> {
                    val lParams = view.layoutParams as LinearLayout.LayoutParams
                    xDelta = x - lParams.leftMargin
                    yDelta = y - lParams.topMargin
                }

                MotionEvent.ACTION_UP -> {
                    val dataX = event.rawX
                    val dataY = event.rawX
                    updatedLocationTextValue!!.text = "X Value is: $dataX AND Y Value is: $dataY"
                }

                MotionEvent.ACTION_MOVE -> {
                    val layoutParams = view.layoutParams as LinearLayout.LayoutParams
                    layoutParams.leftMargin = x - xDelta
                    layoutParams.topMargin = y - yDelta
                    layoutParams.rightMargin = 0
                    layoutParams.bottomMargin = 0
                    view.layoutParams = layoutParams
                }
            }
            mainLayout!!.invalidate()
            true
        }
    }
}
