import scala.collection.mutable.ArrayBuffer

class Teacher(
  name: String,
  age: Int,
  address: Address,
  private var _salary: Double,
  private val _studentLimit: Int
) extends Human(name, age, address) {

  private val _students: ArrayBuffer[Student] = ArrayBuffer.empty[Student]

  // ===== Гетери =====
  def salary: Double = _salary
  def studentLimit: Int = _studentLimit
  def students: List[Student] = _students.toList

  // ===== Сетер =====
  def salary_=(s: Double): Unit = {
    require(s >= 0, s"Зарплата не може бути від'ємною: $s")
    _salary = s
  }

  // ===== Додавання (з перевіркою дубліката за посиланням) =====
  def addStudent(s: Student): Boolean = {
    if (_students.exists(_ eq s)) {
      println(s"Студент ${s.name} вже доданий до $name")
      false
    } else if (_students.size >= _studentLimit) {
      println(s"Ліміт студентів ($_studentLimit) вичерпано для $name")
      false
    } else {
      _students += s
      true
    }
  }

  // ===== Прибирання =====
  def removeStudent(s: Student): Boolean = {
    val idx = _students.indexOf(s)
    if (idx >= 0) { _students.remove(idx); true }
    else false
  }

  // ===== 1. Пошук студентів за групою =====
  def findStudentsByGroup(group: String): List[Student] =
    _students.filter(_.group == group).toList

  // ===== 2. Середня оцінка =====
  def averageEstimation: Option[Double] =
    if (_students.isEmpty) None
    else Some(_students.map(_.grade.toDouble).sum / _students.size)

  // ===== show =====
  override def show(): Unit = {
    super.show()
    println(s"Зарплата:  $salary")
    println(s"Ліміт:     $studentLimit")
    println(s"Студентів: ${_students.size}")
    if (_students.nonEmpty) {
      println("Список студентів:")
      _students.foreach(s =>
        println(s"  - ${s.name} (група ${s.group}, оцінка ${s.grade})"))
    }
  }
}