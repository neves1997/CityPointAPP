package ipvc.estg.citypointecgm

import adapter.NotaAdapter
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import entities.Notaent
import viewModel.NotaViewModel
import java.time.LocalDateTime



class notaspessoais : AppCompatActivity(), CellClickListener {


    private lateinit var notaViewModel: NotaViewModel
    private val newNotaActivityRequestCode = 1
    private val editNotaActivityCode = 2



    val date = LocalDateTime.now().toString().split("T")!!.toTypedArray()
    val stdate = date[0]?.split("-")!!.toTypedArray()
    val finaldate = stdate[2]+"-"+stdate[1]+"-"+stdate[0]



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notaspessoais)
        supportActionBar?.hide()



        // recycler view
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = NotaAdapter(this,this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        // view model
        notaViewModel = ViewModelProvider(this).get(NotaViewModel::class.java)
        notaViewModel.allNotas.observe(this, { notaent ->
            //Update the cached copy of words in the adapter.
            notaent?.let { adapter.setNotas(it) }
        })

        //fab
        val fab = findViewById<FloatingActionButton>(R.id.fabbt)
        fab.setOnClickListener {
            val intent = Intent(this@notaspessoais, AddNota::class.java)
            startActivityForResult(intent, newNotaActivityRequestCode)
        }

    }






    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newNotaActivityRequestCode){
            if (resultCode == Activity.RESULT_OK) {
           val titulo = data?.getStringExtra(AddNota.EXTRA_TITULO).toString()
           val texto = data?.getStringExtra(AddNota.EXTRA_TEXTO).toString()
            val data = data?.getStringExtra(AddNota.EXTRA_DATA).toString()

            if (titulo!=null && texto!=null && data!=null) {
                val notaent = Notaent(titulo = titulo, texto = texto, data = finaldate)
                notaViewModel.insert(notaent)

            }

        } else {
            Toast.makeText(
                applicationContext,
                "Nota vazia",
                Toast.LENGTH_LONG
            ).show()

        }

        } else if (requestCode == editNotaActivityCode) {
            if (resultCode == Activity.RESULT_OK){
                if (data?.action == "DELETE"){
                    val id = data?.getStringExtra(EXTRA_ID).toString()
                    notaViewModel.delete(Integer.parseInt(id))

                } else {
                    val id = data?.getStringExtra(EXTRA_ID).toString()
                    val titulo = data?.getStringExtra(EXTRA_TITULO).toString()
                    val texto = data?.getStringExtra(EXTRA_TEXTO).toString()
                    val data = data?.getStringExtra(EXTRA_DATA).toString()
                    notaViewModel.update(Integer.parseInt(id), titulo, texto, data)

                }

            } else {
                Toast.makeText(applicationContext,
                        data?.getStringExtra(EXTRA_ERRO).toString(),
                        Toast.LENGTH_LONG).show()
            }
            
        }
    }

    override fun onCellClickListener(notaent: Notaent) {

        val id = notaent.id.toString()
        val titulo = notaent.titulo
        val texto = notaent.texto
        val data = notaent.data
        val intent = Intent(this,EditNota::class.java).apply {
            putExtra(EXTRA_ID, id)
            putExtra(EXTRA_TITULO, titulo)
            putExtra(EXTRA_TEXTO, texto)
            putExtra(EXTRA_DATA,data)

        }

        startActivityForResult(intent, editNotaActivityCode)

    }

    companion object {
        const val EXTRA_ID = "ID"
        const val EXTRA_TITULO = "TITULO"
        const val EXTRA_TEXTO = "TEXTO"
        const val EXTRA_DATA = "DATA"
        const val EXTRA_ERRO = "ERRO"

    }

    fun sairapp(view: View) {
        finishAffinity()
        System.exit(0)

    }

    fun voltar(view: View) {
        val intent = Intent(this, MainActivity::class.java).apply {

        }

        startActivity(intent)
    }

    }