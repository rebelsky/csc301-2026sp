/**
 * Simple pairs. Ah, the joy of generics.
 */
public class Pair<T,U> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The first element of the pair.
   */
  T first;

  /**
   * The second element of the pair.
   */
  U second;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new pair.
   */
  public Pair(T _first, U _second) {
    this.first = _first;
    this.second = _second;
  } // Pair

  // +---------+-----------------------------------------------------
  // | Getters |
  // +---------+

  /**
   * Get the first element.
   */
  public T first() {
    return this.first;
  } // first()

  /**
   * Get the second element.
   */
  public U second() {
    return this.second;
  } // second()

} // class Pair
