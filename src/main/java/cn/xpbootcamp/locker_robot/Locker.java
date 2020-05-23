package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.InvalidReceiptException;
import cn.xpbootcamp.locker_robot.exception.LockerIsFullException;

import java.util.HashMap;
import java.util.Map;

public class Locker {

  public Locker(int capacity) {
    this.capacity = capacity;

    receiptToUserPackageMap = new HashMap<>();
  }

  public Receipt deposit(UserPackage userPackage) throws LockerIsFullException {
    if (this.receiptToUserPackageMap.size() < capacity) {
      Receipt receipt = new Receipt();
      receiptToUserPackageMap.put(receipt.getReceiptNumber(), userPackage);
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
      return userPackage;
    } else {
      throw new InvalidReceiptException();
    }
  }

  public int getRemaining() {
    return capacity - receiptToUserPackageMap.size();
  }

  private int capacity;

  private Map<String, UserPackage> receiptToUserPackageMap;

  public boolean hasPackage(Receipt receipt) {
    return receiptToUserPackageMap.containsKey(receipt.getReceiptNumber());
  }

  public boolean isAvailable() {
    return this.capacity > this.receiptToUserPackageMap.size();
  }


}
