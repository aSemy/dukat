package org.jetbrains.dukat.astModel

import org.jetbrains.dukat.astCommon.CommentEntity
import org.jetbrains.dukat.astCommon.Entity

interface KotlinModel : Entity {
    val comment: CommentEntity?
}