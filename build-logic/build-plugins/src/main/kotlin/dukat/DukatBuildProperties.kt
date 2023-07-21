package dukat

import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderFactory
import org.gradle.internal.os.OperatingSystem
import javax.inject.Inject


abstract class DukatBuildProperties @Inject constructor(
    providers: ProviderFactory,
) {
    val currentOS: Provider<OperatingSystem> = providers.provider {
        OperatingSystem.current()
    }
}
