package patmat

/**
 * Created by baugarten on 10/13/14.
 */
abstract class Nat {
  def isZero: Boolean
  def predecessor: Nat
  def successor: Nat = { new Successor(this) }
  def + (that: Nat): Nat
  def - (that: Nat): Nat
}


class Successor(x: Nat) extends Nat {
  override def isZero: Boolean = false

  //  override def successor: Nat = new Successor(this)

  override def +(that: Nat): Nat = that match {
    case x: Successor => successor + that.predecessor
    case _ => this
  }

  override def -(that: Nat): Nat = ???

  override def predecessor: Nat = x
}

object Zero extends Nat {
  def isZero = true
  def predecessor = throw new RuntimeException("lksajdkad")
  //  def successor = { new Successor(Zero) }
  def + (that: Nat): Nat = that
  def - (that: Nat): Nat = that match {
    case x: Successor => throw new RuntimeException("negative!")
    case _ => this
  }
}


//Zero.successor.successor.+(Zero.successor)
