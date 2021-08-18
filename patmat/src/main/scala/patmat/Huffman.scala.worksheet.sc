
/* SCRATCH PAD */

val s = Seq("apple", "oranges", "apple", "banana", "apple", "oranges", "oranges")
val b = List("a", "b", "a", "c", "c", "c")


b.groupBy(identity).toList
val c = b.groupBy(identity).map(x => (x._1, x._2.size)).toList
c.sortBy(_._2)