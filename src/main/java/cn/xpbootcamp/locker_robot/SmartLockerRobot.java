package cn.xpbootcamp.locker_robot;

import java.util.List;

public class SmartLockerRobot {

  private List<Locker> lockers;

  public SmartLockerRobot(List<Locker> lockers) {
    this.lockers = lockers;
  }

  public Receipt deposit(UserPackage userPackage) {
    return null;
  }

  public Locker findPackageLocation(Receipt receipt) {
    return null;
  }

  public UserPackage withdraw(Receipt receipt) {
    return null;
  }
}
