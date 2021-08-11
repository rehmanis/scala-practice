# Scala Notes

Notes taken from the Coursera course [Functional Programming Principles in Scala](https://www.coursera.org/learn/scala-functional-programming).


## Week 3:

Data and Abstraction

###  Persistent Data structures

* The Scala programming language promotes the use of persistent data 
structures for implementing programs using "Object-Functional Style".
Scala contains implementations of many Persistent data structures including 
Linked Lists, Red–black trees, as well as persistent hash array mapped tries 
as introduced in Clojure. Source: https://en.wikipedia.org/wiki/Persistent_data_structure

### Object Definition


```scala
object Empty extends IntSet:
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = NonEmpty(x, Empty, Empty)
end Empty
```
* This defines a singleton object named `Empty`.
No other `Empty` instance can be (or needs to be) created.
Singleton objects are values, so `Empty` evaluates to itself.

### Companion Objects

* An object and a class can have the same name. This is possible since
Scala has two global namespaces: one for types and one for values.
Classes live in the type namespace, whereas objects live in the term
namespace.

* If a class and object with the same name are given in the same sourcefile,
we call them `companions`. Example:

```scala
class IntSet ...
  object IntSet:
  def singleton(x: Int) = NonEmpty(x, Empty, Empty)
```
* This defines a method to build sets with one element, which can be called
as `IntSet.singleton(elem)`.

* A companion object of a class plays a role similar to static class definitions
in Java (which are absent in Scala).