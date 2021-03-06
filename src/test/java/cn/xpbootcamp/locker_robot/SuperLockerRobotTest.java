package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.InvalidReceiptException;
import cn.xpbootcamp.locker_robot.exception.LockerIsFullException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SuperLockerRobotTest {

  @Test
  void should_deposit_in_first_locker_and_return_receipt_when_deposit_given_two_non_full_lockers_and_first_has_higher_vacancy_rate_and_larger_remaining() {
    // given
    Locker firstLocker = new Locker(3);
    Locker secondLocker = new Locker(2);
    SuperLockerRobot superLockerRobot = new SuperLockerRobot(Arrays.asList(firstLocker, secondLocker));
    firstLocker.deposit(new UserPackage());
    secondLocker.deposit(new UserPackage());

    // when
    UserPackage depositedPackage = new UserPackage();
    Receipt receipt = superLockerRobot.deposit(depositedPackage);

    // then
    assertNotNull(receipt);
    assertEquals(depositedPackage, firstLocker.withdraw(receipt));
  }

  @Test
  void should_deposit_in_first_locker_and_return_receipt_when_deposit_given_two_non_full_lockers_and_first_has_higher_vacancy_rate_but_smaller_remaining() {
    // given
    Locker firstLocker = new Locker(1);
    Locker secondLocker = new Locker(3);
    SuperLockerRobot superLockerRobot = new SuperLockerRobot(Arrays.asList(firstLocker, secondLocker));
    secondLocker.deposit(new UserPackage());

    // when
    UserPackage depositedPackage = new UserPackage();
    Receipt receipt = superLockerRobot.deposit(depositedPackage);

    // then
    assertNotNull(receipt);
    assertEquals(depositedPackage, firstLocker.withdraw(receipt));
  }

  @Test
  void should_deposit_in_second_locker_and_return_receipt_when_deposit_given_two_non_full_lockers_and_second_has_higher_vacancy_rate_and_larger_remaining() {
    // given
    Locker firstLocker = new Locker(2);
    Locker secondLocker = new Locker(3);
    SuperLockerRobot superLockerRobot = new SuperLockerRobot(Arrays.asList(firstLocker, secondLocker));
    firstLocker.deposit(new UserPackage());
    secondLocker.deposit(new UserPackage());

    // when
    UserPackage depositedPackage = new UserPackage();
    Receipt receipt = superLockerRobot.deposit(depositedPackage);

    // then
    assertNotNull(receipt);
    assertEquals(depositedPackage, secondLocker.withdraw(receipt));
  }

  @Test
  void should_deposit_in_second_locker_and_return_receipt_when_deposit_given_two_non_full_lockers_and_second_has_higher_vacancy_rate_but_smaller_remaining() {
    // given
    Locker firstLocker = new Locker(3);
    Locker secondLocker = new Locker(1);
    SuperLockerRobot superLockerRobot = new SuperLockerRobot(Arrays.asList(firstLocker, secondLocker));
    firstLocker.deposit(new UserPackage());

    // when
    UserPackage depositedPackage = new UserPackage();
    Receipt receipt = superLockerRobot.deposit(depositedPackage);

    // then
    assertNotNull(receipt);
    assertEquals(depositedPackage, secondLocker.withdraw(receipt));
  }

  @Test
  void should_deposit_in_first_or_second_locker_and_return_receipt_when_deposit_given_three_non_full_lockers_and_vacancy_rate_of_first_two_are_equal_and_larger_than_the_third() {
    // given
    Locker firstLocker = new Locker(3);
    Locker secondLocker = new Locker(3);
    Locker thirdLocker = new Locker(2);
    SuperLockerRobot superLockerRobot = new SuperLockerRobot(Arrays.asList(firstLocker, secondLocker, thirdLocker));
    firstLocker.deposit(new UserPackage());
    secondLocker.deposit(new UserPackage());
    thirdLocker.deposit(new UserPackage());

    // when
    Receipt receipt = superLockerRobot.deposit(new UserPackage());

    // then
    assertNotNull(receipt);
    assertThrows(InvalidReceiptException.class, () -> thirdLocker.withdraw(receipt));
  }

  @Test
  void should_throw_exception_when_deposit_given_two_full_lockers() {
    // given
    Locker firstLocker = new Locker(1);
    Locker secondLocker = new Locker(1);
    SuperLockerRobot superLockerRobot = new SuperLockerRobot(Arrays.asList(firstLocker, secondLocker));
    superLockerRobot.deposit(new UserPackage());
    superLockerRobot.deposit(new UserPackage());

    // when & then
    assertThrows(LockerIsFullException.class, () -> superLockerRobot.deposit(new UserPackage()));
  }

  @Test
  void should_return_deposited_package_when_withdraw_given_valid_receipt_with_package_in_first_locker() {
    // given
    Locker firstLocker = new Locker(2);
    Locker secondLocker = new Locker(1);
    SuperLockerRobot superLockerRobot = new SuperLockerRobot(Arrays.asList(firstLocker, secondLocker));

    UserPackage userPackage = new UserPackage();
    Receipt receipt = superLockerRobot.deposit(userPackage);

    // when
    UserPackage result = superLockerRobot.withdraw(receipt);

    // then
    assertEquals(userPackage, result);
  }

  @Test
  void should_throw_exception_when_withdraw_given_invalid_receipt() {
    // given
    Locker firstLocker = new Locker(1);
    Locker secondLocker = new Locker(2);
    SuperLockerRobot superLockerRobot = new SuperLockerRobot(Arrays.asList(firstLocker, secondLocker));

    // when & then
    assertThrows(InvalidReceiptException.class, () -> superLockerRobot.withdraw(new Receipt()));
  }
}