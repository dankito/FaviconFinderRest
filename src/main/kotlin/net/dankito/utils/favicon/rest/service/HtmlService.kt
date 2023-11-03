package net.dankito.utils.favicon.rest.service

import jakarta.enterprise.context.ApplicationScoped
import net.dankito.utils.favicon.Favicon

@ApplicationScoped
class HtmlService {

  fun createHtmlPage(url: String, favicons: List<Favicon>): String {
    val html = StringBuilder()
      .appendLine("<!DOCTYPE html>")
      .appendLine("<html>")
      .appendLine("<head>")
      .appendLine("<meta charset=\"UTF-8\">")
      .appendLine("<title>Favicon Finder - $url</title>")
      .appendLine("<style>")
      .appendLine("td {")
      .appendLine("min-height: 50px;")
      .appendLine("min-width: 120px;")
      .appendLine("text-align: center;")
      .appendLine("text-align: center;")
      .appendLine("vertical-align: middle;")
      .appendLine("}")
      .appendLine("</style>")
      .appendLine("</head>")
      .appendLine("<body>")
      .appendLine("<h1>$url</h1>")
      .appendLine("<h4>${favicons.size} favicons</h4>")
      .appendLine("<table>")
      .appendLine("<tr>")
      .appendLine("<th>Icon</th>")
      .appendLine("<th>Size</th>")
      .appendLine("<th>Type</th>")
      .appendLine("<th>Image mime type</th>")
      .appendLine("<th>URL</th>")
      .appendLine("</tr>")

    favicons.forEach { favicon ->
      html
        .appendLine("<tr>")
        .appendLine("<td><img src=\"${favicon.url}\" ${if (favicon.size != null) "width=\"${favicon.size!!.width}\" height=\"${favicon.size!!.height}\"" else ""} /></td>")
        .appendLine("<td>${if (favicon.size != null) { favicon.size!!.width.toString() + " x " + favicon.size!!.height } else ""}</td>")
        .appendLine("<td>${favicon.iconType}</td>")
        .appendLine("<td>${if (favicon.imageMimeType != null) favicon.imageMimeType else ""}</td>")
        .appendLine("<td><a href=\"${favicon.url}\">${favicon.url}</a></td>")
        .appendLine("</tr>")
    }

    html
      .appendLine("</table>")
      .appendLine("</body>")
      .appendLine("</html>")

    return html.toString()
  }

}