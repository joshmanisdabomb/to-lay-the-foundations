package net.jidb.to.base.data.provider

import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import net.jidb.to.base.convenience.json.JsonSerialisable
import net.minecraft.data.CachedOutput
import net.minecraft.data.DataProvider
import net.minecraft.data.PackOutput
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path
import java.util.concurrent.CompletableFuture
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream
import kotlin.io.path.inputStream

open class HttpPostProvider(val output: PackOutput) : DataProvider {

    private var url: URL? = null
    private var content = mutableListOf<Content>()

    private var modify: (HttpURLConnection) -> Unit = {}

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

    open fun addContent(name: String, type: String, filename: String? = null, body: () -> ByteArray): HttpPostProvider {
        content.add(Content(name, type, body, filename))
        return this
    }
    fun addContent(name: String, type: String, body: ByteArray, filename: String? = null) = addContent(name, type, filename) { body }
    fun addContent(name: String, type: String, body: String, filename: String? = null) = addContent(name, type, filename) { body.toByteArray() }
    fun addJsonContent(name: String, json: JsonElement, filename: String? = null) = addContent(name, "application/json", GSON.toJson(json), filename)
    fun addJsonContent(name: String, `object`: JsonSerialisable, filename: String? = null) = addJsonContent(name, `object`.toJson(), filename)
    open fun addZipContent(name: String, filename: String? = null, paths: (Path) -> List<Pair<Path, String?>>): HttpPostProvider {
        return addContent(name, "application/zip", filename) {
            val paths = paths(output.outputFolder)
            val receiver = ByteArrayOutputStream()
            ZipOutputStream(BufferedOutputStream(receiver)).use { zip ->
                for (path in paths) {
                    Files.walk(path.first).use { tree ->
                        tree.filter(Files::isRegularFile).forEach { src ->
                            BufferedInputStream(src.inputStream()).use {
                                val name = path.first.relativize(src).toString().replace("\\", "/")
                                val entry = ZipEntry((path.second?.plus("/") ?: "") + name)
                                zip.putNextEntry(entry)
                                it.copyTo(zip)
                                zip.closeEntry()
                            }
                        }
                    }
                }
            }
            receiver.toByteArray()
        }
    }
    fun addZipContent(name: String, filename: String? = null, paths: List<Pair<Path, String?>>) = addZipContent(name, filename) { paths }

    override fun run(cachedOutput: CachedOutput): CompletableFuture<*> {
        return CompletableFuture.runAsync {
            val url = this.getFinalURL()
            val connection = (url.openConnection() as HttpURLConnection).apply {
                requestMethod = "POST"
                doOutput = true
                setRequestProperty("Accept", "application/json")
                setRequestProperty("Content-Type", "multipart/form-data;boundary=$BOUNDARY")
                modify(this)
            }

            try {
                val stream = connection.outputStream

                content.forEach { it.add(stream) }

                stream.write("--$BOUNDARY--\r\n".toByteArray())
                stream.close()

                connection.inputStream.bufferedReader().use {
                    it.lines().forEach(::println)
                }

                connection.disconnect()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            connection.errorStream?.bufferedReader()?.use {
                it.lines().forEach(System.err::println)
            }
            connection.disconnect()
        }
    }

    protected open fun getFinalURL(): URL {
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
        val BOUNDARY = "*****"
    }

    private data class Content(val name: String, val type: String, val content: () -> ByteArray, val filename: String? = null) {

        fun add(stream: OutputStream) {
            val writer = object : PrintWriter(stream) {
                override fun println() {
                    write("\r\n")
                }
            }
            writer.println("--$BOUNDARY")
            writer.print("Content-Disposition: form-data; name=\"$name\"")
            if (filename != null) {
                writer.print("; filename=\"$filename\"")
            }
            writer.println()
            writer.println("Content-Type: $type")
            writer.println()
            writer.flush()
            stream.write(content())
            stream.flush()
            writer.println()
            writer.flush()
        }

    }

}