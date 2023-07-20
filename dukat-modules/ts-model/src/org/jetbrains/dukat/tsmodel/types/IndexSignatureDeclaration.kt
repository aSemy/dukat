package org.jetbrains.dukat.tsmodel.types

import org.jetbrains.dukat.tsmodel.CallableMemberDeclaration
import org.jetbrains.dukat.tsmodel.ParameterDeclaration
import org.jetbrains.dukat.tsmodel.ParameterOwnerDeclaration


data class IndexSignatureDeclaration(
        override val parameters: List<ParameterDeclaration>,
        val returnType: ParameterValueDeclaration
) : CallableMemberDeclaration, ParameterOwnerDeclaration