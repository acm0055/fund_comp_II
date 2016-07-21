import java.util.SortedSet;

public class Test {
   public static void main(String[] args) {
      Extractor e = new Extractor("input100.txt");
      StdDraw.setScale(0, 30000);
      e.drawLines();
   }
}