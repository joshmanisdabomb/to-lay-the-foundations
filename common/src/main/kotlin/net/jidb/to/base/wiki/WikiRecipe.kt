package net.jidb.to.base.wiki

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.mojang.serialization.DataResult
import com.mojang.serialization.JsonOps
import net.jidb.to.base.convenience.json.JsonSerialisable
import net.minecraft.world.Container
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeSerializer

class WikiRecipe<T : Container>(val recipe: Recipe<T>, val since: ModVersion? = null, val obselete: ModVersion? = null) : JsonSerialisable {

    override fun toJson() = JsonObject().also {
        val result = (recipe.serializer as RecipeSerializer<Recipe<T>>).codec().encode(recipe, JsonOps.INSTANCE, JsonObject())
        it.add("recipe", result.result().orElseThrow())
    }

}
