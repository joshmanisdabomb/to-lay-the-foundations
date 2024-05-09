package net.jidb.to.base.wiki.recipe

import com.google.gson.JsonObject
import com.mojang.serialization.JsonOps
import net.jidb.to.base.convenience.json.JsonSerialisable
import net.jidb.to.base.extension.addOrNull
import net.minecraft.world.Container
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeSerializer

class WikiRecipe<T : Container>(val recipe: Recipe<T>, val since: String? = null, val obsolete: String? = null) : JsonSerialisable {

    override fun toJson() = JsonObject().also {
        val result = (recipe.serializer as RecipeSerializer<Recipe<T>>).codec().encode(recipe, JsonOps.INSTANCE, JsonObject())
        it.add("recipe", result.result().orElseThrow())
        it.addOrNull("since", since)
        it.addOrNull("obsolete", obsolete)
    }

}
