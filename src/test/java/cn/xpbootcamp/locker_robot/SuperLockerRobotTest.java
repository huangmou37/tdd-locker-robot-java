package cn.xpbootcamp.locker_robot;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}