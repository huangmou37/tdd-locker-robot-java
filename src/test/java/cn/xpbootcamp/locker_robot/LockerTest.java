package cn.xpbootcamp.locker_robot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LockerTest {

  @Test
  void shouldHasNumberOfAvailableBoxesAsCapacityWhenInitialized() {
    Locker locker = new Locker(20);
    assertEquals(20, locker.getNumberOfAvailableBoxes());
  }
}
