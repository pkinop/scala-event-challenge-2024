import java.security.MessageDigest

@main
def main(): Unit = {
  test()
  val numbers = 1 to 100
  val values = numbers.map(answer) // 実装してください
  println(computeSha256Hex(values))
}

val md = MessageDigest.getInstance("SHA-256")
def computeSha256Hex(values: Seq[String]): String = {
  require(values.nonEmpty, "values must not be empty")
  require(values.length == 100, "values must have 100 elements")
  val s = values.mkString(", ")
  val hash = md.digest(s.getBytes("UTF-8"))
  hash.map("%02x".format(_)).mkString
}

trait Converter {
  def convert(s: String, n: Int): String
}

class AddStringConverter(n: Int, str: String) extends Converter {
  override def convert(s: String, x: Int): String =
    if (x % n == 0) s + str
    else s
}

object EmptyStringConverter extends Converter {
  override def convert(s: String, n: Int): String =
    if (s.isEmpty) n.toString
    else s
}

def answer(x: Int): String = {
  val converters = Seq(
    new AddStringConverter(3, "Fizz"),
    new AddStringConverter(5, "Buzz"),
    new AddStringConverter(7, "Jazz"),
    EmptyStringConverter
  )

  converters.foldLeft("") { case (s, converter) =>
    converter.convert(s, x)
  }
}

def test(): Unit = {
  val expected = List(
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "Jazz",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "Jazz",
    "FizzBuzz", // 3 * 5
    "16",
    "17",
    "Fizz",
    "19",
    "Buzz",
    "FizzJazz", // 3 * 7
    "22",
    "23",
    "Fizz",
    "Buzz",
    "26",
    "Fizz",
    "Jazz",
    "29",
    "FizzBuzz",
    "31",
    "32",
    "Fizz",
    "34",
    "BuzzJazz", // 7 * 5

    "FizzBuzzJazz"
  )
  val actual = ((1 to 35) :+ 105).map(answer).toList

  expected.zip(actual).foreach { case (e, a) =>
    println(s"expected: $e, actual: $a")
    require(e == a)
  }
}
