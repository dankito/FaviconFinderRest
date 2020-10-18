package net.dankito.utils.favicon.rest

import net.dankito.utils.favicon.FaviconFinder
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Path("/favicons")
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

//        val mapped = faviconsSorted.map { FaviconDto(it.url, it.iconType.name, it.size.toString(), it.type) }

        return Response.ok(faviconsSorted)
                .header("Access-Control-Allow-Origin", "*")
                .build()
    }

    protected open fun makeUrlAbsolute(url: String): String {
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