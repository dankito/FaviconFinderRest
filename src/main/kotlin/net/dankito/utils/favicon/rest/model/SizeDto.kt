package net.dankito.utils.favicon.rest.model

import io.quarkus.runtime.annotations.RegisterForReflection
import net.dankito.utils.favicon.Size


@RegisterForReflection // needed in native applications
open class SizeDto(
    open val width: Int,
    open val height: Int
) {

    constructor() : this(-1, -1)

    constructor(size: Size) : this(size.width, size.height)


    val isSquare: Boolean = width == height

    val displayText: String = "$width x $height"

}