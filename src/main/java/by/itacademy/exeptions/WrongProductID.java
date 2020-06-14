package by.itacademy.exeptions;

public class WrongProductID extends Throwable {

  public int wrongID;

  public WrongProductID(int id) {
    wrongID = id;
  }

  public WrongProductID(String message) {
    super(message);
  }

  public void printMessage() {
    if (wrongID == 0) {
      System.out
          .println("Вы ввели недопустимые символы \"" + getMessage() + "\" введите только цифры!");
    } else {
      System.out.println("Билет c номером " + wrongID + " не доступен для продажи!");
    }
  }

}
