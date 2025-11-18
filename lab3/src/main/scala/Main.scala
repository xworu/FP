@main def patternMatchingDemo(): Unit =
  println("=== Лабораторная работа 3: Pattern Matching, ADT и Дженерики ===\n")

  // 1. Case-класс + pattern matching

  case class Person(name: String, age: Int, city: String)

  val p1 = Person("Laura", 18, "Twin Peaks")
  val p2 = Person("Shinji", 14, "Tokyo-2")

  def describePerson(person: Person): String = person match
    case Person(name, age, city) if age >= 18 =>
      s"$name совершеннолетний и живёт в $city"
    case Person(name, age, _) =>
      s"$name несовершеннолетний ($age лет)"

  println("1. Pattern matching с case-классом:")
  println(describePerson(p1))
  println(describePerson(p2))
  println()

  // 2. Функция, принимающая значение любого типа

  def typeInfo(x: Any): String = x match
    case n: Int       => s"Это число Int: $n"
    case s: String    => s"Это строка длиной ${s.length}"
    case b: Boolean   => s"Это булево значение: $b"
    case _: Person    => "Это объект Person"
    case list: List[?] => s"Это список с размером ${list.size}"
    case _            => "Неизвестный тип"

  println("2. Pattern matching для произвольных типов:")
  println(typeInfo(42))
  println(typeInfo("Scala"))
  println(typeInfo(true))
  println(typeInfo(p1))
  println(typeInfo(List(1, 2, 3)))
  println()

  // 3. ADT + вычисление выражений

  // ADT ( Algebraic Data Type )
  enum Expr:
    case Number(value: Int)
    case Add(left: Expr, right: Expr)
    case Mul(left: Expr, right: Expr)
    case Neg(expr: Expr)

  import Expr.*

  // Функция вычисления
  def eval(expr: Expr): Int = expr match
    case Number(v)    => v
    case Add(a, b)    => eval(a) + eval(b)
    case Mul(a, b)    => eval(a) * eval(b)
    case Neg(e)       => -eval(e)

  val expression =
    Add(
      Number(5),
      Mul(
        Number(2),
        Neg(Number(3))
      )
    )

  // Посчитаем выражение: 5 + 2 * (-3) = 5 - 6 = -1
  println("3. ADT для арифметических выражений:")
  println(s"Результат выражения = ${eval(expression)}")
  println()

  // 4. Полиморфная функция (Generics)

  // Берём первый элемент списка любого типа
  def firstElement[T](list: List[T]): Option[T] =
    list match
      case Nil => None
      case head :: _ => Some(head)

  println("4. Полиморфная функция (дженерики):")
  println(firstElement(List(10, 20, 30)))      // Some(10)
  println(firstElement(List("a", "b", "c")))   // Some(a)
  println(firstElement(List.empty[Int]))       // None
