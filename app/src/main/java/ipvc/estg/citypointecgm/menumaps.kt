package ipvc.estg.citypointecgm

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class menumaps : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    var isRemember = true;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menumaps)

        sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)



    fun buttonnotas(view: View) {

        val intent = Intent(this@menumaps, notaspessoais::class.java)
        startActivity(intent)
    }





    }

    override fun onBackPressed() {

    }

    fun mapa(view: View) {

        val intent = Intent(this@menumaps, MapsActivity2::class.java)
        startActivity(intent)
    }

    fun sair(view: View) {
        val sharedPreferences_edit : SharedPreferences.Editor = sharedPreferences.edit()
        sharedPreferences_edit.clear()
        sharedPreferences_edit.apply()

        val intent = Intent(this@menumaps, MainActivity::class.java)
        startActivity(intent)
        finish()

    }
}