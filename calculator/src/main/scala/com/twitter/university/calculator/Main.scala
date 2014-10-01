package com.twitter.university.calculator

import scala.collection.immutable.Stack
import scala.util.{Failure, Success, Try}

object Main {

  object Token {
    def apply(str: String): Try[Token] = {
      Operators.get(str).map { op => Success(Operator(op)) }
        .getOrElse(Try(str.toInt).map(Operand))
    }
  }

  sealed trait Token
  case class Operator(op: Op) extends Token
  case class Operand(value: Int) extends Token

  type Op = (Int, Int) => Int
  val mult: Op = _ * _
  val div: Op = _ / _
  val add: Op = _ + _
  val sub: Op = _ - _

  val Operators = Map("*" -> mult, "+" -> add, "-" -> sub, "/" -> div)

  def main(args: Array[String]): Unit = {
    val resultOrFailure: Try[Int] = args.headOption
      .map(evaluateExpression)
      .getOrElse(Failure(new IllegalArgumentException("No cmd line params specified")))

    resultOrFailure.map(println)
    resultOrFailure.failed.map {t => println(t.getMessage)}
  }

  def evaluateExpression(expr: String): Try[Int] = {
    sequence(tokenize(expr))
      .flatMap(evaluate)
  }

  def tokenize(ss: String): List[Try[Token]] = ss.split(" ").toList.map(Token(_))

  /**
   * This is usually provided in a library. I can't find it though
   */
  def sequence[A](xs: List[Try[A]]): Try[List[A]] = {
    if (xs.isEmpty) Success(Nil)
    else xs.head.flatMap { x => sequence(xs.tail).map(x :: _)}
  }

  /**
   * Generic implementation for popping two values off of a stack,
   * returning the new stack after the state transition
   */
  def popTwice[A](stack: Stack[A]): Try[(A, A, Stack[A])] = Try {
    val (rhs, poppedOnce) = stack.pop2
    val (lhs, poppedTwice) = poppedOnce.pop2
    (lhs, rhs, poppedTwice)
  }

  def evaluate(tokens: List[Token]): Try[Int] = {
    def tryApplyOp(tokens: Stack[Operand], op: Op): Try[Stack[Operand]] = {
      val ops = popTwice(tokens)
      ops.flatMap { case (Operand(v1), Operand(v2), s) =>
        Success(s.push(Operand(op(v1, v2))))
      }.recoverWith { case _ =>
        Failure(new IllegalArgumentException("Found operator, but not enough operands"))
      }
    }

    def doApply(tokens: List[Token], stack: Stack[Operand]): Try[Operand] = {
      if (tokens.isEmpty && stack.size == 1) Success(stack.pop2._1)
      else if (tokens.isEmpty) {
        Failure(new IllegalArgumentException(s"Ran out of tokens to evaluate but still have operands left:  $stack"))
      } else {
        val newStack = tokens.head match {
          case Operator(op) => tryApplyOp(stack, op)
          case Operand(value) => Success(stack.push(Operand(value)))
        }
        newStack.flatMap(doApply(tokens.tail, _))
      }
    }

    doApply(tokens, Stack()).flatMap { case Operand(value) =>
      Success(value)
    }
  }
}
