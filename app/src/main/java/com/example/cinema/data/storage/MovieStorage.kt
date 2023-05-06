package com.example.cinema.data.storage

import android.net.Uri
import com.fasterxml.jackson.databind.JsonNode

interface MovieStorage {

    fun saveMovieJson(uri: Uri?, jsonNode: JsonNode)
    fun saveMovieJsonToCsv(uri: Uri?, jsonNode: JsonNode)
}