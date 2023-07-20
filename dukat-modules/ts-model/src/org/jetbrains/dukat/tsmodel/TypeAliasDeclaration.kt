package org.jetbrains.dukat.tsmodel

import org.jetbrains.dukat.astCommon.NameEntity
import org.jetbrains.dukat.tsmodel.types.ParameterValueDeclaration

data class TypeAliasDeclaration(
        val aliasName: NameEntity,
        val typeParameters: List<TypeParameterDeclaration>,
        val typeReference: ParameterValueDeclaration,

        override val uid: String
) : TopLevelDeclaration, WithUidDeclaration, ParameterOwnerDeclaration