package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.LockerIsFullException;

import java.util.List;
import java.util.Optional;

public class SmartLockerRobot extends LockerRobot {

  public SmartLockerRobot(List<Locker> lockers) {
    super(lockers);
  }

  @Override
  public Receipt deposit(UserPackage userPackage) {
    Optional<Locker> lockerWithMaxRemaining = lockers.stream()
        .filter(locker -> locker.getRemaining() > 0)
        .reduce((first, second) -> {
          if (first.getRemaining() >= second.getRemaining()) {
            return first;
          }
          return second;
        });
    if (lockerWithMaxRemaining.isPresent()) {
      return lockerWithMaxRemaining.get().deposit(userPackage);
    } else {
      throw new LockerIsFullException();
    }
  }
}
