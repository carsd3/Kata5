import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MailListReader {
    List<String> list;

    public MailListReader() {
        this.list = new ArrayList<>();
    }

    public List<String> read(String file) throws FileNotFoundException {
        Scanner reader = new Scanner(new File(file));

        String pattern = "^.+@.+\\..+$";
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            if (line.matches(pattern)) {
                list.add(line);
            }
        }
        return list;
    }
}
