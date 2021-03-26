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

class EditNota : AppCompatActivity() {

    private lateinit var titulo_ET : EditText
    private lateinit var texto_ET :EditText
    private lateinit var data_ET : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_nota)
        supportActionBar?.hide()


        val id = intent.getStringExtra(notaspessoais.EXTRA_ID)
        val titulo = intent.getStringExtra(notaspessoais.EXTRA_TITULO)
        val texto = intent.getStringExtra(notaspessoais.EXTRA_TEXTO)
        val data = intent.getStringExtra(notaspessoais.EXTRA_DATA)



        titulo_ET = findViewById(R.id.textitle)
        titulo_ET.setText(titulo)
        texto_ET = findViewById(R.id.textexto)
        texto_ET.setText(texto)
        data_ET = findViewById(R.id.textdate)
        data_ET.setText(data)


        val btsave = findViewById<Button>(R.id.buttonsave)
        val btdelete = findViewById<Button>(R.id.buttondelete)





   btsave.setOnClickListener() {
        val replyIntent = Intent()
        if (TextUtils.isEmpty(titulo_ET.text)) {
            replyIntent.putExtra(notaspessoais.EXTRA_ERRO,"Os campos obrigatórios não podem estar vazios")
            setResult(Activity.RESULT_CANCELED,replyIntent)
        } else if (titulo_ET.text.toString() == titulo && texto_ET.text.toString() == texto) {
            replyIntent.putExtra(notaspessoais.EXTRA_ERRO,"Nota não alterada")
            setResult(Activity.RESULT_CANCELED, replyIntent)
        } else {
            replyIntent.putExtra(notaspessoais.EXTRA_ID, id.toString())
            replyIntent.putExtra(notaspessoais.EXTRA_TITULO, titulo_ET.text.toString())
            replyIntent.putExtra(notaspessoais.EXTRA_TEXTO, texto_ET.text.toString())

            val date = LocalDateTime.now().toString().split("T")!!.toTypedArray()
            val stdate = date[0]?.split("-")!!.toTypedArray()
            val finaldate = stdate[2]+"-"+stdate[1]+"-"+stdate[0]

            replyIntent.putExtra(notaspessoais.EXTRA_DATA, finaldate.toString()  )
            setResult(Activity.RESULT_OK, replyIntent)
        }
        finish()
        }


        btdelete.setOnClickListener(){

        val replyIntent = Intent()
        replyIntent.putExtra(notaspessoais.EXTRA_ID, id.toString())
        replyIntent.setAction("DELETE")
        setResult(Activity.RESULT_OK, replyIntent)

        finish()





    }
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