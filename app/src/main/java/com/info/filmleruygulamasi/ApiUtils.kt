package com.info.retrofitkullanimi

import com.info.filmleruygulamasi.FilmlerDaoInterface
import com.info.filmleruygulamasi.KategorilerDaoInterface

class ApiUtils {

    companion object{

        val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getKategorilerDaoInterface(): KategorilerDaoInterface {
            return RetrofitClient.getClient(BASE_URL).create(KategorilerDaoInterface::class.java)
        }

        fun getFilmlerDaoInterface(): FilmlerDaoInterface {
            return RetrofitClient.getClient(BASE_URL).create(FilmlerDaoInterface::class.java)
        }
    }
}



