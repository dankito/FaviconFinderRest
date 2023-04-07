package net.dankito.utils.favicon.rest

import net.dankito.utils.favicon.FaviconFinder
import net.dankito.utils.favicon.rest.model.FaviconDto
import net.dankito.utils.favicon.rest.model.SizeSorting
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Path("/favicon-finder")
class FaviconFinderResource {

    companion object {
        private const val UrlParameterName = "url"
        private const val SortedByParameterName = "sortedBy"
    }

    protected val faviconFinder = FaviconFinder()


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun findFavicons(
        @QueryParam(UrlParameterName) url: String,
        @QueryParam(SortedByParameterName) @DefaultValue("Descending") sortedBy: SizeSorting
    ): Response {
        val absoluteUrl = makeUrlAbsolute(url)

        val favicons = faviconFinder.extractFavicons(absoluteUrl)

        val faviconsSorted = if (sortedBy == SizeSorting.Descending) {
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