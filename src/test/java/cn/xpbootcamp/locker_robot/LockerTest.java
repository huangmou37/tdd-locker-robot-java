package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.NoAvailableLockerBoxException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LockerTest {

  @Test
  void shouldHasNumberOfAvailableBoxesAsCapacityWhenInitialized() {
    Locker locker = new Locker(20);
    assertEquals(20, locker.getNumberOfAvailableBoxes());
  }

  @Test
  void shouldReturnBoxWhenDepositAndNumberOfAvailableBoxesLargerThanZero() throws NoAvailableLockerBoxException {
    Locker locker = new Locker(20);
    LockerBox box = locker.deposit();
    assertNotNull(box);
    assertNotNull(box.getReceipt());
  }

  @Test
  void shouldReduceNumberOfAvailableBoxesWhenDepositSuccessfully() throws NoAvailableLockerBoxException {
    Locker locker = new Locker(20);
    locker.deposit();
    assertEquals(19, locker.getNumberOfAvailableBoxes());
  }

  @Test
  void shouldThrowExceptionWhenDepositNoAvailableBox() throws NoAvailableLockerBoxException {
    Locker locker = new Locker(5);
    for (int i=0;i<5;i++) {
      locker.deposit();
    }
    assertEquals(0, locker.getNumberOfAvailableBoxes());
    NoAvailableLockerBoxException exception = assertThrows(NoAvailableLockerBoxException.class, locker::deposit);
    assertEquals("Locker is full", exception.getMessage() );
  }
}
