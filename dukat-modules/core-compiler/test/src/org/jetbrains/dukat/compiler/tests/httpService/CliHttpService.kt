package org.jetbrains.dukat.compiler.tests.httpService

import org.jetbrains.dukat.compiler.tests.NodeResolver
import org.jetbrains.dukat.compiler.tests.core.TestConfig

class CliHttpService(
        private val serverPath: String = "./test/src/org/jetbrains/dukat/compiler/tests/server.js",
        private val nodeResolver: NodeResolver = NodeResolver("../node-package/build/env.json"),
        private val emitDiagnostics: Boolean = false,
        private val inspectNodeProcess: Boolean = false
) {

    private val maxOldSpaceSize = 8192

    fun start(
            port: String,
            vararg sandboxDirs: String
    ): Process {
        val args = listOf(
                nodeResolver.nodePath,
                if (inspectNodeProcess) "--inspect" else null,
                "--max-old-space-size=${maxOldSpaceSize}",
                serverPath,
                port,
                emitDiagnostics.toString(),
                *sandboxDirs
        ).filterNotNull().toTypedArray()
        return ProcessBuilder().inheritIO().command(*args).start()
    }

    fun start() = start(
            TestConfig.CLI_TEST_SERVER_PORT,
            *listOf(TestConfig.DEFINITELY_TYPED_DIR).filterNotNull().toTypedArray()
    )
}

fun main() {
    CliHttpService(
            serverPath = "./compiler/test/src/org/jetbrains/dukat/compiler/tests/server.js",
            nodeResolver = NodeResolver("./node-package/build/env.json"),
            inspectNodeProcess = true
    ).start()
}