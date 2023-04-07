package net.dankito.utils.favicon.rest.service

import net.dankito.utils.favicon.Favicon
import net.dankito.utils.favicon.FaviconFinder
import net.dankito.utils.favicon.rest.model.SizeSorting
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class FaviconFinderService {

  protected val faviconFinder = FaviconFinder()

  fun findFavicons(url: String, sortedBy: SizeSorting ): List<Favicon> {
    val favicons = faviconFinder.extractFavicons(url)

    return if (sortedBy == SizeSorting.Descending) {
      favicons.sortedByDescending { it.size }
    }
    else {
      favicons.sortedBy { it.size }
    }
  }

}