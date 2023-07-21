package org.jetbrains.dukat.compiler.tests.extended

import org.jetbrains.dukat.compiler.tests.core.TestConfig
import org.jetbrains.dukat.descriptors.writeDescriptorsToFile
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.condition.EnabledIfSystemProperty
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.io.File

class DescriptorCompilationTests : CompilationTests() {

    @DisplayName("descriptor compilation set")
    @ParameterizedTest(name = "{0}")
    @MethodSource("descriptorCompilationSet")
    @EnabledIfSystemProperty(named = "dukat.test.descriptorCompilation", matches = "true")
    override fun runTests(descriptor: String, sourcePath: String, tsConfig: String) {
        println("file:///${sourcePath}")
        val targetPath = "./build/tests/descriptors/$START_TIMESTAMP/$descriptor"
        val targetDir = File(targetPath)
        println("file:///${targetDir.normalize().absolutePath}")

        targetDir.deleteRecursively()

        val sourceSet = getTranslator().translate(sourcePath)
        writeDescriptorsToFile(sourceSet, TestConfig.STDLIB_JAR, targetDir.absolutePath)
    }

    companion object {
        @JvmStatic
        fun descriptorCompilationSet(): Array<Array<String>> = DefinitelyTypedCompilationTests.extendedSet()
    }

}