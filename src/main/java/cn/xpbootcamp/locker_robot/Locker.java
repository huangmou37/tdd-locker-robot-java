package cn.xpbootcamp.locker_robot;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Locker {

  public Locker(int capacity) {
    this.capacity = capacity;

    lockerBoxes = IntStream.range(0, capacity).mapToObj(i -> new LockerBox()).collect(Collectors.toList());
  }

  public int getNumberOfAvailableBoxes() {
    return (int) lockerBoxes.stream().filter(lockerBox -> lockerBox.getReceipt() == null).count();
  }

  public Optional<LockerBox> findNextAvailableBox() {
    return lockerBoxes.stream().filter(lockerBox -> lockerBox.getReceipt() == null).findAny();
  }

  public boolean deposit() {
    Optional<LockerBox> lockerBox = findNextAvailableBox();
    if (lockerBox.isPresent()) {
      lockerBox.get().deposit();
      return true;
    } else {
      return false;
    }
  }

  private int capacity;

  private List<LockerBox> lockerBoxes;
}
