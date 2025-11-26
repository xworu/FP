import scala.concurrent._
import scala.concurrent.duration._
import ExecutionContext.Implicits.global

// ====================
// ЗАДАНИЕ 1: Простой Future
// ====================
def simpleFuture(): Unit =
  println("=== Задание 1: Простой Future ===")

  println("Старт асинхронной операции...")

  val futureResult: Future[Int] = Future {
    Thread.sleep(2000)  // моделируем долгую операцию
    42                  // результат
  }

  futureResult.onComplete {
    case scala.util.Success(value) =>
      println(s"Операция завершена. Результат = $value")
    case scala.util.Failure(e) =>
      println(s"Ошибка: ${e.getMessage}")
  }

  Thread.sleep(2500)


// ====================
// ЗАДАНИЕ 2: Promise
// ====================
def promiseExample(): Unit =
  println("\n=== Задание 2: Promise и Future ===")

  val promise = Promise[Int]()
  val future = promise.future

  Future {
    println("Запуск вычисления в отдельном потоке...")
    Thread.sleep(1500)

    val a = 6
    val b = 7
    val result = a * b

    if (result > 0)
      promise.success(result)
    else
      promise.failure(new Exception("Ошибка: некорректный результат"))
  }

  future.onComplete {
    case scala.util.Success(value) =>
      println(s"Future завершён успешно. Результат: $value")
    case scala.util.Failure(e) =>
      println(s"Future завершён ошибкой: ${e.getMessage}")
  }

  Thread.sleep(2500)


// ====================
// Главная точка входа
// ====================
@main def runAll(): Unit =
  simpleFuture()
  promiseExample()

