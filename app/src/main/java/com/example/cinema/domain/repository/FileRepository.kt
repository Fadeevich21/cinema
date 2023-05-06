package com.example.cinema.domain.repository

import android.net.Uri

interface FileRepository {

    fun getFilename(uri: Uri): String?
}