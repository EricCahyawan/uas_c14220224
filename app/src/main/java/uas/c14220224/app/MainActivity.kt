package uas.c14220224.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import uas.c14220224.app.database.daftarKesehatan
import uas.c14220224.app.database.daftarKesehatanDB

class MainActivity : AppCompatActivity() {
    private lateinit var DB: daftarKesehatanDB
    private lateinit var customAdapter: customAdapter
    private var arDaftar: MutableList<daftarKesehatan> = mutableListOf()
    lateinit var fabAdd: FloatingActionButton
    lateinit var rvdaftarKesehatan: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        DB = daftarKesehatanDB.getDatabase(this)
        customAdapter = customAdapter(arDaftar)
        rvdaftarKesehatan = findViewById(R.id.rvdaftarKesehatan)
        rvdaftarKesehatan.layoutManager = LinearLayoutManager(this)
        rvdaftarKesehatan.adapter = customAdapter
        fabAdd = findViewById(R.id.fabAdd)
        fabAdd.setOnClickListener {
            val intent = Intent(this, tambah_simpan::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.Main).async {
            val daftarBelanja = DB.fundaftarKesehatanDB().selectAll()
            customAdapter.isiData(daftarBelanja)
            Log.d("data ROOM", daftarBelanja.toString())
        }
    }
}