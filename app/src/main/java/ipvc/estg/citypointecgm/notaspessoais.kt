package ipvc.estg.citypointecgm

import adapter.NotaAdapter
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import entities.Notaent
import viewModel.NotaViewModel

class notaspessoais : AppCompatActivity() {

    private lateinit var notaViewModel: NotaViewModel
    private val newNotaActivityRequestCode = 1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notaspessoais)


        // recycler view
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = NotaAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        // view model
        notaViewModel = ViewModelProvider (this).get(NotaViewModel::class.java)
        notaViewModel.allNotas.observe(this, { notaent ->
            //Update the cached copy of words in the adapter.
            notaent?.let { adapter.setNotas(it) }
        })

        //fab
        val fab = findViewById<FloatingActionButton>(R.id.fabbt)
        fab.setOnClickListener {
            val intent = Intent (this@notaspessoais, AddNota::class.java)
            startActivityForResult(intent, newNotaActivityRequestCode)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newNotaActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(AddNota.EXTRA_REPLY)?.let {
                val notaent = Notaent(titulo = it, texto = "Texto",data = "12/03/2021")
                notaViewModel.insert(notaent)
            }
        } else {
            Toast.makeText(
                    applicationContext,
                    "Nota vazia",
                    Toast.LENGTH_LONG).show()

        }
    }


}