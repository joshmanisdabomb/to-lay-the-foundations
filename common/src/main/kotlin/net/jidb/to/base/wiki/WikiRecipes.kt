package net.jidb.to.base.wiki

import com.google.gson.JsonObject
import net.jidb.to.base.convenience.json.JsonSerialisable
import net.jidb.to.base.extension.addSerialisableArray

class WikiRecipes(val result: List<WikiRecipe<*>> = emptyList(), val ingredient: List<WikiRecipe<*>> = emptyList(), val crafter: List<WikiRecipe<*>> = emptyList()) : JsonSerialisable {

    override fun toJson() = JsonObject().also {
        it.addSerialisableArray("result", result.toTypedArray())
        it.addSerialisableArray("ingredient", ingredient.toTypedArray())
        it.addSerialisableArray("crafter", crafter.toTypedArray())
    }

}
