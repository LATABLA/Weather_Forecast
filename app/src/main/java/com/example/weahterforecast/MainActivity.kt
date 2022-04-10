package com.example.weahterforecast

/*  main background color HEX (gradient)
#11103a  #2b5876  #4e4376
*/

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("EEE, MMM d, ''yy h:mm a")
        val formattedDate = sdf.format(calendar.time)

        val x1Temp: TextView = findViewById(R.id.D_Temp)
        val x2Location: TextView = findViewById(R.id.D_Location)
        val x3DateHour: TextView = findViewById(R.id.D_Date_Hour)

        x1Temp.text = ""
        x2Location.text = ""
        x3DateHour.text = formattedDate

        weatherAPI()


    }

    private fun weatherAPI (){



    }



}
