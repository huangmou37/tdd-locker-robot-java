package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.InvalidReceiptException;
import cn.xpbootcamp.locker_robot.exception.LockerIsFullException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LockerRobotTest {

  @Test
  void should_deposit_in_first_locker_given_two_lockers_and_first_locker_is_not_full_when_deposit_package() {
    // given
    int numberOfLocker = 2;
    int capacityOfLocker = 2;
    List<Locker> lockers = IntStream.range(0, numberOfLocker).mapToObj(i -> new Locker(capacityOfLocker))
        .collect(Collectors.toList());
    LockerRobot lockerRobot = new LockerRobot(lockers);
    Locker firstLocker = lockers.get(0);

    // when
    List<Receipt> receipts = new ArrayList<>();
    for (int i = 0; i < capacityOfLocker; i++) {
      receipts.add(lockerRobot.deposit(new UserPackage()));
    }

    // then
    for (int i = 0; i < capacityOfLocker; i++) {
      assertEquals(firstLocker, lockerRobot.findPackageLocation(receipts.get(i)));
    }
  }

  @Test
  void should_deposit_in_second_locker_given_two_lockers_and_first_locker_is_full_when_deposit_package() {
    // given
    int numberOfLocker = 2;
    int capacityOfLocker = 2;
    List<Locker> lockers = IntStream.range(0, numberOfLocker).mapToObj(i -> new Locker(capacityOfLocker))
        .collect(Collectors.toList());
    LockerRobot lockerRobot = new LockerRobot(lockers);
    Locker secondLocker = lockers.get(1);

    for (int i = 0; i < capacityOfLocker; i++) {
      lockerRobot.deposit(new UserPackage());
    }

    // when
    Receipt receipt = lockerRobot.deposit(new UserPackage());

    // then
    assertEquals(secondLocker, lockerRobot.findPackageLocation(receipt));
  }

  @Test
  void should_throw_exception_given_two_lockers_and_both_lockers_are_full_when_deposit_package() {
    // given
    int numberOfLocker = 2;
    int capacityOfLocker = 2;
    List<Locker> lockers = IntStream.range(0, numberOfLocker).mapToObj(i -> new Locker(capacityOfLocker))
        .collect(Collectors.toList());
    LockerRobot lockerRobot = new LockerRobot(lockers);

    for (int i = 0; i < capacityOfLocker * numberOfLocker; i++) {
      lockerRobot.deposit(new UserPackage());
    }

    // when & then
    assertThrows(LockerIsFullException.class, () -> lockerRobot.deposit(new UserPackage()));
  }

  @Test
  void should_return_deposited_package_given_valid_receipt_when_withdraw_package() {
    // given
    int numberOfLocker = 2;
    int capacityOfLocker = 2;
    List<Locker> lockers = IntStream.range(0, numberOfLocker).mapToObj(i -> new Locker(capacityOfLocker))
        .collect(Collectors.toList());
    LockerRobot lockerRobot = new LockerRobot(lockers);
    UserPackage depositedPackage = new UserPackage();
    Receipt receipt = lockerRobot.deposit(depositedPackage);

    // when
    UserPackage withdrawnPackage = lockerRobot.withdraw(receipt);

    // then
    assertEquals(depositedPackage, withdrawnPackage);
  }

  @Test
  void should_throw_exception_given_invalid_receipt_when_withdraw_package() {
    // given
    LockerRobot lockerRobot = new LockerRobot(Collections.singletonList(new Locker(2)));

    // when & then
    assertThrows(InvalidReceiptException.class, () -> lockerRobot.withdraw(new Receipt()));
  }

  @Test
  void should_throw_exception_given_used_receipt_when_withdraw_package() {
    // given
    LockerRobot lockerRobot = new LockerRobot(Collections.singletonList(new Locker(2)));
    Receipt receipt = lockerRobot.deposit(new UserPackage());
    lockerRobot.withdraw(receipt);

    // when & then
    assertThrows(InvalidReceiptException.class, () -> lockerRobot.withdraw(receipt));
  }
}
