package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.LockerIsFullException;
import java.util.List;

public class SmartLockerRobot extends AbstractLockerRobot {

  public SmartLockerRobot(List<Locker> lockers) {
    super(lockers);
  }

  @Override
  protected Locker getLocker() {
    return lockers.stream()
        .filter(locker -> locker.getRemaining() > 0)
        .reduce((first, second) -> {
          if (first.getRemaining() >= second.getRemaining()) {
            return first;
          }
          return second;
        }).orElseThrow(LockerIsFullException::new);
  }
}
