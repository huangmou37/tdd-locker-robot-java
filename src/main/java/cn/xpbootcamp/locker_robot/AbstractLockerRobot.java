package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.InvalidReceiptException;
import cn.xpbootcamp.locker_robot.exception.LockerIsFullException;
import java.util.List;

public abstract class AbstractLockerRobot {

  protected List<Locker> lockers;

  public AbstractLockerRobot(List<Locker> lockers) {
    this.lockers = lockers;
  }

  public Receipt deposit(UserPackage userPackage) throws LockerIsFullException {
    Locker locker = getLocker();
    return locker.deposit(userPackage);
  }

  protected abstract Locker getLocker();

  Locker findPackageLocation(Receipt receipt) {
    return lockers.stream().filter(locker -> locker.hasPackage(receipt)).findFirst().orElseThrow(InvalidReceiptException::new);
  }

  public UserPackage withdraw(Receipt receipt) {
    Locker locker = findPackageLocation(receipt);
    return locker.withdraw(receipt);
  }
}
