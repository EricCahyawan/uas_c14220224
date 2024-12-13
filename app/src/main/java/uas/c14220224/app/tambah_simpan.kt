package uas.c14220224.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import uas.c14220224.app.database.daftarKesehatanDB

class tambah_simpan : AppCompatActivity() {
    lateinit var uploadBtn: Button
    lateinit var downloadBtn: Button
    val DB = Firebase.firestore
    val rooomDB = daftarKesehatanDB.getDatabase(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_simpan)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        uploadBtn = findViewById(R.id.uploadBtn)
        downloadBtn = findViewById(R.id.downloadBtn)
        uploadBtn.setOnClickListener {
            CoroutineScope(Dispatchers.Main).async {
                val daftarKesehatan = rooomDB.fundaftarKesehatanDB().selectAll()
                daftarKesehatan.forEach {
                    val item = hashMapOf(
                        "tanggalDanJam" to it.tanggalDanJam,
                        "beratBadan" to it.beratBadan,
                        "tekananDarah" to it.tekananDarah,
                        "catatan" to it.catatan,
                        "idCatatan" to it.idCatatan
                    )

                    DB.collection("daftarKesehatan").whereEqualTo("namaItem", item["idCatatan"]).get().addOnSuccessListener {
                            documents ->
                        if (documents.isEmpty){
                            DB.collection("daftarKesehatan").add(item)
                                .addOnSuccessListener {
                                    Toast.makeText(this@tambah_simpan, "Upload Berhasil", Toast.LENGTH_SHORT).show()
                                    finish()
                                }
                                .addOnFailureListener{
                                    Toast.makeText(this@tambah_simpan, "Upload Gagal", Toast.LENGTH_SHORT).show()
                                    finish()
                                }
                        }
                        else{
                            Toast.makeText(this@tambah_simpan, "Upload Gagal", Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            }
        }
    }
}