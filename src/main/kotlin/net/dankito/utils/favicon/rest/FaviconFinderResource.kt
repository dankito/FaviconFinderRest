package net.dankito.utils.favicon.rest

import net.dankito.utils.favicon.rest.model.FaviconDto
import net.dankito.utils.favicon.rest.model.SizeSorting
import net.dankito.utils.favicon.rest.service.FaviconFinderService
import net.dankito.utils.favicon.rest.service.HtmlService
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Path("/favicon-finder")
class FaviconFinderResource {

    companion object {
        private const val UrlParameterName = "url"
        private const val SortedByParameterName = "sortedBy"
    }

    @Inject internal lateinit var service: FaviconFinderService

    @Inject internal lateinit var htmlService: HtmlService


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

    @GET
    @Produces(MediaType.TEXT_HTML)
    fun findFaviconsHtml(
        @QueryParam(UrlParameterName) url: String,
        @QueryParam(SortedByParameterName) @DefaultValue("Descending") sortedBy: SizeSorting
    ): String {
        val faviconsSorted = service.findFavicons(url, sortedBy)

        return htmlService.createHtmlPage(url, faviconsSorted)
    }

}