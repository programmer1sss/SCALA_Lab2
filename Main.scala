object Main {
  def main(args: Array[String]): Unit = {

    // ===== Адреси =====
    val kyiv  = Address("Ukraine", "Kyiv")
    val lviv  = Address("Ukraine", "Lviv")
    val odesa = Address("Ukraine", "Odesa")
    val kharkiv = Address("Ukraine", "Kharkiv")

    // ===== Студенти =====
    val ivan     = new Student("Ivan",     18, kyiv,  "131 M")
    val valerii  = new Student("Valerii",  19, kyiv,  "131 M")
    val olena    = new Student("Olena",    20, lviv,  "132 M")
    val mykola   = new Student("Mykola",   18, odesa, "131 M")

    // ===== Викладачі =====
    val ruslan = new Teacher("Ruslan", 45, kyiv, 15000.0, 5)
    val oksana = new Teacher("Oksana", 38, lviv, 18000.0, 3)

    // Додаємо студентів
    ruslan.addStudent(ivan)
    ruslan.addStudent(valerii)
    ruslan.addStudent(mykola)
    ruslan.addStudent(ivan)   // ❌ дублікат за посиланням

    oksana.addStudent(olena)
    oksana.addStudent(valerii)

    // ===== DataList =====
    val data = new DataList()
    data.add(ruslan)
    data.add(oksana)
    data.add(new Teacher("Petro", 50, kharkiv, 12000.0, 4))  // інше місто

    println("########## ВСІ ВИКЛАДАЧІ ##########")
    data.printAll()

    // ===== 1. Пошук за групою =====
    println("\n########## 1. Студенти групи '131 M' у Ruslan ##########")
    val g131 = ruslan.findStudentsByGroup("131 M")
    if (g131.isEmpty) println("Немає студентів у групі 131 M")
    else g131.foreach(s => println(s"  ${s.name} (${s.grade})"))

    println("\n########## 1. Студенти групи '999 X' (немає) ##########")
    val g999 = ruslan.findStudentsByGroup("999 X")
    if (g999.isEmpty) println("Немає студентів у групі 999 X")
    else g999.foreach(s => println(s"  ${s.name}"))

    // ===== 2. Середня оцінка =====
    println("\n########## 2. Середня оцінка Ruslan ##########")
    ruslan.averageEstimation match {
      case Some(avg) => println(f"Середня: $avg%.2f")
      case None      => println("Немає студентів — середньої немає")
    }

    println("\n########## 2. Середня оцінка Petro (немає студентів) ##########")
    data.findByName("Petro").headOption.foreach { p =>
      p.averageEstimation match {
        case Some(avg) => println(f"Середня: $avg%.2f")
        case None      => println("Немає студентів — середньої немає")
      }
    }

    // ===== 3. Пошук викладачів за містом =====
    println("\n########## 3. Викладачі у Kyiv ##########")
    val kyivTeachers = data.findTeachersByCity("Kyiv")
    if (kyivTeachers.isEmpty) println("Немає викладачів у Kyiv")
    else kyivTeachers.foreach(t => println(s"  ${t.name} — ${t.address}"))

    println("\n########## 3. Викладачі у Kharkiv ##########")
    val kharkivTeachers = data.findTeachersByCity("Kharkiv")
    if (kharkivTeachers.isEmpty) println("Немає викладачів у Kharkiv")
    else kharkivTeachers.foreach(t => println(s"  ${t.name} — ${t.address}"))

    println("\n########## 3. Викладачі у Berlin (немає) ##########")
    val berlin = data.findTeachersByCity("Berlin")
    if (berlin.isEmpty) println("Немає викладачів у Berlin")
    else berlin.foreach(t => println(s"  ${t.name}"))

    // ===== 4. Сума студентів =====
    println("\n########## 4. Сума студентів у всіх викладачів ##########")
    println(s"Всього записів: ${data.totalStudentAssignments}")

    println("\n########## 4. Сума у порожньому DataList ##########")
    val emptyData = new DataList()
    println(s"Всього записів: ${emptyData.totalStudentAssignments}")

    // ===== 5. Перевірка порожніх рядків =====
    println("\n########## 5. Перевірка порожніх рядків ##########")

    testRequire("Порожнє ім'я",          () => new Student("", 18, kyiv, "131 M"))
    testRequire("Пробіли в імені",       () => new Student("   ", 18, kyiv, "131 M"))
    testRequire("Порожня група",         () => new Student("Ivan", 18, kyiv, ""))
    testRequire("Пробіли в групі",       () => new Student("Ivan", 18, kyiv, "   "))
    testRequire("Порожня країна",        () => Address("", "Kyiv"))
    testRequire("Порожнє місто",         () => Address("Ukraine", " "))
    testRequire("Коректні дані",         () => new Student("Ivan", 18, kyiv, "131 M"))
  }

  // Допоміжний метод для демонстрації require
  def testRequire(label: String, block: () => Any): Unit = {
    try {
      block()
      println(s"  ✅ $label — ок")
    } catch {
      case e: IllegalArgumentException =>
        println(s"  ❌ $label — ${e.getMessage}")
    }
  }
}
