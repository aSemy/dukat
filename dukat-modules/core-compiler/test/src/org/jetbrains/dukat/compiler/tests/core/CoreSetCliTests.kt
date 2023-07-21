package org.jetbrains.dukat.compiler.tests.core

import kotlinx.serialization.Serializable
import org.jetbrains.dukat.compiler.tests.CliTranslator
import org.jetbrains.dukat.compiler.tests.MethodSourceSourceFiles
import org.jetbrains.dukat.compiler.tests.OutputTests
import org.jetbrains.dukat.compiler.tests.extended.CliTestsEnded
import org.jetbrains.dukat.compiler.tests.extended.CliTestsStarted
import org.jetbrains.dukat.compiler.tests.toFileUriScheme
import org.jetbrains.dukat.panic.resolvePanicMode
import org.jetbrains.dukat.translatorString.D_TS_DECLARATION_EXTENSION
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.io.File


@Serializable
data class ReportJson(val outputs: List<String>)

class ResolvePanicMode : BeforeAllCallback {
    override fun beforeAll(context: ExtensionContext?) {
        resolvePanicMode()
    }
}

@ExtendWith(ResolvePanicMode::class, CliTestsStarted::class, CliTestsEnded::class)
open class CoreSetCliTests {
    @DisplayName("core test set [cli run]")
    @ParameterizedTest(name = "{0}")
    @MethodSource("coreSet")
    open fun withValueSource(name: String, tsPath: String, ktPath: String, tsConfig: String) {
        assertContentEqualsBinary(name, tsPath, ktPath, if (tsConfig.isEmpty()) null else tsConfig)
    }

    open fun getTranslator(): CliTranslator = CliTranslator()

    companion object {
        @JvmStatic
        fun coreSet(): Array<Array<String>> {
            return MethodSourceSourceFiles("./test/data/typescript/node_modules", D_TS_DECLARATION_EXTENSION).fileSetWithDescriptors()
        }
    }

    protected fun assertContentEqualsBinary(
            descriptor: String,
            tsPath: String,
            ktPath: String,
            tsConfig: String?
    ) {
        println("\nSOURCE:\t${tsPath.toFileUriScheme()}\nTARGET:\t${ktPath.toFileUriScheme()}")

        val dirName = "./build/tests/core/cli/${descriptor}"
        val reportOutput = getTranslator().convert(tsPath, tsConfig, dirName)

        val translatedOutput = reportOutput.mapNotNull { output ->
            println("OUTPUT ${output}")
            val targetFile = File(dirName, output)

            if (output.startsWith("lib.")) {
                null
            } else {
                targetFile
            }
        }

        val translated = translatedOutput.joinToString(OutputTests.SEPARATOR) { file ->
            "// [test] ${file.name}" + System.getProperty("line.separator") + file.readText()
        }

        assertNormalizedContentEquals(
                if (translated.isEmpty()) {
                    "// NO DECLARATIONS"
                } else {
                    translated
                },
                File(ktPath).readText().trimEnd()
        )
    }

}

