package ipvc.estg.citypointecgm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import dataclasse.PlaceNota

class nota : AppCompatActivity() {

    private lateinit var myList: ArrayList<PlaceNota>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nota)

        myList = ArrayList<PlaceNota>()


    }

    fun insert(view: View) {



    }
}