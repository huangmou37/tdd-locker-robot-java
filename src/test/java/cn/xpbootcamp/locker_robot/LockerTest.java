package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.InvalidReceiptException;
import cn.xpbootcamp.locker_robot.exception.NoAvailableLockerBoxException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LockerTest {

  @Test
  void should_has_number_of_available_boxes_as_capacity_when_initialized() {
    Locker locker = new Locker(20);
    assertEquals(20, locker.getNumberOfAvailableBoxes());
  }

  @Test
  void should_return_box_and_decrease_number_of_available_boxes_when_deposit_given_having_available_box() throws NoAvailableLockerBoxException {
    Locker locker = new Locker(20);
    LockerBox box = locker.deposit();
    assertNotNull(box);
    assertNotNull(box.getReceipt());
    assertEquals(19, locker.getNumberOfAvailableBoxes());
  }

  @Test
  void should_throw_exception_when_deposit_given_no_available_box() throws NoAvailableLockerBoxException {
    Locker locker = new Locker(5);
    for (int i = 0; i < 5; i++) {
      locker.deposit();
    }
    assertEquals(0, locker.getNumberOfAvailableBoxes());
    NoAvailableLockerBoxException exception = assertThrows(NoAvailableLockerBoxException.class, locker::deposit);
    assertEquals("Locker is full", exception.getMessage());
  }

  @Test
  void should_increase_number_of_available_boxes_when_withdraw_given_valid_receipt()
      throws NoAvailableLockerBoxException, InvalidReceiptException {
    Locker locker = new Locker(5);
    LockerBox box = locker.deposit();
    assertEquals(4, locker.getNumberOfAvailableBoxes());
    Receipt receipt = box.getReceipt();
    locker.withdraw(receipt.getReceiptNumber());
    assertEquals(5, locker.getNumberOfAvailableBoxes());
  }

  @Test
  void should_throw_exception_when_withdraw_given_invalid_receipt()
      throws NoAvailableLockerBoxException {
    Locker locker = new Locker(5);
    LockerBox box = locker.deposit();
    assertEquals(4, locker.getNumberOfAvailableBoxes());
    Receipt receipt = box.getReceipt();
    InvalidReceiptException exception = assertThrows(InvalidReceiptException.class,
        () -> locker.withdraw("fake-receipt-number"));
    assertEquals("Receipt is invalid", exception.getMessage());
  }
}
