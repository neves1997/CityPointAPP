package ipvc.estg.citypointecgm

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class AddNota : AppCompatActivity() {

    private lateinit var editNotaView1 : EditText
    private lateinit var editNotaView2 : EditText
    private lateinit var editNotaView3 : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_nota)

        editNotaView1 = findViewById(R.id.textitle)
        editNotaView2= findViewById(R.id.textexto)
        editNotaView3 = findViewById(R.id.textdate)



        val button = findViewById<Button>(R.id.buttonsave)
        button.setOnClickListener{
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editNotaView1.text) || TextUtils.isEmpty(editNotaView2.text)|| TextUtils.isEmpty(editNotaView3.text)){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val notatitulo = editNotaView1.text.toString()
                replyIntent.putExtra(EXTRA_REPLY,  notatitulo)
                setResult(Activity.RESULT_OK,replyIntent)

                val notatexto = editNotaView2.text.toString()
                replyIntent.putExtra(EXTRA_REPLY1, notatexto)

                val notadata = editNotaView3.text.toString()
                replyIntent.putExtra(EXTRA_REPLY2, notadata)
            }

            finish()

        }
    }

    companion object {
        const val EXTRA_REPLY = "ipvc.estg.citypointecgm.REPLY"
        const val EXTRA_REPLY1 = "ipvc.estg.citypointecgm.REPLY1"
        const val EXTRA_REPLY2 = "ipvc.estg.citypointecgm.REPLY2"


    }
}