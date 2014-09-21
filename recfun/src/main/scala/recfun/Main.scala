package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(col: Int, row: Int): Int = {
    if (col < 0 || col > row) 0
    else if (row == 0) 1
    else pascal(col - 1, row - 1) + pascal(col, row - 1)
  }


  /**
    * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def balanceImpl(chars: List[Char], stack: List[Char]): Boolean = {
      if (chars.isEmpty) stack.isEmpty
      else balanceImpl(chars.tail, maybePushOrPopOffStack(stack, chars.head))
    }
    def maybePushOrPopOffStack(chars: List[Char], c: Char): List[Char] = {
      if (c == '(') chars :+ c
      else if (c == ')' && chars.nonEmpty && chars.last == '(') chars.tail
      else if (c == ')') chars :+ c
      else chars
    }

    balanceImpl(chars, List())
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money < 0 || coins.isEmpty) 0
    else if (money == 0) 1
    else {
      countChange(money - coins.head, coins) + countChange(money, coins.tail)
    }
  }
}
