package com.info.filmleruygulamasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.info.retrofitkullanimi.ApiUtils
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var kategoriListe:ArrayList<Kategoriler>
    private lateinit var adapter:KategoriAdapter
    private lateinit var kdi:KategorilerDaoInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarKategori.title = "Kategoriler"
        setSupportActionBar(toolbarKategori)

        kategoriRv.setHasFixedSize(true)
        kategoriRv.layoutManager = LinearLayoutManager(this)

        kdi = ApiUtils.getKategorilerDaoInterface()
        tumKategoriler()
    }

    fun tumKategoriler(){
        kdi.tumKategoriler().enqueue(object : Callback<KategorilerCevap>{
            override fun onResponse(call: Call<KategorilerCevap>?, response: Response<KategorilerCevap>?) {

                if(response != null){
                    val liste= response.body().kategoriler
                    adapter = KategoriAdapter(this@MainActivity,liste)

                    kategoriRv.adapter = adapter
                }
            }

            override fun onFailure(call: Call<KategorilerCevap>?, t: Throwable?) {
                TODO("Not yet implemented")
            }

        })
    }
}
