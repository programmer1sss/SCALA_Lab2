import scala.collection.mutable.ArrayBuffer

class DataList {
  private val _teachers: ArrayBuffer[Teacher] = ArrayBuffer.empty[Teacher]

  // ===== Додавання / видалення =====
  def add(t: Teacher): Unit = _teachers += t

  def remove(t: Teacher): Boolean = {
    val idx = _teachers.indexOf(t)
    if (idx >= 0) { _teachers.remove(idx); true }
    else false
  }

  def all: List[Teacher] = _teachers.toList

  // ===== Виведення =====
  def printAll(): Unit = {
    if (_teachers.isEmpty) println("Список викладачів порожній")
    else _teachers.foreach { t =>
      t.show()
      println("-" * 40)
    }
  }

  // ===== 3. Пошук викладачів за містом =====
  def findTeachersByCity(city: String): List[Teacher] =
    _teachers.filter(_.address.city.equalsIgnoreCase(city)).toList

  // ===== 4. Сума студентів у всіх викладачів =====
  def totalStudentAssignments: Int =
    _teachers.map(_.students.size).sum

  // ===== Інші пошуки / сортування =====
  def findByName(name: String): List[Teacher] =
    _teachers.filter(_.name.equalsIgnoreCase(name)).toList

  def findByMinSalary(min: Double): List[Teacher] =
    _teachers.filter(_.salary >= min).toList

  def sortedByName: List[Teacher] = _teachers.sortBy(_.name).toList
  def sortedByAge: List[Teacher] = _teachers.sortBy(_.age).toList
  def sortedBySalaryDesc: List[Teacher] = _teachers.sortBy(-_.salary).toList
}