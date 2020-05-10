package cn.xpbootcamp.locker_robot;

import java.util.UUID;

public class Receipt {

  public Receipt() {
    receiptNumber = UUID.randomUUID().toString();
  }

  public String getReceiptNumber() {
    return receiptNumber;
  }

  private String receiptNumber;

}
