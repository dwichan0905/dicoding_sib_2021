package id.dwichan.githubbook.util

import android.content.Context
import id.dwichan.githubbook.R
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringWriter

object FileManagement {
    fun readJSONFile(context: Context): String {
        val stream = context.resources.openRawResource(R.raw.githubuser)
        val writer = StringWriter()
        val buffer = CharArray(1024)
        stream.use { `is` ->
            val reader = BufferedReader(InputStreamReader(`is`, "UTF-8"))
            var n: Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)
            }
        }

        return writer.toString()
    }
}