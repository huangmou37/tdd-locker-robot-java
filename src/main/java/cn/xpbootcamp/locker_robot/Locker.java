package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.InvalidReceiptException;
import cn.xpbootcamp.locker_robot.exception.NoAvailableLockerBoxException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Locker {

  public Locker(int capacity) {
    this.capacity = capacity;

    lockerBoxes = IntStream.range(0, capacity).mapToObj(i -> new LockerBox()).collect(Collectors.toList());
    lockerBoxReceiptMap = new HashMap<>();
  }

  public Receipt deposit() throws NoAvailableLockerBoxException {
    Optional<LockerBox> lockerBox = findNextAvailableBox();
    if (lockerBox.isPresent()) {
      LockerBox selectedBox = lockerBox.get();
      selectedBox.deposit();
      Receipt receipt = new Receipt();
      lockerBoxReceiptMap.put(receipt.getReceiptNumber(), selectedBox);
      return receipt;
    } else {
      throw new NoAvailableLockerBoxException();
    }
  }

  public void withdraw(String receiptNumber) throws InvalidReceiptException {
    if (lockerBoxReceiptMap.containsKey(receiptNumber)) {
      LockerBox lockerBox = lockerBoxReceiptMap.get(receiptNumber);
      lockerBox.withdraw();
      lockerBoxReceiptMap.remove(receiptNumber);
    } else {
      throw new InvalidReceiptException();
    }
  }

  private Optional<LockerBox> findNextAvailableBox() {
    return lockerBoxes.stream().filter(LockerBox::isAvailable).findAny();
  }

  private int capacity;

  private List<LockerBox> lockerBoxes;

  private Map<String, LockerBox> lockerBoxReceiptMap;
}
