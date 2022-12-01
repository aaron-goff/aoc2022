package app

import java.io.File

fun readFile(fileName: String): List<String> = File("src/src/resources/$fileName.txt").useLines { it.toList() }

fun print(thing: Any) = kotlin.io.println(thing)