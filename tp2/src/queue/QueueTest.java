package queue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {

  private String FIRST_ELEMENT = "First";
  private String SECOND_ELEMENT = "Second";
  private String SINGLE_ELEMENT = "Something";

  @Test
  public void test01QueueShouldBeEmptyWhenCreated() { assertTrue(new Queue().isEmpty()); }

  @Test
  public void test02AddElementsToTheQueue() { assertFalse(createOneElementQueue().isEmpty()); }

  @Test
  public void test03AddedElementsIsAtHead() { assertEquals(SINGLE_ELEMENT, createOneElementQueue().head()); }

  @Test
  public void test04TakeRemovesElementsFromTheQueue() {
    Queue queue = createOneElementQueue();
    queue.take();

    assertTrue(queue.isEmpty());
  }

  @Test
  public void test05TakeReturnsLastAddedObject() {
    Queue queue = createOneElementQueue();

    assertEquals(SINGLE_ELEMENT, queue.take());
  }

  @Test
  public void test06QueueBehavesFIFO() {
  Queue queue = createTwoElementQueue();

    assertEquals(queue.take(), FIRST_ELEMENT);
    assertEquals(queue.take(), SECOND_ELEMENT);
    assertTrue(queue.isEmpty());
  }

  @Test
  public void test07HeadReturnsFirstAddedObject() {
    Queue queue = createTwoElementQueue();

    assertEquals(queue.head(), FIRST_ELEMENT);
  }

  @Test
  public void test08HeadDoesNotRemoveObjectFromQueue() {
    Queue queue = createOneElementQueue();
    assertEquals(1, queue.size());
    queue.head();
    assertEquals(1, queue.size());
  }

  @Test
  public void test09SizeRepresentsObjectInTheQueue() {
    assertEquals(2, createTwoElementQueue().size());
  }

  @Test
  public void test10CanNotTakeWhenThereAreNoObjectsInTheQueue() {
    Queue queue = new Queue();
    assertTrue(throwsEmptyError(queue::take));
  }

  @Test
  public void test09CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
    Queue queue = createOneElementQueue();
    queue.take();

    assertTrue(throwsEmptyError(queue::take));
  }

  @Test
  public void test10CanNotHeadWhenThereAreNoObjectsInTheQueue() {
    Queue queue = new Queue();
    assertTrue(throwsEmptyError(queue::head));
  }

  private boolean throwsEmptyError(Executable runnable) {
    Throwable exception = assertThrows(Error.class, runnable);
    return exception.getMessage().equals(EmptyContainer.emptyError);
  }

  private Queue createOneElementQueue() {
    return new Queue().add(SINGLE_ELEMENT);
  }

  private Queue createTwoElementQueue() {
    return new Queue().add(FIRST_ELEMENT).add(SECOND_ELEMENT);
  }
}