package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.LockerIsFullException;

import java.util.List;

public class SuperLockerRobot extends AbstractLockerRobot {

  public SuperLockerRobot(List<Locker> asList) {
    super(asList);
  }

  @Override
  protected Locker getLocker() {
    return lockers.stream().filter(locker -> locker.getRemaining() > 0)
        .reduce((a, b) -> {
          if (a.getVacancyRate() >= b.getVacancyRate()) {
            return a;
          }
          return b;
        }).orElseThrow(LockerIsFullException::new);
  }
}
