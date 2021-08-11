package funsets

/**
 * This class is a test suite for the methods in object FunSets.
 *
 * To run this test suite, start "sbt" then run the "test" command.
 */
class FunSetSuite extends munit.FunSuite:

  import FunSets.*

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets:
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(4)
    val s5 = singletonSet(-1000)
    val s6 = singletonSet(1000)
    val sa = union(union(s1, s2), union(s3, s4))
    val sb = union(s1, s4)
  /**
   * This test is currently disabled (by using @Ignore) because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", remove the
   * .ignore annotation.
   */
  test("singleton set one contains one") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets:
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
      assert(!contains(s1, 2), "Singleton")
      assert(!contains(s1, -1), "Singleton")
      assert(!contains(s1, 0), "Singleton")
  }

  test("union contains all elements of each set") {
    new TestSets:
      assert(contains(sb, 1), "Union 1")
      assert(contains(sb, 4), "Union 4")
      assert(!contains(sb, 2), "Union 2")
      assert(!contains(sb, 3), "Union 3")
  }

  test("intersect contains common elements of each set") {
    new TestSets:
      val s = intersect(sa, sb)
      assert(contains(s, 1), "Intersect 1")
      assert(contains(s, 4), "Intersect 4")
      assert(!contains(s, 3), "Intersect 3")
      assert(!contains(s, 2), "Intersect 2")
  }

  test("diff contains only the uncommon") {
    new TestSets:
      val s = diff(sa, sb)
      assert(contains(s, 2), "Diff 2")
      assert(contains(s, 3), "Diff 3")
      assert(!contains(s, 1), "Diff 1")
      assert(!contains(s, 4), "Diff 4")
  }

  test("filter based on condition") {
    new TestSets:
      val s = filter(sa, x => x > 2)
      assert(contains(s, 3), "filter 3")
      assert(contains(s, 4), "filter 4")
      assert(!contains(s, 1), "filter 1")
      assert(!contains(s, 2), "filter 2")
  }


  test("forall with conditions") {
    new TestSets:
      assert(!forall(sa, x => x > 2), "forall values greater than 2")
      assert(forall(sa, x => x > 0), "forall values greater than 0")
      assert(forall(sa, x => x > 0 && x <= 4), "forall values greater than 0 and less than 5")
      assert(!forall(sa, x => x > 0 && x <= 3), "forall values greater than 0 and less than 4")
      assert(!forall(sa, x => x < 0), "forall values less than 0")
      assert(forall(s5, x => x < 0), "forall on negative boundary value")
      assert(forall(s6, x => x > 0), "forall on positive boundary value")
  }

  test("exists with conditions") {
    new TestSets:
      assert(exists(sa, x => x > 3), "exists values greater than 3")
      assert(!exists(sa, x => x > 4), "exists values greater than 4")
      assert(exists(s5, x => x < 0), "exists on negative boundary value")
      assert(exists(s6, x => x > 0), "exists on positive boundary value")
      assert(!exists(s5, x => x < -1000), "exits outside negative boundary value")
      assert(!exists(s6, x => x > 1000), "exits outside positive boundary value")
  }

  test("map each val to some function") {
    new TestSets:
      val s = map(sa, x => x * x)
      assert(contains(s, 1), "map contains 1")
      assert(!contains(s, 2), "map does not contains 2")
      assert(!contains(s, 3), "map does not contains 3")
      assert(contains(s, 4), "map contains 4")
      assert(contains(s, 9), "map contains 9")
      assert(contains(s, 16), "map contains 16")
  }

  import scala.concurrent.duration.*
  override val munitTimeout = 10.seconds
