package uas.c14220224.app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import uas.c14220224.app.database.daftarKesehatan
import uas.c14220224.app.database.daftarKesehatanDB
import uas.c14220224.app.helper.DateHelper.getCurrentDate

class addData : AppCompatActivity() {
    lateinit var etberatBadan: EditText
    lateinit var ettekananDarah: EditText
    lateinit var etcatatan: EditText
    lateinit var saveBtn: Button
    var DB = daftarKesehatanDB.getDatabase(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_data)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etberatBadan = findViewById(R.id.etberatBadan)
        ettekananDarah = findViewById(R.id.ettekananDarah)
        etcatatan = findViewById(R.id.etcatatan)
        saveBtn = findViewById(R.id.saveBtn)
        var tanggal = getCurrentDate()
        saveBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).async {
                DB.fundaftarKesehatanDB().insert(
                    daftarKesehatan(
                        tanggalDanJam = tanggal,
                        beratBadan = etberatBadan.text.toString(),
                        tekananDarah = ettekananDarah.text.toString(),
                        catatan = etcatatan.text.toString()
                    )
                )
            }
            finish()

        }
    }
}