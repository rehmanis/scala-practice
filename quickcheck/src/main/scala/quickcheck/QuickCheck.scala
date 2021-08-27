package quickcheck

import org.scalacheck.*
import Arbitrary.*
import Gen.*
import Prop.forAll

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap:

  lazy val genHeap: Gen[H] =
    for
      item <- arbitrary[A]
      h <- oneOf(const(empty), genHeap)
    yield insert(item, h)

  given Arbitrary[H] = Arbitrary(genHeap)

  property("gen1") = forAll { (h: H) =>
    val m = if isEmpty(h) then 0 else findMin(h)
    findMin(insert(m, h)) == m
  }

  // If you insert any two elements into an empty heap, finding the minimum of
  // the resulting heap should get the smallest of the two elements back.
  property("insertMin") = forAll { (a1: Int, a2: Int) =>
    val h = insert(a1, insert(a2, empty))
    findMin(h) == (a1 min a2)
  }


  // If you insert an element into an empty heap, then delete the minimum, the
  // resulting heap should be empty.
  property("deleteMin") = forAll { (a: Int) =>
    val h1 = insert(a, empty)
    val h2 = deleteMin(h1)
    isEmpty(h2)
  }

  // Given any heap, you should get a sorted sequence of elements when
  // continually finding and deleting minima. 
  property("ordering") = forAll { (h: H) =>
    def getSorted(h: H): List[A] = 
      if isEmpty(h) then Nil
      else
        findMin(h) :: getSorted(deleteMin(h))
    
    val xs = getSorted(h)
    xs == xs.sorted
  }

  // Finding a minimum of the melding of any two heaps should return a minimum
  // of one or the other.
  property("meldMin") = forAll { (h1: H, h2: H) =>
    val hMeld = meld(h1, h2)
    val meldMin = findMin(hMeld)
    meldMin == (findMin(h1) min findMin(h2))
  }

  // Meld and make sure the ordering remains the same
  property("meldOrdering") = forAll { (h1: H, h2: H) =>
    def getSorted(h: H): List[A] = 
      if isEmpty(h) then Nil
      else
        findMin(h) :: getSorted(deleteMin(h))
    
    val hMeld = meld(h1, h2)
    val xs = getSorted(hMeld)
    val xs1 = getSorted(h1)
    val xs2 = getSorted(h2)
    xs == (xs2 ::: xs1).sorted
    
  }