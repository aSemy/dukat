package org.jetbrains.dukat.tsLowerings

import org.jetbrains.dukat.astCommon.NameEntity
import org.jetbrains.dukat.tsmodel.types.ParameterValueDeclaration
import org.jetbrains.dukat.tsmodel.types.TypeParamReferenceDeclaration

typealias TypeMapping = Map<NameEntity, ParameterValueDeclaration>

class ApplyTypeParameters(private val typesMapping: TypeMapping) : DeclarationLowering {
    override fun lowerTypeParamReferenceDeclaration(declaration: TypeParamReferenceDeclaration): ParameterValueDeclaration =
        typesMapping[declaration.value] ?: super.lowerTypeParamReferenceDeclaration(declaration)
}