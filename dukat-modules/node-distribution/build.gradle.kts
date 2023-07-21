plugins {
    id("dukat.conventions.node")
    id("dukat.conventions.node-distribution-files")
}

node {
    download.set(true)
}

configurations.nodeDistributionElements {
    outgoing {
        artifact(tasks.nodeSetup.map { it.nodeDir })
    }
}
