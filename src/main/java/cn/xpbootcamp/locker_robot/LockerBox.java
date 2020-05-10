package cn.xpbootcamp.locker_robot;

public class LockerBox {

  public LockerBox() {
    this.available = true;
  }

  public void deposit() {
    this.available = false;
  }

  public void withdraw() {
    this.available = true;
  }

  public boolean isAvailable() {
    return available;
  }

  private boolean available;
}
