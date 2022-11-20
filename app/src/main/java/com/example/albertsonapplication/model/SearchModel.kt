package com.example.albertsonapplication.model

import com.google.gson.annotations.SerializedName

data class SearchModel(
    @SerializedName("sf")
    var sf: String? = null,
    @SerializedName("lfs")
    var lfs: ArrayList<MainLfS> = arrayListOf()
)

/*data class Lfs(
    @SerializedName("lf")
    var lf: String? = null,
    @SerializedName("freq")
    var freq: Int? = null,
    @SerializedName("since")
    var since: Int? = null,
    @SerializedName("vars")
    var vars: ArrayList<Vars> = arrayListOf()
)*/

data class Vars(
    @SerializedName("lf")
    var lf: String? = null,
    @SerializedName("freq")
    var freq: Int? = null,
    @SerializedName("since")
    var since: Int? = null
)