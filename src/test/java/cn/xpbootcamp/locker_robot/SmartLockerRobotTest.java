package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.InvalidReceiptException;
import cn.xpbootcamp.locker_robot.exception.LockerIsFullException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SmartLockerRobotTest {

  @Test
  void should_deposit_in_first_locker_and_return_receipt_when_deposit_given_two_non_full_lockers_and_remaining_of_first_one_is_larger_than_second() {
    // given
    Locker firstLocker = new Locker(2);
    Locker secondLocker = new Locker(1);
    SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(firstLocker, secondLocker));

    // when
    Receipt receipt = smartLockerRobot.deposit(new UserPackage());

    // then
    assertNotNull(receipt);
    assertEquals(firstLocker, smartLockerRobot.findPackageLocation(receipt));
  }

  @Test
  void should_deposit_in_second_locker_and_return_receipt_when_deposit_given_two_non_full_lockers_and_remaining_of_first_one_is_smaller_than_second() {
    // given
    Locker firstLocker = new Locker(1);
    Locker secondLocker = new Locker(2);
    SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(firstLocker, secondLocker));

    // when
    Receipt receipt = smartLockerRobot.deposit(new UserPackage());

    // then
    assertNotNull(receipt);
    assertEquals(secondLocker, smartLockerRobot.findPackageLocation(receipt));
  }

  @Test
  void should_deposit_in_first_or_second_locker_and_return_receipt_when_deposit_given_three_non_full_lockers_and_remaining_of_first_two_are_equal_and_larger_than_the_third() {
    // given
    Locker firstLocker = new Locker(2);
    Locker secondLocker = new Locker(2);
    Locker thirdLocker = new Locker(1);
    SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(firstLocker, secondLocker, thirdLocker));

    // when
    Receipt receipt = smartLockerRobot.deposit(new UserPackage());

    // then
    assertNotNull(receipt);
    assertTrue(smartLockerRobot.findPackageLocation(receipt) == firstLocker
        || smartLockerRobot.findPackageLocation(receipt) == secondLocker);
  }

  @Test
  void should_throw_exception_when_deposit_given_two_full_lockers() {
    // given
    Locker firstLocker = new Locker(1);
    Locker secondLocker = new Locker(1);
    SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(firstLocker, secondLocker));
    smartLockerRobot.deposit(new UserPackage());
    smartLockerRobot.deposit(new UserPackage());

    // when & then
    assertThrows(LockerIsFullException.class, () -> secondLocker.deposit(new UserPackage()));
  }

  @Test
  void should_return_deposited_package_when_withdraw_given_valid_receipt_with_package_in_first_locker() {
    // given
    Locker firstLocker = new Locker(2);
    Locker secondLocker = new Locker(1);
    SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(firstLocker, secondLocker));
    UserPackage userPackage = new UserPackage();
    Receipt receipt = smartLockerRobot.deposit(userPackage);

    // when
    UserPackage result = smartLockerRobot.withdraw(receipt);

    // then
    assertEquals(userPackage, result);
  }

  @Test
  void should_return_deposited_package_when_withdraw_given_valid_receipt_with_package_in_second_locker() {
    // given
    Locker firstLocker = new Locker(1);
    Locker secondLocker = new Locker(2);
    SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(firstLocker, secondLocker));
    UserPackage userPackage = new UserPackage();
    Receipt receipt = smartLockerRobot.deposit(userPackage);

    // when
    UserPackage result = smartLockerRobot.withdraw(receipt);

    // then
    assertEquals(userPackage, result);
  }

  @Test
  void should_throw_exception_when_withdraw_given_invalid_receipt() {
    // given
    Locker firstLocker = new Locker(1);
    Locker secondLocker = new Locker(2);
    SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(firstLocker, secondLocker));

    // when & then
    assertThrows(InvalidReceiptException.class, () -> smartLockerRobot.withdraw(new Receipt()));
  }

  @Test
  void should_throw_exception_when_withdraw_given_used_receipt() {
    // given
    Locker firstLocker = new Locker(1);
    Locker secondLocker = new Locker(2);
    SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(firstLocker, secondLocker));
    Receipt deposit = smartLockerRobot.deposit(new UserPackage());

    // when
    smartLockerRobot.withdraw(deposit);

    // then
    assertThrows(InvalidReceiptException.class, () -> smartLockerRobot.withdraw(deposit));
  }
}