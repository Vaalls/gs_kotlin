package com.github.gabriel_valls_rm95590

import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.gabriel_valls_rm95590.adapter.EcoDicaAdapter
import com.github.gabriel_valls_rm95590.model.EcoDica
import com.github.gabriel_valls_rm95590.repository.EcoDicaDao
import com.github.gabriel_valls_rm95590.repository.EcoDicaDatabase
import com.github.gabriel_valls_rm95590.ui.theme.Gabriel_valls_rm95590Theme

class MainActivity : ComponentActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var adapter: EcoDicaAdapter
    private val ecoDicaDao: EcoDicaDao by lazy {
        EcoDicaDatabase.getDatabase(this).ecoDicaDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        // Adicionar dicas mockadas
        val ecoDicas = listOf(
            EcoDica("Use lâmpadas LED", "As lâmpadas LED consomem menos energia e têm maior durabilidade."),
            EcoDica("Desligue aparelhos que não estão em uso", "Desconectar aparelhos evita o consumo em modo de espera."),
            EcoDica("Use a água de forma consciente", "Feche a torneira enquanto escova os dentes para economizar água.")
        )

        // Configurar Adapter
        adapter = EcoDicaAdapter(ecoDicas) { ecoDica ->
            Toast.makeText(this, "${ecoDica.titulo}: ${ecoDica.descricao}", Toast.LENGTH_SHORT).show()
        }

        recyclerView.adapter = adapter

        // Funcionalidade de busca
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredDicas = ecoDicas.filter { it.titulo.contains(newText ?: "", true) }
                adapter = EcoDicaAdapter(filteredDicas) { ecoDica ->
                    Toast.makeText(this@MainActivity, "${ecoDica.titulo}: ${ecoDica.descricao}", Toast.LENGTH_SHORT).show()
                }
                recyclerView.adapter = adapter
                return true
            }
        })
    }
}