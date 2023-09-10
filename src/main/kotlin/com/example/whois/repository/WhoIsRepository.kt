package com.example.whois.repository

import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import org.springframework.stereotype.Component

@Component
class WhoIsRepository {

    fun getWhoIs(domainName: String): String {
        val outputStringBuilder = StringBuilder()

        // Check the operating system
        val isWindows = isWindows()

        try {
            // Define the command to run
            val command = if (isWindows) {
                // Load the Windows executable from resources (if on Windows)
                val executablePath = loadExecutable()
                if (executablePath != null) {
                    // Use the executable if found
                    "$executablePath $domainName"
                } else {
                    // Use the standard "whois" command if executable not found
                    "whois $domainName"
                }
            } else {
                // Use the standard "whois" command on non-Windows systems
                "whois $domainName"
            }

            // Execute the command
            val process = Runtime.getRuntime().exec(command)

            // Read the output and append it to the outputStringBuilder
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                outputStringBuilder.append(line).append("\n")
            }

            // Wait for the process to complete
            process.waitFor()

            // Close the input stream
            reader.close()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        return outputStringBuilder.toString()
    }

    private fun isWindows(): Boolean {
        val osName = System.getProperty("os.name").toLowerCase()
        return osName.contains("win")
    }

    private fun loadExecutable(): String? {
        if (isWindows()) {
            val classLoader = Thread.currentThread().contextClassLoader
            val resourceStream = classLoader.getResourceAsStream("whois64.exe")

            if (resourceStream != null) {
                val executableContents = resourceStream.readBytes()
                val tempFile = File.createTempFile("whois64", ".exe")
                tempFile.deleteOnExit()
                tempFile.writeBytes(executableContents)
                return tempFile.absolutePath
            }
        }
        return null
    }
}