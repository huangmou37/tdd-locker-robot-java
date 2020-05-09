package cn.xpbootcamp.locker_robot;

public class Locker {

  public Locker(int capacity) {
    this.capacity = capacity;
  }

  public int getNumberOfAvailableBoxes() {
    return capacity;
  }

  public boolean deposit() {
    return getNumberOfAvailableBoxes() > 0;
  }

  private int capacity;
}
