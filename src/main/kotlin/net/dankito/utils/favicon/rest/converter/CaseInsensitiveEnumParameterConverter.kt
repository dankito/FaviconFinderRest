package net.dankito.utils.favicon.rest.converter

import net.dankito.utils.favicon.rest.model.SizeSorting
import java.lang.reflect.Type
import javax.ws.rs.ext.ParamConverter
import javax.ws.rs.ext.ParamConverterProvider
import javax.ws.rs.ext.Provider

@Provider
class CaseInsensitiveEnumParameterConverterProvider : ParamConverterProvider {
  override fun <T : Any> getConverter(clazz: Class<T>, genericType: Type?, annotations: Array<out Annotation>?): ParamConverter<T>? {
    if (clazz == SizeSorting::class.java) {
      return CaseInsensitiveEnumParameterConverter() as ParamConverter<T>
    }

    return null
  }

}

class CaseInsensitiveEnumParameterConverter : ParamConverter<SizeSorting> {

  override fun toString(enum: SizeSorting): String =
    enum.name // will never be used

  override fun fromString(string: String?): SizeSorting? =
    SizeSorting.values().first { it.name.equals(string, true) }
}