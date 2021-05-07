package ipvc.estg.citypointecgm

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.Menu
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import api.OutputLogin
import api.ServiceBuilder
import retrofit2.Call
import retrofit2.Response
import android.widget.Toast
import api.EndPoints
import api.ServiceBuilder.buildService
import com.google.android.material.floatingactionbutton.FloatingActionButton
import api.Utilizador
import retrofit2.Callback



class MainActivity : AppCompatActivity() {

    private lateinit var editNome: EditText
    private lateinit var editPass: EditText
    private lateinit var checkBox: CheckBox


    lateinit var sharedPreferences: SharedPreferences
    var isRemember = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        //classe


        editNome = findViewById(R.id.textemail)
        editPass = findViewById(R.id.textpass)
        checkBox = findViewById(R.id.lembrar)

        //Criar a variével para verificar se queremos login automático ou não



        sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
        isRemember = sharedPreferences.getBoolean("isRemember", false)

        //Verificamos aqui se o checkbox for selecionado redireciona para o Menu automaticamente
        if(isRemember){
            val intent = Intent(this@MainActivity, menumaps::class.java)
            startActivity(intent)
            finish()
        }


    }


    fun login(view: View) {
        var name = findViewById<EditText>(R.id.textemail)
        var pass = findViewById<EditText>(R.id.textpass)
        val checked_remember: Boolean = checkBox.isChecked

        val intent = Intent(this, menumaps::class.java)

        if(name.text.isNullOrEmpty() || pass.text.isNullOrEmpty()){

            if(name.text.isNullOrEmpty()){
                name.error = "getString(R.string.aviso_email)"
            }
            if(pass.text.isNullOrEmpty()){
                pass.error = "getString(R.string.aviso_pass)"
            }

        }else{
            val request = buildService(EndPoints::class.java)
            val call = request.postLgn(name.text.toString(), pass.text.toString())

            call.enqueue(object : Callback<List<OutputLogin>> {
                override fun onResponse(call: Call<List<OutputLogin>>, response: Response<List<OutputLogin>>) {
                    if (response.isSuccessful){

                        for(OutputLogin in response.body()!!){
                            Log.d("TAG_", OutputLogin.name.toString() + OutputLogin.pass.toString())

                            val shared_preferences_edit : SharedPreferences.Editor = sharedPreferences.edit()


                            shared_preferences_edit.putBoolean("isRemember", checked_remember)
                            shared_preferences_edit.putInt(getString(R.string.id_ut),OutputLogin.id)
                            shared_preferences_edit.apply()


                        }

                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<List<OutputLogin>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "getString(R.string.pass_email_erro)", Toast.LENGTH_SHORT).show()
                }
            })

        }

    }




    fun buttonnotas(view: View) {

            val intent = Intent(this, notaspessoais::class.java).apply {

            }

        startActivity(intent)

        }

    fun sairapp(view: View) {
        finishAffinity()
        System.exit(0)
    }

















}


