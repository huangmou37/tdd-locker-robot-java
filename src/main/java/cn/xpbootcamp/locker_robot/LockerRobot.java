package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.InvalidReceiptException;
import cn.xpbootcamp.locker_robot.exception.LockerIsFullException;
import java.util.List;
import java.util.Optional;

public class LockerRobot {

  private List<Locker> lockers;

  public LockerRobot(List<Locker> lockers) {
    this.lockers = lockers;
  }

  public Receipt deposit(UserPackage userPackage) throws LockerIsFullException {
    return lockers.stream()
        .filter(Locker::isAvailable)
        .findFirst()
        .map(locker -> locker.deposit(userPackage))
        .orElseThrow(LockerIsFullException::new);
  }

  Locker findPackageLocation(Receipt receipt) {
    return lockers.stream().filter(locker -> locker.hasPackage(receipt)).findFirst().orElseThrow(InvalidReceiptException::new);
  }

  public UserPackage withdraw(Receipt receipt) {
    Locker locker = findPackageLocation(receipt);
    return locker.withdraw(receipt.getReceiptNumber());
  }
}
