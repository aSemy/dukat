package org.jetbrains.dukat.compiler.tests.core

import org.jetbrains.dukat.astModel.SourceSetModel
import org.jetbrains.dukat.compiler.tests.CliTranslator
import org.jetbrains.dukat.compiler.tests.MethodSourceSourceFiles
import org.jetbrains.dukat.compiler.tests.OutputTests
import org.jetbrains.dukat.compiler.tests.extended.CliTestsEnded
import org.jetbrains.dukat.compiler.tests.extended.CliTestsStarted
import org.jetbrains.dukat.js.translator.JavaScriptLowerer
import org.jetbrains.dukat.moduleNameResolver.ConstNameResolver
import org.jetbrains.dukat.panic.PanicMode
import org.jetbrains.dukat.panic.setPanicMode
import org.jetbrains.dukat.translator.InputTranslator
import org.jetbrains.dukat.translatorString.JS_DECLARATION_EXTENSION
import org.jetbrains.dukat.translatorString.translateSourceSet
import org.jetbrains.dukat.ts.translator.JsRuntimeByteArrayTranslator
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.io.File

@ExtendWith(CliTestsStarted::class, CliTestsEnded::class)
class JSTypeTests : OutputTests() {
    @DisplayName("js type test set")
    @ParameterizedTest(name = "{0}")
    @MethodSource("jsSet")
    fun withValueSource(name: String, jsPath: String, ktPath: String) {
        assertContentEqualsBinary(name, jsPath, ktPath)
    }

    override fun getTranslator(): InputTranslator<String> = object : CliTranslator() {
        override fun translate(data: String): SourceSetModel {
            val binData = translateBinary(data, null)
            val translator = JsRuntimeByteArrayTranslator(JavaScriptLowerer(ConstNameResolver()))
            return translator.translate(binData)
        }
    }

    companion object {
        @JvmStatic
        fun jsSet(): Array<Array<String>> {
            return MethodSourceSourceFiles("./test/data/javascript", JS_DECLARATION_EXTENSION).fileSetWithDescriptors()
        }

        @JvmStatic
        @BeforeAll
        fun setup() {
            setPanicMode(PanicMode.NEVER_FAIL)
        }
    }


    private fun assertContentEqualsBinary(
            descriptor: String,
            tsPath: String,
            ktPath: String
    ) {
        setPanicMode(PanicMode.NEVER_FAIL)

        val targetShortName = "${descriptor}.d.kt"

        val modules = translateSourceSet(getTranslator().translate(tsPath))
        val translated = concatenate(tsPath, modules)

        assertNormalizedContentEquals(
                translated,
                File(ktPath).readText().trimEnd(),
                "\nSOURCE:\tfile:///${tsPath}\nTARGET:\tfile:///${ktPath}"
        )

        val outputDirectory = File("./build/tests/out")
        translated.let {
            val outputFile = outputDirectory.resolve(targetShortName)
            outputFile.parentFile.mkdirs()
            outputFile.writeText(translated)
        }
    }

}

