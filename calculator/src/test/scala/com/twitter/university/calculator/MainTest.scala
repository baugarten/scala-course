package com.twitter.university.calculator

import scala.util.Try


class MainTest extends org.scalatest.FunSuite {

  test("evaluate well-formed expression") {
    val expression: Try[Int] = Main.evaluateExpression("1 2 +")
    assert(expression.get == 3)
  }

  test("evaluate subtraction") {
    val expression: Try[Int] = Main.evaluateExpression("2 1 - ") // 5 + 4 + 3 - 2 - 1
    assert(expression.get == 1)
  }

  test("evaluate subtraction when popped off stack") {
    val expression: Try[Int] = Main.evaluateExpression("3 1 1 + - ") // 5 + 4 + 3 - 2 - 1
    assert(expression.get == 1)
  }

  test("evaluate long well-formed expression") {
    val expression: Try[Int] = Main.evaluateExpression("2 3 4 5 + + -") // 2 - (5 + 4 + 3)
    assert(expression.get == -10)
  }

  test("evaluate division") {
    val expression: Try[Int] = Main.evaluateExpression("3 4 5 + + 2 /") // (5 + 4 + 3) / 2 == 6
    assert(expression.get == 6)
  }

  test("returns an error if too many operands") {
    val expression: Try[Int] = Main.evaluateExpression("3 4 5 + + 2 / 3")
    assert(expression.failed.get.getMessage == "Ran out of tokens to evaluate but still have operands left:  Stack(Operand(3), Operand(6))")
  }

  test("returns an error if too many operators") {
    val expression: Try[Int] = Main.evaluateExpression("3 4 5 + + 2 / 3 + -")
    assert(expression.failed.get.getMessage == "Found operator, but not enough operands")
  }
}
