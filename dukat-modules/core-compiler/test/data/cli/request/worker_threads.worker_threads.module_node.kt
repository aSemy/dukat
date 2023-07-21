@file:JsModule("worker_threads")
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")
package worker_threads

import kotlin.js.*
import org.khronos.webgl.*
import org.w3c.dom.*
import org.w3c.dom.events.*
import org.w3c.dom.parsing.*
import org.w3c.dom.svg.*
import org.w3c.dom.url.*
import org.w3c.fetch.*
import org.w3c.files.*
import org.w3c.notifications.*
import org.w3c.performance.*
import org.w3c.workers.*
import org.w3c.xhr.*

external var isMainThread: Boolean

external var parentPort: MessagePort?

external var threadId: Number

external var workerData: Any

external open class MessageChannel {
    open var port1: MessagePort
    open var port2: MessagePort
}

external open class MessagePort : EventEmitter {
    open fun close()
    open fun postMessage(value: Any, transferList: Array<dynamic /* ArrayBuffer | MessagePort */>? = definedExternally /* null */)
    open fun ref()
    open fun unref()
    open fun start()
    open fun addListener(event: String /* "close" */, listener: () -> Unit): MessagePort /* this */
    open fun addListener(event: String /* "message" */, listener: (value: Any) -> Unit): MessagePort /* this */
    override fun addListener(event: String, listener: (args: Array<Any>) -> Unit): MessagePort /* this */
    override fun addListener(event: Any, listener: (args: Array<Any>) -> Unit): MessagePort /* this */
    open fun emit(event: String /* "close" */): Boolean
    override fun emit(event: String /* "message" */, value: Any): Boolean
    override fun emit(event: String, vararg args: Any): Boolean
    override fun emit(event: Any, vararg args: Any): Boolean
    open fun on(event: String /* "close" */, listener: () -> Unit): MessagePort /* this */
    open fun on(event: String /* "message" */, listener: (value: Any) -> Unit): MessagePort /* this */
    override fun on(event: String, listener: (args: Array<Any>) -> Unit): MessagePort /* this */
    override fun on(event: Any, listener: (args: Array<Any>) -> Unit): MessagePort /* this */
    open fun once(event: String /* "close" */, listener: () -> Unit): MessagePort /* this */
    open fun once(event: String /* "message" */, listener: (value: Any) -> Unit): MessagePort /* this */
    override fun once(event: String, listener: (args: Array<Any>) -> Unit): MessagePort /* this */
    override fun once(event: Any, listener: (args: Array<Any>) -> Unit): MessagePort /* this */
    open fun prependListener(event: String /* "close" */, listener: () -> Unit): MessagePort /* this */
    open fun prependListener(event: String /* "message" */, listener: (value: Any) -> Unit): MessagePort /* this */
    override fun prependListener(event: String, listener: (args: Array<Any>) -> Unit): MessagePort /* this */
    override fun prependListener(event: Any, listener: (args: Array<Any>) -> Unit): MessagePort /* this */
    open fun prependOnceListener(event: String /* "close" */, listener: () -> Unit): MessagePort /* this */
    open fun prependOnceListener(event: String /* "message" */, listener: (value: Any) -> Unit): MessagePort /* this */
    override fun prependOnceListener(event: String, listener: (args: Array<Any>) -> Unit): MessagePort /* this */
    override fun prependOnceListener(event: Any, listener: (args: Array<Any>) -> Unit): MessagePort /* this */
    open fun removeListener(event: String /* "close" */, listener: () -> Unit): MessagePort /* this */
    open fun removeListener(event: String /* "message" */, listener: (value: Any) -> Unit): MessagePort /* this */
    override fun removeListener(event: String, listener: (args: Array<Any>) -> Unit): MessagePort /* this */
    override fun removeListener(event: Any, listener: (args: Array<Any>) -> Unit): MessagePort /* this */
    open fun off(event: String /* "close" */, listener: () -> Unit): MessagePort /* this */
    open fun off(event: String /* "message" */, listener: (value: Any) -> Unit): MessagePort /* this */
    override fun off(event: String, listener: (args: Array<Any>) -> Unit): MessagePort /* this */
    override fun off(event: Any, listener: (args: Array<Any>) -> Unit): MessagePort /* this */
}

external interface WorkerOptions {
    var eval: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var workerData: Any?
        get() = definedExternally
        set(value) = definedExternally
    var stdin: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var stdout: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var stderr: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var execArgv: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
}

external open class Worker(filename: String, options: WorkerOptions? = definedExternally /* null */) : EventEmitter {
    open var stdin: Writable?
    open var stdout: Readable
    open var stderr: Readable
    open var threadId: Number
    open fun postMessage(value: Any, transferList: Array<dynamic /* ArrayBuffer | MessagePort */>? = definedExternally /* null */)
    open fun ref()
    open fun unref()
    open fun terminate(): Promise<Number>
    open fun moveMessagePortToContext(port: MessagePort, context: Context): MessagePort
    open fun receiveMessageOnPort(port: MessagePort): Any?
    open fun addListener(event: String /* "error" */, listener: (err: Error) -> Unit): Worker /* this */
    open fun addListener(event: String /* "exit" */, listener: (exitCode: Number) -> Unit): Worker /* this */
    open fun addListener(event: String /* "message" */, listener: (value: Any) -> Unit): Worker /* this */
    open fun addListener(event: String /* "online" */, listener: () -> Unit): Worker /* this */
    override fun addListener(event: String, listener: (args: Array<Any>) -> Unit): Worker /* this */
    override fun addListener(event: Any, listener: (args: Array<Any>) -> Unit): Worker /* this */
    override fun emit(event: String /* "error" */, err: Error): Boolean
    override fun emit(event: String /* "exit" */, exitCode: Number): Boolean
    override fun emit(event: String /* "message" */, value: Any): Boolean
    open fun emit(event: String /* "online" */): Boolean
    override fun emit(event: String, vararg args: Any): Boolean
    override fun emit(event: Any, vararg args: Any): Boolean
    open fun on(event: String /* "error" */, listener: (err: Error) -> Unit): Worker /* this */
    open fun on(event: String /* "exit" */, listener: (exitCode: Number) -> Unit): Worker /* this */
    open fun on(event: String /* "message" */, listener: (value: Any) -> Unit): Worker /* this */
    open fun on(event: String /* "online" */, listener: () -> Unit): Worker /* this */
    override fun on(event: String, listener: (args: Array<Any>) -> Unit): Worker /* this */
    override fun on(event: Any, listener: (args: Array<Any>) -> Unit): Worker /* this */
    open fun once(event: String /* "error" */, listener: (err: Error) -> Unit): Worker /* this */
    open fun once(event: String /* "exit" */, listener: (exitCode: Number) -> Unit): Worker /* this */
    open fun once(event: String /* "message" */, listener: (value: Any) -> Unit): Worker /* this */
    open fun once(event: String /* "online" */, listener: () -> Unit): Worker /* this */
    override fun once(event: String, listener: (args: Array<Any>) -> Unit): Worker /* this */
    override fun once(event: Any, listener: (args: Array<Any>) -> Unit): Worker /* this */
    open fun prependListener(event: String /* "error" */, listener: (err: Error) -> Unit): Worker /* this */
    open fun prependListener(event: String /* "exit" */, listener: (exitCode: Number) -> Unit): Worker /* this */
    open fun prependListener(event: String /* "message" */, listener: (value: Any) -> Unit): Worker /* this */
    open fun prependListener(event: String /* "online" */, listener: () -> Unit): Worker /* this */
    override fun prependListener(event: String, listener: (args: Array<Any>) -> Unit): Worker /* this */
    override fun prependListener(event: Any, listener: (args: Array<Any>) -> Unit): Worker /* this */
    open fun prependOnceListener(event: String /* "error" */, listener: (err: Error) -> Unit): Worker /* this */
    open fun prependOnceListener(event: String /* "exit" */, listener: (exitCode: Number) -> Unit): Worker /* this */
    open fun prependOnceListener(event: String /* "message" */, listener: (value: Any) -> Unit): Worker /* this */
    open fun prependOnceListener(event: String /* "online" */, listener: () -> Unit): Worker /* this */
    override fun prependOnceListener(event: String, listener: (args: Array<Any>) -> Unit): Worker /* this */
    override fun prependOnceListener(event: Any, listener: (args: Array<Any>) -> Unit): Worker /* this */
    open fun removeListener(event: String /* "error" */, listener: (err: Error) -> Unit): Worker /* this */
    open fun removeListener(event: String /* "exit" */, listener: (exitCode: Number) -> Unit): Worker /* this */
    open fun removeListener(event: String /* "message" */, listener: (value: Any) -> Unit): Worker /* this */
    open fun removeListener(event: String /* "online" */, listener: () -> Unit): Worker /* this */
    override fun removeListener(event: String, listener: (args: Array<Any>) -> Unit): Worker /* this */
    override fun removeListener(event: Any, listener: (args: Array<Any>) -> Unit): Worker /* this */
    open fun off(event: String /* "error" */, listener: (err: Error) -> Unit): Worker /* this */
    open fun off(event: String /* "exit" */, listener: (exitCode: Number) -> Unit): Worker /* this */
    open fun off(event: String /* "message" */, listener: (value: Any) -> Unit): Worker /* this */
    open fun off(event: String /* "online" */, listener: () -> Unit): Worker /* this */
    override fun off(event: String, listener: (args: Array<Any>) -> Unit): Worker /* this */
    override fun off(event: Any, listener: (args: Array<Any>) -> Unit): Worker /* this */
}