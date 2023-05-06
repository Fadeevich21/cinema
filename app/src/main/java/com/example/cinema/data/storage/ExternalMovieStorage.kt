package com.example.cinema.data.storage

import android.content.Context
import android.net.Uri
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import java.lang.Exception

class ExternalMovieStorage(
    private val context: Context
) : MovieStorage {

    override fun saveMovieJson(uri: Uri?, jsonNode: JsonNode) {
        val json = jsonNode.toString()
        try {
            val outputStream = context.contentResolver.openOutputStream(uri!!)
            outputStream?.write(json.toByteArray())
            outputStream?.close()
        } catch (e: Exception) {
            print(e.localizedMessage)
        }
    }


    override fun saveMovieJsonToCsv(uri: Uri?, jsonNode: JsonNode) {
        val builder = CsvSchema.builder()
        jsonNode.fieldNames().forEachRemaining { fieldName ->
            builder.addColumn(fieldName)
        }
        val schema = builder.build().withHeader()
        val csvMapper = CsvMapper()
        try {
            val outputStream = context.contentResolver.openOutputStream(uri!!)
            csvMapper.writerFor(JsonNode::class.java).with(schema).writeValue(
                outputStream,
                jsonNode
            )
            outputStream?.close()
        } catch (e: Exception) {
            print(e.localizedMessage)
        }
    }
}
