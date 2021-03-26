package ipvc.estg.citypointecgm

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import java.time.LocalDateTime

class AddNota : AppCompatActivity() {

    private lateinit var editNotaView1 : EditText
    private lateinit var editNotaView2 : EditText
    private lateinit var editNotaView3 : EditText





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_nota)
        supportActionBar?.hide()


        val date = LocalDateTime.now().toString().split("T")!!.toTypedArray()
        val stdate = date[0]?.split("-")!!.toTypedArray()
        val finaldate = stdate[2]+"-"+stdate[1]+"-"+stdate[0]


        editNotaView1 = findViewById(R.id.textitle)
        editNotaView2= findViewById(R.id.textexto)
        editNotaView3 = findViewById(R.id.textdate)

        editNotaView3.setText(finaldate.toString())



        val button = findViewById<Button>(R.id.buttonsave)
        button.setOnClickListener{
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editNotaView1.text) || TextUtils.isEmpty(editNotaView2.text)|| TextUtils.isEmpty(editNotaView3.text)){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val notatitulo = editNotaView1.text.toString()
                replyIntent.putExtra(EXTRA_TITULO,  notatitulo)

                val notatexto = editNotaView2.text.toString()
                replyIntent.putExtra(EXTRA_TEXTO, notatexto)

                val notadata = editNotaView3.text.toString()
                replyIntent.putExtra(EXTRA_DATA, notadata)

                setResult(Activity.RESULT_OK,replyIntent)

            }


            finish()

        }
    }

    companion object {
        const val EXTRA_TITULO = "titulo"
        const val EXTRA_TEXTO = "texto"
        const val EXTRA_DATA = "data"


    }

    fun sairapp(view: View) {

        finishAffinity()
        System.exit(0)
    }



    fun voltar(view: View) {

        val intent = Intent(this, notaspessoais::class.java).apply {

        }
        startActivity(intent)

    }
}