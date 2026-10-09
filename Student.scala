import scala.util.Random

class Student(
  name: String,
  age: Int,
  address: Address,
  private var _group: String
) extends Human(name, age, address) {

  require(_group.trim.nonEmpty, "Група не може бути порожньою")

  private val _grade: Int = Random.nextInt(5) + 1

  def group: String = _group
  def grade: Int = _grade

  def group_=(g: String): Unit = {
    require(g.trim.nonEmpty, "Група не може бути порожньою")
    _group = g
  }


  override def show(): Unit = {
    super.show()
    println(s"Група:  $group")
    println(s"Оцінка: $grade")
  }
}