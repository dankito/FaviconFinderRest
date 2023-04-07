package net.dankito.utils.favicon.rest.service

import net.dankito.utils.favicon.Favicon
import net.dankito.utils.favicon.FaviconFinder
import net.dankito.utils.favicon.rest.model.SizeSorting
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class FaviconFinderService {

  protected val faviconFinder = FaviconFinder()

  fun findFavicons(url: String, sortedBy: SizeSorting ): List<Favicon> {
    val absoluteUrl = makeUrlAbsolute(url)

    val favicons = faviconFinder.extractFavicons(absoluteUrl)

    return if (sortedBy == SizeSorting.Descending) {
      favicons.sortedByDescending { it.size }
    }
    else {
      favicons.sortedBy { it.size }
    }
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