package org.jetbrains.dukat.tsmodel

import org.jetbrains.dukat.tsmodel.types.ParameterValueDeclaration

data class MethodSignatureDeclaration(
        override val name: String,
        override val parameters: List<ParameterDeclaration>,
        override val type: ParameterValueDeclaration,
        override val typeParameters: List<TypeParameterDeclaration>,
        override val optional: Boolean,
        override val modifiers: Set<ModifierDeclaration>
) : NamedMethodLikeDeclaration, CallableMemberDeclaration