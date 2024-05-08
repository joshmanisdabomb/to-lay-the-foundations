package net.jidb.to.base.convenience.json

import com.google.gson.JsonElement

interface JsonSerialisable {

    fun toJson(): JsonElement

}