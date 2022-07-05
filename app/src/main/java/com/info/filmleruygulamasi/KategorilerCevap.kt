package com.info.filmleruygulamasi

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class KategorilerCevap(@SerializedName("kategoriler")
                            @Expose
                            var kategoriler:List<Kategoriler>
                            ,
                            @SerializedName("success")
                            @Expose
                            var success:Int) {
}