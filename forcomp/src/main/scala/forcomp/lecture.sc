import forcomp.Anagrams

def isPrime(n: Int): Boolean = {
  (2 until n).forall(n % _ != 0)
}
def addTerms(terms: Map[Int, Double], term: (Int, Double)) = {
  term match {
    case (exp, coef) =>
      terms ++ Map(exp -> (coef + terms(exp)))
  }
}

def scalarProduct(xs: List[Double], ys: List[Double]): Double = (for {
  x <- xs
  ys <- ys
} yield (x * ys)).sum

class Poly(val terms: Map[Int, Double]) {
  def +(other: Poly) =
    new Poly(other.terms.foldLeft(Map(0 -> 0.0))(addTerms))

}

//val anagrams = Anagrams.combinations(List(('a' -> 2), ('b'-> 2)))
//anagrams.size

val x = List(('a', 1), ('d', 1), ('l', 1), ('r', 1))
val y = List(('r', 1))

Anagrams.subtract(x, y)

isPrime(7)
isPrime(13)
isPrime(14)
isPrime(28)
isPrime(53)