package cn.xpbootcamp.locker_robot;

public class Locker {

  public Locker(int capacity) {
    this.capacity = capacity;
  }

  public int getNumberOfAvailableBoxes() {
    return capacity;
  }

  private int capacity;
}
