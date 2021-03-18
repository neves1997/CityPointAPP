package ipvc.estg.citypointecgm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import ipvc.estg.citypointecgm.MainActivity as MainActivity1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //classe
    }


    //evento butão login
    //fazer validações

    fun buttonlogin(view: View) {



    }

    fun buttonnotas(view: View) {

            val intent = Intent (this,notaspessoais::class.java).apply {

            }

        startActivity(intent)

        }


    }


