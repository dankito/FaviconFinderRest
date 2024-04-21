package net.dankito.utils.favicon.rest

import io.quarkus.runtime.Startup
import io.quarkus.runtime.annotations.RegisterForReflection
import net.dankito.utils.favicon.webmanifest.WebManifest
import net.dankito.utils.favicon.webmanifest.WebManifestIcon

@Startup
@RegisterForReflection(
    targets = [
        // Quarkus won't find them in native mode otherwise
        WebManifest::class, WebManifestIcon::class
    ]
)
class QuarkusConfig {
}