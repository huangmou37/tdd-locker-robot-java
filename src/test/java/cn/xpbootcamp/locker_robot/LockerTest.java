package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.InvalidReceiptException;
import cn.xpbootcamp.locker_robot.exception.LockerIsFullException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LockerTest {

  @Test
  void should_return_receipt_when_deposit_given_locker_is_not_full() throws LockerIsFullException {
    // given
    Locker locker = new Locker(20);

    // when
    Receipt receipt = locker.deposit(new UserPackage());

    // then
    assertNotNull(receipt.getReceiptNumber());
  }

  @Test
  void should_throw_exception_when_deposit_given_locker_is_full() throws LockerIsFullException {
    // given
    Locker locker = new Locker(5);
    for (int i = 0; i < 5; i++) {
      locker.deposit(new UserPackage());
    }

    // when & then
    assertThrows(LockerIsFullException.class, () -> locker.deposit(new UserPackage()));
  }

  @Test
  void should_return_deposited_package_when_withdraw_given_valid_receipt()
      throws LockerIsFullException, InvalidReceiptException {
    // given
    Locker locker = new Locker(5);
    UserPackage packageDeposited = new UserPackage();
    Receipt receipt = locker.deposit(packageDeposited);
    String receiptNumber = receipt.getReceiptNumber();

    // when & then
    assertEquals(packageDeposited, locker.withdraw(receipt));
  }

  @Test
  void should_throw_exception_when_withdraw_given_invalid_receipt()
      throws LockerIsFullException {
    // given
    Locker locker = new Locker(5);
    locker.deposit(new UserPackage());

    // when & then
    assertThrows(InvalidReceiptException.class,
        () -> locker.withdraw(new Receipt()));
  }
}
