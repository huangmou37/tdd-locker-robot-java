package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.InvalidReceiptException;
import cn.xpbootcamp.locker_robot.exception.LockerIsFullException;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SmartLockerRobotTest {

  @Test
  void should_deposit_in_first_locker_and_return_receipt_when_deposit_given_two_no_full_lockers__and_available_number_of_first_one_is_bigger_than_second() {
    Locker firstLocker = new Locker(2);
    Locker secondLocker = new Locker(1);
    SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(firstLocker, secondLocker));

    Receipt receipt = smartLockerRobot.deposit(new UserPackage());

    assertNotNull(receipt);
    assertEquals(firstLocker, smartLockerRobot.findPackageLocation(receipt));
  }

  @Test
  void should_deposit_in_second_locker_and_return_receipt_when_deposit_given_two_no_full_lockers_and_available_number_of_first_one_is_less_than_second() {
    Locker firstLocker = new Locker(1);
    Locker secondLocker = new Locker(2);
    SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(firstLocker, secondLocker));

    Receipt receipt = smartLockerRobot.deposit(new UserPackage());

    assertNotNull(receipt);
    assertEquals(secondLocker, smartLockerRobot.findPackageLocation(receipt));
  }

  @Test
  void should_deposit_in_first_or_second_locker_and_return_receipt_when_deposit_given_three_no_full_lockers_and_available_number_of_first_two_are_both_and_is_more_than_the_second() {
    Locker firstLocker = new Locker(2);
    Locker secondLocker = new Locker(2);
    Locker ThirdLocker = new Locker(1);
    SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(firstLocker, secondLocker));

    Receipt receipt = smartLockerRobot.deposit(new UserPackage());

    assertNotNull(receipt);
    assertTrue(smartLockerRobot.findPackageLocation(receipt) == firstLocker || smartLockerRobot.findPackageLocation(receipt) == secondLocker);
  }

  @Test
  void should_throw_exception_when_deposit_given_two_lockers_and_available_number_of_both_locker_are_zero() {
    Locker firstLocker = new Locker(0);
    Locker secondLocker = new Locker(0);
    SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(firstLocker, secondLocker));

    assertThrows(LockerIsFullException.class, () -> secondLocker.deposit(new UserPackage()));
  }

  @Test
  void should_return_deposited_package_when_withdraw_given_valid_receipt_with_package_in_first_locker() {

    Locker firstLocker = new Locker(2);
    Locker secondLocker = new Locker(1);
    SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(firstLocker, secondLocker));
    UserPackage userPackage = new UserPackage();
    Receipt receipt = smartLockerRobot.deposit(userPackage);

    UserPackage result = smartLockerRobot.withdraw(receipt);

    assertEquals(userPackage, result);
  }

  @Test
  void should_return_deposited_package_when_withdraw_given_valid_receipt_with_package_in_second_locker() {

    Locker firstLocker = new Locker(1);
    Locker secondLocker = new Locker(2);
    SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(firstLocker, secondLocker));
    UserPackage userPackage = new UserPackage();
    Receipt receipt = smartLockerRobot.deposit(userPackage);

    UserPackage result = smartLockerRobot.withdraw(receipt);

    assertEquals(userPackage, result);
  }

  @Test
  void should_throw_exception_when_withdraw_given_invalid_receipt() {

    Locker firstLocker = new Locker(1);
    Locker secondLocker = new Locker(2);
    SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(firstLocker, secondLocker));

    assertThrows(InvalidReceiptException.class, () -> smartLockerRobot.withdraw(new Receipt()));
  }

  @Test
  void should_throw_exception_when_withdraw_given_used_receipt() {

    Locker firstLocker = new Locker(1);
    Locker secondLocker = new Locker(2);
    SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(firstLocker, secondLocker));
    Receipt deposit = smartLockerRobot.deposit(new UserPackage());
    smartLockerRobot.withdraw(deposit);

    assertThrows(InvalidReceiptException.class, () -> smartLockerRobot.withdraw(deposit));
  }


}