package org.jetbrains.dukat.model.serialization

import org.jetbrains.dukat.translator.ModuleTranslationUnit
import org.jetbrains.dukat.translatorString.translateSourceSet
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class ModelsProtoTest {
    private val kotlinStdlibJsJarPath = System.getenv()["kotlinStdlibJsJarPath"]
        ?: error("missing kotlinStdlibJsJarPath")

    @Test
    fun test() {
        val binary = serializeStdLib(kotlinStdlibJsJarPath)
        val sourceSetConverted = convertProtobufToModels(binary)

        val units = translateSourceSet(sourceSetConverted)

        val contents = units
            .filterIsInstance(ModuleTranslationUnit::class.java)
            .flatMap { listOf("// --------- ${it.packageName} ---------", it.content) }
            .joinToString(System.lineSeparator())
            .trimEnd()

        File("build/test/actual.out.kt").apply {
            parentFile.mkdirs()
            writeText(contents)
        }

        assertEquals(
            contents,
            File("test/resources/code.out.kt").readText().trimEnd(),
            "De-serialized stdlib does not look like as expected"
        )
    }
}
