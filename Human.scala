class Human(
  private var _name: String,
  private var _age: Int,
  private var _address: Address
) {
  require(_name.trim.nonEmpty, "Ім'я не може бути порожнім")
  require(_age >= 0 && _age <= 150, s"Некоректний вік: $_age")

  def name: String = _name
  def age: Int = _age
  def address: Address = _address

  def name_=(n: String): Unit = {
    require(n.trim.nonEmpty, "Ім'я не може бути порожнім")
    _name = n
  }

  def age_=(a: Int): Unit = {
    require(a >= 0 && a <= 150, s"Некоректний вік: $a")
    _age = a
  }

  def address_=(addr: Address): Unit = _address = addr

  def show(): Unit = {
    println(s"Ім'я:   $name")
    println(s"Вік:    $age")
    println(s"Адреса: $address")
  }
}