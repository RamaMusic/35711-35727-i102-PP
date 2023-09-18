package queue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueueTest {

  @Test
  public void test01QueueShouldBeEmptyWhenCreated() { assertTrue( new Queue().isEmpty() ); }

  @Test
  public void test02AddElementsToTheQueue() { assertFalse( createQueueWithOneElement().isEmpty() ); }

  @Test
  public void test03AddedElementsIsAtHead() { assertEquals("Something", createQueueWithOneElement().head() ); }

  @Test
  public void test04TakeRemovesElementsFromTheQueue() { assertTrue( createQueueWithOneElementAndRemoveIt().isEmpty() ); }

  @Test
  public void test05TakeReturnsLastAddedObject() {
    assertEquals("Something", createQueueWithOneElement().take() );
  }

  @Test
  public void test06QueueBehavesFIFO() {
  Queue queue = createQueueWithTwoElements();

    assertEquals( queue.take(), "First");
    assertEquals( queue.take(), "Second");
    assertTrue( queue.isEmpty() );
  }

  @Test
  public void test07HeadReturnsFirstAddedObject() { assertEquals( createQueueWithTwoElements().head(), "First"); }

  @Test
  public void test08HeadDoesNotRemoveObjectFromQueue() {
    Queue queue = createQueueWithOneElement();
    assertEquals( 1, queue.size() );
    queue.head();
    assertEquals(1, queue.size());
  }

  @Test
  public void test09SizeRepresentsObjectInTheQueue() {
    assertEquals(2, createQueueWithTwoElements().size());
  }

  @Test
  public void test10CanNotTakeWhenThereAreNoObjectsInTheQueue() { assertTrue( throwsEmptyError(new Queue()::take ) ); }

  @Test
  public void test09CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
    assertTrue( throwsEmptyError( createQueueWithOneElementAndRemoveIt()::take ) );
  }

  @Test
  public void test10CanNotHeadWhenThereAreNoObjectsInTheQueue() {
    assertTrue( throwsEmptyError( new Queue()::head ) );
  }

  private boolean throwsEmptyError( Executable runnable ) {
    return assertThrows( Error.class, runnable, "Expected Error was not thrown." ).getMessage().equals( EmptyContainer.emptyError );
  }
  private Queue createQueueWithOneElement() {
    return new Queue().add("Something");
  }

  private Queue createQueueWithTwoElements() {
    return new Queue().add("First").add("Second");
  }

  private Queue createQueueWithOneElementAndRemoveIt() {
    Queue queue = new Queue().add("Something");
    queue.take();
    return queue;
  }
}