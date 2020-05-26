package uoption

object internals:
  case object UNone
  case class WrappedUNone(level: Int):
    assert(level > 0)

import internals._

opaque type UOption[+A] >: UNone.type = A | UNone.type | WrappedUNone

def [A](a: A).wrap: UOption[A] = a match
  case UNone => WrappedUNone(1)
  case WrappedUNone(l) => WrappedUNone(l+1)
  case a => a

def [A, B](oa: UOption[A]).fold(fa: A => B)(ifEmpty: => B): B = oa match
  case UNone => ifEmpty
  case WrappedUNone(1) => fa(UNone.asInstanceOf[A])
  case WrappedUNone(l) => fa(WrappedUNone(l-1).asInstanceOf[A])
  case a => fa(a.asInstanceOf[A])
