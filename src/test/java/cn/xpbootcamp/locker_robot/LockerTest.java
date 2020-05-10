package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.InvalidReceiptException;
import cn.xpbootcamp.locker_robot.exception.NoAvailableLockerBoxException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LockerTest {

  @Test
  void should_return_receipt_when_deposit_given_having_available_box() throws NoAvailableLockerBoxException {
    // given
    Locker locker = new Locker(20);

    // when
    Receipt receipt = locker.deposit();

    // then
    assertNotNull(receipt.getReceiptNumber());
  }

  @Test
  void should_throw_exception_when_deposit_given_no_available_box() throws NoAvailableLockerBoxException {
    // given
    Locker locker = new Locker(5);
    for (int i = 0; i < 5; i++) {
      locker.deposit();
    }

    // when & then
    assertThrows(NoAvailableLockerBoxException.class, locker::deposit);
  }

  @Test
  void should_increase_number_of_available_boxes_when_withdraw_given_valid_receipt()
      throws NoAvailableLockerBoxException {
    // given
    Locker locker = new Locker(5);
    Receipt receipt = locker.deposit();
    String receiptNumber = receipt.getReceiptNumber();

    // when & then
    assertDoesNotThrow(() -> locker.withdraw(receipt.getReceiptNumber()));
  }

  @Test
  void should_throw_exception_when_withdraw_given_invalid_receipt()
      throws NoAvailableLockerBoxException {
    // given
    Locker locker = new Locker(5);
    locker.deposit();

    // when & then
    assertThrows(InvalidReceiptException.class,
        () -> locker.withdraw("fake-receipt-number"));
  }
}
