package com.example.cinema.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import com.example.cinema.domain.repository.FileRepository

class FileRepositoryImpl(private val context: Context) : FileRepository {

    @SuppressLint("Range", "Recycle")
    override fun getFilename(uri: Uri): String? {
        var filename: String? = null
        if ((uri.scheme == "content")) {
            val cursor: Cursor? = context.contentResolver.query(uri, null, null, null, null)
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    filename = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                }
            } finally {
                cursor!!.close()
            }
        }

        if (filename == null) {
            filename = uri.path
            val cut: Int = filename!!.lastIndexOf('/')
            if (cut != -1) {
                filename = filename.substring(cut + 1)
            }
        }

        return filename
    }
}