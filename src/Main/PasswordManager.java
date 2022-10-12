package Main;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PasswordManager {
    public Map<String, Entry> map;

    Console console = new Console();

    public PasswordManager(String path) {
        File entries = new File(path);
        if (entries.exists()) {
            try {
                FileReader fileReader = new FileReader(entries);
                Gson gson = new Gson();
                TypeToken<Map<String, Entry>> mapType = new TypeToken<>() {};
                map = gson.fromJson(fileReader, mapType.getType());
            } catch (Exception e) {
                map = new HashMap<>();
                e.printStackTrace();
            }
        } else {
            map = new HashMap<>();
        }
    }

    public void add(String readWebsite, String readUserName, String readPassword) {
        Entry entry = new Entry(readWebsite, readUserName, readPassword);
        map.put(entry.website, entry);
    }

    public void view(String website) {
        if (map.containsKey(website)) {
            var entry = map.get(website);
            console.output(MessageFormat.format("website:  {0}\nusername: {1}\npassword: {2}\n",
                    entry.website,
                    entry.userName,
                    entry.password));
        }else console.output("No data for that website exists\n");
    }

    public void fullList() {
            for (var entry : map.values()) {
                console.output(MessageFormat.format("website:  {0}\nusername: {1}\npassword: {2}\n",
                        entry.website,
                        entry.userName,
                        entry.password));
            }
    }

    public void delete(String readWebsite) {
        map.remove(readWebsite);
    }

    public void save() {
        try {
            File entries = new File("/tmp/entries.json");
            FileWriter fileWriter = new FileWriter(entries);
            Gson gson = new Gson();
            gson.toJson(this.map, fileWriter);
            fileWriter.close();
            console.output("Changes saved\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
