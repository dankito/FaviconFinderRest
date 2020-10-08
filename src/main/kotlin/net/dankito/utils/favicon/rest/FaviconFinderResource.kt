package net.dankito.utils.favicon.rest

import net.dankito.utils.favicon.Favicon
import net.dankito.utils.favicon.FaviconFinder
import net.dankito.utils.web.client.OkHttpWebClient
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Path("/favicons")
open class FaviconFinderResource {

    protected val faviconFinder = FaviconFinder(OkHttpWebClient())


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun findFavicons(
            @QueryParam("url") url: String
    ): Response {
        val absoluteUrl = makeUrlAbsolute(url)

        val favicons = faviconFinder.extractFavicons(absoluteUrl)

        return Response.ok(favicons)
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