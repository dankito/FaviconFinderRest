package net.dankito.utils.favicon.rest.service

import net.codinux.log.LoggerFactory.logger
import net.dankito.utils.favicon.Favicon
import net.dankito.utils.favicon.FaviconFinder
import net.dankito.utils.favicon.rest.model.SizeSorting
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class FaviconFinderService {

  protected val faviconFinder = FaviconFinder()

  private val log by logger()


  fun findFavicons(url: String, sortedBy: SizeSorting ): List<Favicon> {
    val favicons = faviconFinder.extractFavicons(url)

    log.info("Found ${favicons.size} favicons for '$url'")

    return if (sortedBy == SizeSorting.Descending) {
      favicons.sortedByDescending { it.size }
    }
    else {
      favicons.sortedBy { it.size }
    }
  }

}