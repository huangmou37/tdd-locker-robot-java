package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.InvalidReceiptException;
import cn.xpbootcamp.locker_robot.exception.LockerIsFullException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PrimaryLockerRobotTest {

  @Test
  void should_deposit_in_first_locker_when_deposit_package_given_two_lockers_and_first_locker_is_not_full() {
    // given
    List<Locker> lockers = createLockers(2, 2);
    PrimaryLockerRobot lockerRobot = new PrimaryLockerRobot(lockers);

    UserPackage userPackage = new UserPackage();

    // when
    Receipt receipt = lockerRobot.deposit(userPackage);

    // then
    Locker firstLocker = lockers.get(0);
    assertEquals(firstLocker, lockerRobot.findPackageLocation(receipt));
  }

  @Test
  void should_deposit_in_second_locker_when_deposit_package_given_two_lockers_and_first_locker_is_full() {
    // given
    List<Locker> lockers = createLockers(2, 2);
    PrimaryLockerRobot lockerRobot = new PrimaryLockerRobot(lockers);
    fullFillLocker(lockerRobot, lockers.get(0));

    // when
    Receipt receipt = lockerRobot.deposit(new UserPackage());

    // then
    Locker secondLocker = lockers.get(1);
    assertEquals(secondLocker, lockerRobot.findPackageLocation(receipt));
  }

  @Test
  void should_throw_exception_when_deposit_package_given_two_lockers_and_both_lockers_are_full() {
    // given
    List<Locker> lockers = createLockers(2, 2);
    PrimaryLockerRobot lockerRobot = new PrimaryLockerRobot(lockers);
    fullFillLocker(lockerRobot, lockers.get(0));
    fullFillLocker(lockerRobot, lockers.get(1));

    // when & then
    assertThrows(LockerIsFullException.class, () -> lockerRobot.deposit(new UserPackage()));
  }

  @Test
  void should_return_deposited_package_when_withdraw_package_given_valid_receipt_with_package_in_first_locker() {
    // given
    List<Locker> lockers = createLockers(2, 2);
    PrimaryLockerRobot lockerRobot = new PrimaryLockerRobot(lockers);
    UserPackage depositedPackage = new UserPackage();
    Receipt receipt = lockerRobot.deposit(depositedPackage);

    // when
    UserPackage withdrawnPackage = lockerRobot.withdraw(receipt);

    // then
    assertEquals(depositedPackage, withdrawnPackage);
  }

  @Test
  void should_return_deposited_package_when_withdraw_package_given_valid_receipt_with_package_in_second_locker() {
    // given
    List<Locker> lockers = createLockers(2, 2);
    PrimaryLockerRobot lockerRobot = new PrimaryLockerRobot(lockers);
    fullFillLocker(lockerRobot, lockers.get(0));

    UserPackage depositedPackage = new UserPackage();
    Receipt receipt = lockerRobot.deposit(depositedPackage);

    // when
    UserPackage withdrawnPackage = lockerRobot.withdraw(receipt);

    // then
    assertEquals(depositedPackage, withdrawnPackage);
  }

  @Test
  void should_throw_exception_when_withdraw_package_given_invalid_receipt() {
    // given
    PrimaryLockerRobot lockerRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(2)));

    // when & then
    assertThrows(InvalidReceiptException.class, () -> lockerRobot.withdraw(new Receipt()));
  }

  @Test
  void should_throw_exception_when_withdraw_package_given_used_receipt() {
    // given
    PrimaryLockerRobot lockerRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(2)));
    Receipt receipt = lockerRobot.deposit(new UserPackage());
    lockerRobot.withdraw(receipt);

    // when & then
    assertThrows(InvalidReceiptException.class, () -> lockerRobot.withdraw(receipt));
  }

  private static List<Locker> createLockers(int numberOfLocker, int capacityOfLocker) {
    return IntStream.range(0, numberOfLocker).mapToObj(i -> new Locker(capacityOfLocker))
        .collect(Collectors.toList());
  }

  private static void fullFillLocker(PrimaryLockerRobot lockerRobot, Locker locker) {
    while(locker.isAvailable()) {
      lockerRobot.deposit(new UserPackage());
    }
  }
}
