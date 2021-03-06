package week01;

import java.util.Deque;
import java.util.LinkedList;

/**
 * TestDeque
 */
public class TestDeque {

  public static void main(String[] args) {
    Deque<String> deque = new LinkedList<>();
    deque.addFirst("a");
    deque.addFirst("b");
    deque.addFirst("c");
    System.out.println(deque);

    String str = deque.peekFirst();
    System.out.println(str);
    System.out.println(deque);

    while(deque.size() > 0) {
      System.out.println(deque.removeFirst());
    }

    System.out.println(deque);
  }
}