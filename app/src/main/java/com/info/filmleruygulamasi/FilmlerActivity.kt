package com.info.filmleruygulamasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.info.retrofitkullanimi.ApiUtils
import kotlinx.android.synthetic.main.activity_filmler.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmlerActivity : AppCompatActivity() {

    private lateinit var filmListe:ArrayList<Filmler>
    private lateinit var adapter:FilmlerAdapter
    private lateinit var fdi:FilmlerDaoInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filmler)

        val kategori = intent.getSerializableExtra("kategoriNesne") as Kategoriler

        toolbarFilmler.title = "Filmler : ${kategori.kategori_ad}"
        setSupportActionBar(toolbarFilmler)

        filmlerRv.setHasFixedSize(true)
        filmlerRv.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

        fdi = ApiUtils.getFilmlerDaoInterface()

        tumFilmlerByKategoriId(kategori.kategori_id)
    }

    fun tumFilmlerByKategoriId(kategori_id:Int){
        fdi.tumFilmlerByKategoriId(kategori_id).enqueue(object : Callback<FilmlerCevap>{

            override fun onResponse(call: Call<FilmlerCevap>?, response: Response<FilmlerCevap>?) {

                if(response != null){
                    val liste = response.body().filmler

                    adapter = FilmlerAdapter(this@FilmlerActivity,liste)

                    filmlerRv.adapter = adapter
                }
            }

            override fun onFailure(call: Call<FilmlerCevap>?, t: Throwable?) { }
        })
    }
}
