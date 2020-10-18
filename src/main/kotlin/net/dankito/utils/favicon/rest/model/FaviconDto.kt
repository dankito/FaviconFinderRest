package net.dankito.utils.favicon.rest.model

import io.quarkus.runtime.annotations.RegisterForReflection
import net.dankito.utils.favicon.Favicon
import net.dankito.utils.favicon.FaviconType


@RegisterForReflection // needed in native applications
open class FaviconDto(
    open val url: String,
    open val iconType: FaviconType,
    open val size: SizeDto? = null,
    open val type: String? = null
)  {

    constructor() : this("", FaviconType.Icon)

    constructor(favicon: Favicon) : this(favicon.url, favicon.iconType, favicon.size?.let { SizeDto(it) }, favicon.type)

}