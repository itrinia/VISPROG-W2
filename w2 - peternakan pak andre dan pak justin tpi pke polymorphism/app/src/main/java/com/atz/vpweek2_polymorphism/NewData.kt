package com.atz.vpweek2_polymorphism


import android.content.Intent
import Model.Ayam
import Model.Sapi
import Model.Kambeng
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import Datapokoknya.globalvar
import Model.Ternak
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.atz.vpweek2_polymorphism.databinding.ActivityNewDataBinding
import kotlinx.android.synthetic.main.activity_new_data.*

class NewData : AppCompatActivity() {
    private lateinit var viewBind: ActivityNewDataBinding
    private lateinit var ternak: Ternak
    var position = -1
    var image: String = ""

    private val GetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){   // successfully added image
            val uri = it.data?.data
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if(uri != null){
                    baseContext.getContentResolver().takePersistableUriPermission(uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                }}// GET PATH TO IMAGE FROM GALLY
            viewBind.plez.setImageURI(uri)
            image = uri.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityNewDataBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        supportActionBar?.hide()
        radioError.visibility = View.GONE
        getintent()
        listener()
        display()
    }

    private fun getintent(){
        position = intent.getIntExtra("position", -1)

        if(position != -1){
            val Ternak = globalvar.ternakkq[position]
            viewBind.plez.setImageURI(Uri.parse(globalvar.ternakkq[position].imageternak))
        }
        if (AddJenisHewan.checkedRadioButtonId == R.id.radioButton) {
            ternak = Sapi(AddNamaHewan.editText?.text.toString(), AddUsiaHewan.editText?.text.toString().toInt(), "")
        } else if (AddJenisHewan.checkedRadioButtonId == R.id.radioButton2){
            ternak = Ayam(AddNamaHewan.editText?.text.toString(), AddUsiaHewan.editText?.text.toString().toInt(), "")
        } else if (AddJenisHewan.checkedRadioButtonId == R.id.radioButton3){
            ternak = Kambeng(AddNamaHewan.editText?.text.toString(), AddUsiaHewan.editText?.text.toString().toInt(), "")
        }
    }

    private fun listener(){
        viewBind.addimage.setOnClickListener{
            val myIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            myIntent.type = "image/*"
            GetResult.launch(myIntent)
        }

        viewBind.Add.setOnClickListener{
            var nama = viewBind.AddNamaHewan.editText?.text.toString().trim()
            var usia = 0

            var jenis =
                if (viewBind.AddJenisHewan.checkedRadioButtonId == viewBind.radioButton.id){
                    "Ayam"
                }else if(viewBind.AddJenisHewan.checkedRadioButtonId == viewBind.radioButton2.id){
                    "Sapi"
                }else {
                    "Kambing"
                }
            finish()

            ternak = Ternak(nama, usia, jenis)
            checker()
        }
    }

    private fun checker()
    {
        var isCompleted:Boolean = true

        if(ternak.nama!!.isEmpty()){
            viewBind.AddNamaHewan.error = "Nama Hewan gbs ksg"
            isCompleted = false
        }else{
            viewBind.AddNamaHewan.error = ""
        }


        ternak.imageternak = image

        if(viewBind.AddUsiaHewan.editText?.text.toString().isEmpty() || viewBind.AddUsiaHewan.editText?.text.toString().toInt() < 0)
        {
            viewBind.AddUsiaHewan.error = "Usia cannot be empty or 0"
            isCompleted = false
        }

        if(isCompleted == true)
        {
            if(position == -1)
            {
                ternak.usia = viewBind.AddUsiaHewan.editText?.text.toString().toInt()
                globalvar.ternakkq.add(ternak)

            }else
            {
                ternak.usia = viewBind.AddUsiaHewan.editText?.text.toString().toInt()
                globalvar.ternakkq[position] = ternak
            }
            finish()
        }
    }

    private fun display() {
        if (position != -1) {
            AddNamaHewan.editText!!.setText(globalvar.ternakkq.get(position).nama)

            AddUsiaHewan.editText!!.setText(globalvar.ternakkq.get(position).usia.toString())

            if (globalvar.ternakkq.get(position).imageternak != "") {
                addimage.setImageURI(
                    Uri.parse(
                        globalvar.ternakkq.get(position).imageternak
                    )
                )
            }
        }
    }
}