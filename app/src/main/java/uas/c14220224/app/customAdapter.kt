package uas.c14220224.app

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import uas.c14220224.app.database.daftarKesehatan

class customAdapter(private val daftarKesehatan: MutableList<daftarKesehatan>) : RecyclerView.Adapter<customAdapter.ListViewHolder>(){
    private lateinit var onItemClickCallBack: OnItemClickCallback
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): customAdapter.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return daftarKesehatan.size
    }

    override fun onBindViewHolder(holder: customAdapter.ListViewHolder, position: Int) {
        val item = daftarKesehatan[position]
        holder.tvidCatatan.setText(item.idCatatan)
        holder.tvtanggalDanJam.setText(item.tanggalDanJam)
        holder.tvberatBadan.setText(item.beratBadan)
        holder.tvtekananDarah.setText(item.tekananDarah)
        holder.tvcatatan.setText(item.catatan)
        holder.main.setOnClickListener {
            onItemClickCallBack.delData(item)
        }
    }

    class ListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvidCatatan = itemView.findViewById<TextView>(R.id.tvidCatatan)
        val tvtanggalDanJam = itemView.findViewById<TextView>(R.id.tvtanggalDanJam)
        val tvberatBadan = itemView.findViewById<TextView>(R.id.tvberatBadan)
        val tvtekananDarah = itemView.findViewById<TextView>(R.id.tvtekananDarah)
        val tvcatatan = itemView.findViewById<TextView>(R.id.tvcatatan)
        val main = itemView.findViewById<ConstraintLayout>(R.id.main)
    }

    interface OnItemClickCallback {
        fun delData(dtBelanja: daftarKesehatan)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallBack = onItemClickCallback
    }

}