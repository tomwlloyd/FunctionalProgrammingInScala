package chapter3

sealed trait CustomList[+A]

case object CustomNil extends CustomList[Nothing]
case class CustomCons[+A](head: A, tail: CustomList[A]) extends CustomList[A]

object CustomList {
  def foldRight[A, B](as: CustomList[A], z: B)(f: (A, B) => B): B = as match {
    case CustomCons(x, xs) => f(x, foldRight(xs, z)(f))
    case CustomNil => z
  }

  def sum(ints: CustomList[Int]): Int = ints match {
    case CustomCons(x, xs) => x + sum(xs)
    case CustomNil => 0
  }

  def apply[A](as: A*): CustomList[A] = {
    if (as.isEmpty) CustomNil
    else CustomCons(as.head, apply(as.tail: _*))
  }
}