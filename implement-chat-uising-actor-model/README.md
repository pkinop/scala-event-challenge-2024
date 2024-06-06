# 【Scala経験者限定！】アクタープログラミングを使ってビジネスチャットの実装に挑戦しよう: 事前課題

## 問題内容

この問題の解答のSHA-256ハッシュ値をイベントの申し込みフォームにご入力ください。

## 詳細

1から100までの数を順に評価し、以下のルールに従って各数値に対応する文字列を生成してください。

- 数値が3で割り切れる場合は「Fizz」
- 数値が5で割り切れる場合は「Buzz」
- 数値が7で割り切れる場合は「Jazz」
- 数値が3と5の両方で割り切れる場合は「FizzBuzz」
- 数値が3と7の両方で割り切れる場合は「FizzJazz」
- 数値が5と7の両方で割り切れる場合は「BuzzJazz」
- 数値が3と5と7の全てで割り切れる場合は「FizzBuzzJazz」
- 上記のいずれにも該当しない場合は、その数値をそのまま出力します。

上記によって生成された文字列のコレクションを computeSha256Hex の引数に与えて得られる結果を提出してください。

※正確なハッシュ値の提出がイベント参加のための必須条件です。
ハッシュ値の計算間違いや、ルールの誤解があると参加資格が得られませんのでご注意ください。

以下の、Scala3のコードテンプレートを参考にしてください。

```scala
import java.security.MessageDigest

@main
def main(): Unit = {
  val values = ??? // 実装してください
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
```
