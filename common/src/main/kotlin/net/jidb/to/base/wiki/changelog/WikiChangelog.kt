package net.jidb.to.base.wiki.changelog

import com.google.gson.JsonObject
import net.jidb.to.base.convenience.json.JsonSerialisable
import net.jidb.to.base.extension.addSerialisable
import net.jidb.to.base.wiki.WikiArticle

class WikiChangelog(val map: Map<WikiArticle, WikiChangelogEntry>) : JsonSerialisable {

    override fun toJson() = JsonObject().also {
        it.addSerialisable(map.mapKeys { it.key.identifier.toString() })
    }

    fun getScoped(article: WikiArticle) = map[article]

}