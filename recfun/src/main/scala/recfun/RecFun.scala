package recfun

import scala.annotation.tailrec

object RecFun extends RecFunInterface:

  def main(args: Array[String]): Unit =
    println("Pascal's Triangle")
    for row <- 0 to 10 do
      for col <- 0 to row do
        print(s"${pascal(col, row)} ")
      println()

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = 
    if(c == 0 || c == r)
      1
    else
      pascal(c - 1, r - 1) + pascal(c, r - 1)


  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean =
    @tailrec
    def iter(chars: List[Char], count: Int) : Boolean =
      chars match {
        case Nil => count == 0
        case '(' :: tail => iter(tail, count + 1)
        case ')' :: tail => if count == 0 then false else iter(tail, count - 1)
        case _ :: tail => iter(tail, count)
      }
    iter(chars, 0)

  /**
   * Exercise 3
   * 
   */
  def countChange(money: Int, coins: List[Int]): Int = 

    if(money == 0) {
      return 1
    }

    coins match {
      case Nil => if money == 0 then 1 else 0
      case coin :: tail => 
        if coin <= money 
        then countChange(money, tail) + countChange(money - coin, coins) 
        else countChange(money, tail)
    }
