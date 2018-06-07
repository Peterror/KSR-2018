import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    public static void main(String[] args) throws IOException {
        FileReader file = new FileReader("database.dat");
        BufferedReader reader = new BufferedReader(file);
        String object = "";
        List<Entity> listOfObjects = new ArrayList<Entity>();
        object = reader.readLine();
        for (int i=0;i<10000;i++)
        {
            List<String> list = Arrays.asList(object.split(", "));
            Entity newEntity = new Entity(Integer.parseInt(list.get(0)), Integer.parseInt(list.get(1)),
                    Integer.parseInt(list.get(2)), Integer.parseInt(list.get(3)), Integer.parseInt(list.get(4)),
                    Double.parseDouble(list.get(5)), Double.parseDouble(list.get(6)), Double.parseDouble(list.get(7)),
                    Double.parseDouble(list.get(8)), Double.parseDouble(list.get(9)), Double.parseDouble(list.get(10)),
                    list.get(11), Double.parseDouble(list.get(12)), Double.parseDouble(list.get(13)),
                    list.get(14),Double.parseDouble(list.get(15)),Double.parseDouble(list.get(16)),
                    Double.parseDouble(list.get(17)),Double.parseDouble(list.get(18)),Double.parseDouble(list.get(19)),
                    Double.parseDouble(list.get(20)),Double.parseDouble(list.get(21)),Double.parseDouble(list.get(22)),
                    Double.parseDouble(list.get(23)),Double.parseDouble(list.get(24)),Double.parseDouble(list.get(25)),
                    Double.parseDouble(list.get(26)),Integer.parseInt(list.get(27)),Integer.parseInt(list.get(28)),
                    Double.parseDouble(list.get(29)),Integer.parseInt(list.get(30)));
            listOfObjects.add(newEntity);
            System.out.println(newEntity.toString());
            object = reader.readLine();
        }
        System.out.print(listOfObjects);
    }
}
