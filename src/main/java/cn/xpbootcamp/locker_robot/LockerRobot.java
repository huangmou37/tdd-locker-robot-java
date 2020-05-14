package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.LockerIsFullException;
import java.util.List;
import java.util.Optional;

public class LockerRobot {

  private List<Locker> lockers;

  public LockerRobot(List<Locker> lockers) {
    this.lockers = lockers;
  }

  public Receipt deposit(UserPackage userPackage) throws LockerIsFullException {
    Optional<Locker> firstAvailableLocker = lockers.stream().filter(Locker::isAvailable).findFirst();
    return firstAvailableLocker.get().deposit(userPackage);
  }

  public Locker findPackageLocation(Receipt receipt) {
    return lockers.stream().filter(locker -> locker.hasPackage(receipt)).findFirst().get();
  }

  public UserPackage withdraw(Receipt receipt) {
    return null;
  }
}
