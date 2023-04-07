package net.dankito.utils.favicon.rest

import net.dankito.utils.favicon.rest.model.FaviconDto
import net.dankito.utils.favicon.rest.model.SizeSorting
import net.dankito.utils.favicon.rest.service.FaviconFinderService
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Path("/favicon-finder")
class FaviconFinderResource(
    @Inject
    private val service: FaviconFinderService
) {

    companion object {
        private const val UrlParameterName = "url"
        private const val SortedByParameterName = "sortedBy"
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun findFavicons(
        @QueryParam(UrlParameterName) url: String,
        @QueryParam(SortedByParameterName) @DefaultValue("Descending") sortedBy: SizeSorting
    ): Response {
        val faviconsSorted = service.findFavicons(url, sortedBy)

        val mapped = faviconsSorted.map { FaviconDto(it) }

        return Response.ok(mapped)
                .header("Access-Control-Allow-Origin", "*")
                .build()
    }

}