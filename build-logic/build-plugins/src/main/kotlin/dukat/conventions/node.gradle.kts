package dukat.conventions

import dukat.utils.libs

plugins {
    id("dukat.conventions.base")
    id("com.github.node-gradle.node")
}

node {
    version.set(libs.versions.node)
}
