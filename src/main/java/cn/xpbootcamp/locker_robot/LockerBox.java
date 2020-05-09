package cn.xpbootcamp.locker_robot;

public class LockerBox {

  private Receipt receipt;

  public Receipt getReceipt() {
    return receipt;
  }

  public void deposit() {
    this.receipt = new Receipt();
  }

  public void closeDoor() {
    if (receipt != null && !receipt.isEnabled()) {
      receipt = null;
    }
  }
}
