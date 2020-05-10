package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.InvalidReceiptException;
import cn.xpbootcamp.locker_robot.exception.NoAvailableLockerBoxException;

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

  public LockerBox deposit() throws NoAvailableLockerBoxException {
    Optional<LockerBox> lockerBox = findNextAvailableBox();
    if (lockerBox.isPresent()) {
      LockerBox selectedBox = lockerBox.get();
      selectedBox.deposit();
      return selectedBox;
    } else {
      throw new NoAvailableLockerBoxException("Locker is full");
    }
  }

  public void withdraw(String receiptNumber) throws InvalidReceiptException {
    Optional<LockerBox> matchedLockerBox = lockerBoxes.stream()
        .filter(box -> box.getReceipt() != null && box.getReceipt().getReceiptNumber().equals(receiptNumber)).findAny();
    if (matchedLockerBox.isPresent()) {
      matchedLockerBox.get().withdraw();
    } else {
      throw new InvalidReceiptException("Receipt is invalid");
    }
  }

  private Optional<LockerBox> findNextAvailableBox() {
    return lockerBoxes.stream().filter(lockerBox -> lockerBox.getReceipt() == null).findAny();
  }

  private int capacity;

  private List<LockerBox> lockerBoxes;
}
