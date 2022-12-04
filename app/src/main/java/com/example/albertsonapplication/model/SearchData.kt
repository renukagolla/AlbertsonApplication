package com.sample.project.models

class SerachData(elements: Collection<SearchDatum>) : ArrayList<SearchDatum>(elements) {
    /*public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = SerachData(klaxon.parseArray<SearchDatum>(json)!!)
    }*/
}