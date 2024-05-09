package net.jidb.to.base.data.provider

import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import dev.architectury.platform.Platform
import net.minecraft.data.CachedOutput
import net.minecraft.data.DataProvider
import net.minecraft.data.PackOutput
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.file.Path
import java.util.concurrent.CompletableFuture
import kotlin.io.path.name

open class HttpPostProvider(val output: PackOutput) : DataProvider {

    private var url: URL? = null
    private var paths: (Path) -> List<Path> = { listOf() }
    private var modify: (HttpURLConnection) -> Unit = {}
    private var body: String? = null

    fun setURL(url: URL): HttpPostProvider {
        this.url = url
        return this
    }
    fun promptURL(): HttpPostProvider {
        url = null
        return this
    }

    fun modifyConnection(modifier: (HttpURLConnection) -> Unit): HttpPostProvider {
        modify = modifier
        return this
    }

    fun setBody(body: String?): HttpPostProvider {
        this.body = body
        return this
    }
    fun setBody(json: JsonElement) = setBody(GSON.toJson(json))

    fun setPathBuilder(builder: (Path) -> List<Path>): HttpPostProvider {
        paths = builder
        return this
    }
    fun setPathBuilder(paths: List<Path>) = setPathBuilder { paths }

    override fun run(cachedOutput: CachedOutput): CompletableFuture<*> {
        return CompletableFuture.runAsync {
            val url = this.getFinalURL()
            val connection = (url.openConnection() as HttpURLConnection).apply {
                requestMethod = "POST"
                modify(this)
            }

            val body = body
            if (body != null) {
                connection.doOutput = true
                connection.outputStream.bufferedWriter().use {
                    it.write(body)
                    it.flush()
                }
            }

            val lines = connection.inputStream.bufferedReader().readLines()
            println(lines.joinToString(separator = "\n"))
            connection.inputStream.close()

            connection.disconnect()
        }
    }

    private fun getFinalURL(): URL {
        val url = url
        return if (url != null) {
            url
        } else {
            var input = ""
            while (input.isEmpty()) {
                print("Enter URL to POST data: ")
                input = readln()
            }
            URL(input)
        }
    }

    override fun getName() = "Post to HTTP"

    companion object {
        private val GSON = GsonBuilder().setLenient().create()
    }

}