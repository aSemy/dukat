package org.jetbrains.dukat.translator

import org.jetbrains.dukat.astModel.SourceSetModel

interface InputTranslator<T> {
    fun translate(data: T): SourceSetModel
}