package io.github.lg5.jvm.checkstyle.code

import java.io.File
import java.nio.file.Files

class FileUtil {

    companion object {

        fun copyContentToTempFile(resourceFileName: String?, tempFileName: String?): File {
            val temp = File.createTempFile(tempFileName, ".tmp")
            Files.newOutputStream(temp.toPath()).use { out ->
                FileUtil::class.java.classLoader
                    .getResourceAsStream(resourceFileName).use { resourceStream ->
                        val buffer = ByteArray(1024)
                        var bytes = resourceStream.read(buffer)
                        while (bytes >= 0) {
                            out.write(buffer, 0, bytes)
                            bytes = resourceStream.read(buffer)
                        }
                    }
            }
            return temp
        }
    }
}