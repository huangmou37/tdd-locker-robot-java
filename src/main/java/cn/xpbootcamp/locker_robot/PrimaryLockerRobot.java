package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.LockerIsFullException;
import java.util.List;

public class PrimaryLockerRobot extends AbstractLockerRobot {

  public PrimaryLockerRobot(List<Locker> lockers) {
    super(lockers);
  }

  @Override
  protected Locker getLocker() {
    return lockers.stream()
        .filter(Locker::isAvailable)
        .findFirst()
        .orElseThrow(LockerIsFullException::new);
  }

}
