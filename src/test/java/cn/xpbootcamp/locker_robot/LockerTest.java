package cn.xpbootcamp.locker_robot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LockerTest {

  @Test
  void shouldHasNumberOfAvailableBoxesAsCapacityWhenInitialized() {
    Locker locker = new Locker(20);
    assertEquals(20, locker.getNumberOfAvailableBoxes());
  }

  @Test
  void shouldReturnTrueWhenDepositAndNumberOfAvailableBoxesLargerThanZero() {
    Locker locker = new Locker(20);
    assertTrue(locker.deposit());
  }

  @Test
  void shouldReduceNumberOfAvailableBoxesWhenDepositSuccessfully() {
    Locker locker = new Locker(20);
    locker.deposit();
    assertEquals(19, locker.getNumberOfAvailableBoxes());
  }

  @Test
  void shouldReturnFalseWhenDepositNoAvailableBox() {
    Locker locker = new Locker(5);
    for (int i=0;i<5;i++) {
      locker.deposit();
    }
    assertEquals(0, locker.getNumberOfAvailableBoxes());
    boolean result = locker.deposit();
    assertFalse(result);
  }
}
