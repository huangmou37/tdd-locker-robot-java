package cn.xpbootcamp.locker_robot;

public class Receipt {

  public Receipt() {
    enabled = true;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void disable() {
    enabled = false;
  }

  private boolean enabled;
}
