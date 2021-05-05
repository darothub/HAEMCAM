package ng.com.thewhitecellfoundation.haemcam.ui.adapter

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import ng.com.thewhitecellfoundation.haemcam.model.DataPair
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class CustomTypeAdapter : TypeAdapter<DataPair>() {
    @Throws(IOException::class)
    override fun write(out: JsonWriter?, value: DataPair) {
        out?.beginObject()
        out?.name("first")?.value(value.first)
        out?.name("second")?.value(value.second)
        out?.endObject()
    }

    override fun read(`in`: JsonReader?): DataPair? {
        return null
    }
}

object CustomRepository {

    private val gson: Gson
        get() {
            val builder = GsonBuilder()
            builder.registerTypeAdapter(DataPair::class.java, CustomTypeAdapter())
            return builder.create()
        }

    fun saveData(dataPair: DataPair) {
        val str = gson.toJson(dataPair)
        try {
            val dataStream = dataOutputStream(dataPair)
            dataStream.write(str.toByteArray())
            dataStream.close()
        } catch (e: IOException) {
        }
    }
    private val appContext = Application().applicationContext
    private val dataDirectory = appContext.getDir("data", Context.MODE_PRIVATE)
    private fun dataFile(filename: String) = File(dataDirectory, filename)
    private fun dataFileName(dataPair: DataPair) = "${dataPair.first}.datapair"
    private fun dataOutputStream(dataPair: DataPair) = FileOutputStream(dataFile(dataFileName(dataPair)))
}
