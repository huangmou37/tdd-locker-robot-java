package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.InvalidReceiptException;
import cn.xpbootcamp.locker_robot.exception.LockerIsFullException;

import java.util.HashMap;
import java.util.Map;

public class Locker {

  public Locker(int capacity) {
    this.capacity = capacity;
    this.occupied = 0;

    receiptToUserPackageMap = new HashMap<>();
  }

  public Receipt deposit(UserPackage userPackage) throws LockerIsFullException {
    if (occupied < capacity) {
      Receipt receipt = new Receipt();
      receiptToUserPackageMap.put(receipt.getReceiptNumber(), userPackage);
      occupied ++;
      return receipt;
    } else {
      throw new LockerIsFullException();
    }
  }

  public UserPackage withdraw(Receipt receipt) throws InvalidReceiptException {
    String receiptNumber = receipt.getReceiptNumber();
    if (receiptToUserPackageMap.containsKey(receiptNumber)) {
      UserPackage userPackage = receiptToUserPackageMap.get(receiptNumber);
      receiptToUserPackageMap.remove(receiptNumber);
      occupied --;
      return userPackage;
    } else {
      throw new InvalidReceiptException();
    }
  }

  private int capacity;
  private int occupied;

  private Map<String, UserPackage> receiptToUserPackageMap;

  public boolean hasPackage(Receipt receipt) {
    return receiptToUserPackageMap.containsKey(receipt.getReceiptNumber());
  }

  public boolean isAvailable() {
    return this.capacity > this.occupied;
  }


}
