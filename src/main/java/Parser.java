import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    public static void main(String[] args) throws IOException {
        FileReader file = new FileReader("E:\\Studia\\semestr VI\\KSR\\Zadanie2\\src\\main\\java\\adult.data.txt");
        BufferedReader reader = new BufferedReader(file);


        String object = "";
        List<Entity> listOfObjects = new ArrayList<Entity>();
        object = reader.readLine();
        while (object != null) {
            List<String> list = Arrays.asList(object.split(", "));
            Entity newEntity = new Entity(Integer.parseInt(list.get(0)), list.get(1), Integer.parseInt(list.get(2)),
                    list.get(3), Integer.parseInt(list.get(4)), list.get(5),
                    list.get(6), list.get(7), list.get(8), list.get(9), Integer.parseInt(list.get(10)),
                    Integer.parseInt(list.get(11)), Integer.parseInt(list.get(12)), list.get(13), list.get(14));
            listOfObjects.add(newEntity);
            System.out.println(newEntity.toString());
            object = reader.readLine();
        }
        System.out.print(listOfObjects);
    }
}
