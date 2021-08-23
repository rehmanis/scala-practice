
/* SCRATCH PAD */

val s = Seq("apple", "oranges", "apple", "banana", "apple", "oranges", "oranges")
val b = List("a", "b", "a", "c", "c", "c")


b.groupBy(identity).toList
val c = b.groupBy(identity).map(x => (x._1, x._2.size)).toList
c.sortBy(_._2)


def pack[T](xs: List[T]): List[List[T]] = xs match

    case Nil => Nil
    case x :: xs1 => 
        val (more, rest) = xs1.span(y => y == x)
        (x :: more) :: pack(rest)


val elems = List('a', 'a', 'b', 'a')
pack(elems)

4 / 2
6 / 2
5 / 2

val mnem = Map('2' -> "ABC", '3' -> "DEF", '4' -> "GHI")

val charCode: Map[Char, Char] =
    for (digit, str) <- mnem
        ltr <- str
    yield ltr -> digit

val test = "ABC"
test.map(x => (x + 1).toChar)
test.map(charCode)
val x = charCode
charCode('A')

val w = "aabcdd"
w.groupBy(identity).map((ltr, list) => (ltr, list.length)).toList

val words = List("I", "am", "legend")
words.mkString("")


/** A word is simply a `String`. */
type Word = String

/** A sentence is a `List` of words. */
type Sentence = List[Word]
type Occurrences = List[(Char, Int)]

/** The dictionary is simply a sequence of words.
 *  It is predefined and obtained as a sequence using the utility method `loadDictionary`.
 */
val dictionary: List[Word] = List("add", "you", "ate", "tea", "dad")

/** Converts the word into its character occurrence list.
 *
 *  Note: the uppercase and lowercase version of the character are treated as the
 *  same character, and are represented as a lowercase character in the occurrence list.
 *
 *  Note: you must use `groupBy` to implement this method!
 */
def wordOccurrences(w: Word): Occurrences = w.groupBy(letter => letter).map((ltr, list) => (ltr, list.length)).toList

/** Converts a sentence into its character occurrence list. */
def sentenceOccurrences(s: Sentence): Occurrences = wordOccurrences(s.mkString(""))

val dictionaryByOccurrences: Map[Occurrences, List[Word]] = dictionary.groupBy(wordOccurrences).withDefaultValue(List())


val ocurr = wordOccurrences("aabbb")
dictionaryByOccurrences(ocurr)
ocurr ::: List(('c', '1')) 


def combinations(occurrences: Occurrences): List[Occurrences] =
    if occurrences.isEmpty then List(List())
    else
        for
            (ltr, freq) <- occurrences
            currFreq <- 1 to freq
            rest <- combinations(occurrences.filter((char, _) => char > ltr ))
        yield List((ltr, currFreq)) ::: rest

    // case (ltr, freq) :: xs => (for {
    //     i <- 0 to n
    //     tail <- combinations(xs)
    // } yield ((c, i) :: tail).filter(_._2 != 0))


combinations(List(('a', 2), ('b', 3)))

val z = List(('a', 1), ('d', 1), ('l', 1), ('r', 1))
val y = List(('r', 1))

// z.updated(0, ('a', 2))

// z.foldLeft(y)((lst, pair) => pair :: lst)

z.map((ltr, freq) => (ltr, freq - y.toMap.getOrElse(ltr, 0))).filter(_._2 > 0)