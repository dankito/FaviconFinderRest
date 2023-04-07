package net.dankito.utils.favicon.rest

import net.dankito.utils.favicon.FaviconFinder
import net.dankito.utils.favicon.rest.model.FaviconDto
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Path("/favicons-finder")
class FaviconFinderResource {

    companion object {

        const val SortedBySizeAscending="size_ascending"

        const val SortedBySizeDescending="size_descending"

    }

    protected val faviconFinder = FaviconFinder()


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun findFavicons(
            @QueryParam("url") url: String,
            @QueryParam("sortedBy") @DefaultValue(SortedBySizeDescending) sortedBy: String
    ): Response {
        val absoluteUrl = makeUrlAbsolute(url)

        val favicons = faviconFinder.extractFavicons(absoluteUrl)

        val faviconsSorted = if (sortedBy == SortedBySizeDescending) {
            favicons.sortedByDescending { it.size }
        }
        else {
            favicons.sortedBy { it.size }
        }

        val mapped = faviconsSorted.map { FaviconDto(it) }

        return Response.ok(mapped)
                .header("Access-Control-Allow-Origin", "*")
                .build()
    }

    protected fun makeUrlAbsolute(url: String): String {
        if (url.startsWith("http")) {
            return url
        }

        var absoluteUrl = url

        if (absoluteUrl.startsWith("www.", true) == false) {
            absoluteUrl = "www." + absoluteUrl
        }

        return "https://" + absoluteUrl
    }

}