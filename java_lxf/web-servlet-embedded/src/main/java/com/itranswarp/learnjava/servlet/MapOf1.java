import java.util.Map;
public class MapOf1 {
  public static void main(String[] args) {
	Map<Integer, String> map = Map.of(101, "PP", 102, "QQ", 103, "RR");
	map.forEach((k, v) -> System.out.println(k + " - " + v));
  }
} 
