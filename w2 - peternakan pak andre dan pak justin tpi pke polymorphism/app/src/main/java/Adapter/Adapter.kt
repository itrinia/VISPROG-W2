package Adapter

import Datapokoknya.globalvar
import Model.Sapi
import Model.Ayam
import Model.Kambeng
import Model.Ternak
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.atz.vpweek2_polymorphism.NewData
import com.atz.vpweek2_polymorphism.R
import com.atz.vpweek2_polymorphism.databinding.KartuBinding
import kotlinx.android.synthetic.main.kartu.view.*


class Adapter(val listdata: ArrayList<Ternak>) :
    RecyclerView.Adapter<Adapter.viewHolder>() {

    class viewHolder(val itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val binding = KartuBinding.bind(itemView)

        val editBtn: Button
        init {
            editBtn = itemView.findViewById(R.id.Edit)
        }

        val deleteBtn: Button
        init {
            deleteBtn = itemView.findViewById(R.id.Delete)
        }

        fun setData(data: Ternak) {
            binding.NamaHewan.text = data.nama
            binding.Usia.text = data.usia.toString()
            if (data.imageternak.isNotEmpty()) {
                binding.sepi.setImageURI(Uri.parse(data.imageternak))
            }
            if (data is Ayam) {
                binding.JenisHewan.text = "Ayam"
            } else if (data is Sapi) {
                binding.JenisHewan.text = "Sapi"
            } else {
                binding.JenisHewan.text = "Kambing"
            }
            itemView.sound.setOnClickListener {
                Toast.makeText(itemView.context, data.interaction(), Toast.LENGTH_SHORT).show()
            }

            itemView.mkn.setOnClickListener {
                if (data is Sapi || data is Kambeng) {
                    Toast.makeText(itemView.context, data.mkn(1), Toast.LENGTH_SHORT).show()
                } else if (data is Ayam){
                    Toast.makeText(itemView.context, data.mkn("1"), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.kartu, parent, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.setData(listdata[position])

        holder.editBtn.setOnClickListener {
            val editt = Intent(it.context, NewData::class.java).apply {
                putExtra("position", position)
            }
            it.context.startActivity(editt)
        }

        holder.deleteBtn.setOnClickListener{
            val deletee = Intent(it.context, NewData::class.java).apply {
                putExtra("position", position)
            }
            globalvar.ternakkq.removeAt(position)
            notifyDataSetChanged()
            it.context.startActivity(deletee)
        }

    }

    override fun getItemCount(): Int {
        return listdata.size
    }
}