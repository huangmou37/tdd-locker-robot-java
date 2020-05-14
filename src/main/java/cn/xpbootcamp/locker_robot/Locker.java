package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.InvalidReceiptException;
import cn.xpbootcamp.locker_robot.exception.LockerIsFullException;

import java.util.HashMap;
import java.util.Map;

public class Locker {

  public Locker(int capacity) {
    this.capacity = capacity;
    this.occupied = 0;

    userPackageReceiptMap = new HashMap<>();
  }

  public Receipt deposit(UserPackage userPackage) throws LockerIsFullException {
    if (occupied < capacity) {
      Receipt receipt = new Receipt();
      userPackageReceiptMap.put(receipt.getReceiptNumber(), userPackage);
      occupied ++;
      return receipt;
    } else {
      throw new LockerIsFullException();
    }
  }

  public UserPackage withdraw(String receiptNumber) throws InvalidReceiptException {
    if (userPackageReceiptMap.containsKey(receiptNumber)) {
      UserPackage userPackage = userPackageReceiptMap.get(receiptNumber);
      userPackageReceiptMap.remove(receiptNumber);
      occupied --;
      return userPackage;
    } else {
      throw new InvalidReceiptException();
    }
  }

  private int capacity;
  private int occupied;

  private Map<String, UserPackage> userPackageReceiptMap;

  public boolean hasPackage(Receipt receipt) {
    return userPackageReceiptMap.containsKey(receipt.getReceiptNumber());
  }

  public boolean isAvailable() {
    return this.capacity > this.occupied;
  }


}
