package com.ndgnndg91.was

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket


fun main() {
    val serverSocket = ServerSocket(8080)
    println("Server is listening on port 8080...")

    while (true) {
        try {
            serverSocket.accept().use { clientSocket ->
                BufferedReader(InputStreamReader(clientSocket.getInputStream())).use { `in` ->
                    PrintWriter(clientSocket.getOutputStream(), true).use { out ->

                        // 클라이언트 요청을 읽음
                        val requestLine = `in`.readLine()
                        println("Request: $requestLine")

                        // 간단한 HTTP 1.0 응답 작성
                        val response = """
                HTTP/1.0 200 OK
                Content-Type: text/html
                
                <html><body>Hello, World!</body></html>
                """.trimIndent()

                        // 응답을 클라이언트로 전송
                        out.println(response)
                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}