package com.example.cinema.domain.usecase.file

import android.net.Uri
import com.example.cinema.domain.repository.FileRepository

class GetFilenameUseCase(private val repository: FileRepository) {

    fun execute(uri: Uri): String? {
        return repository.getFilename(uri)
    }
}