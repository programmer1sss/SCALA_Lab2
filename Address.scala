case class Address(
    country: String,
    city: String
) {
  require(country.trim.nonEmpty, "Країна не може бути порожньою")
  require(city.trim.nonEmpty, "Місто не може бути порожнім")

  override def toString: String = s"$country, $city"
}
