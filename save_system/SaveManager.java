package save_system;

import java.io.File;
import java.io.FileWriter;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.*;

public class SaveManager {
    static ObjectMapper mapper = new ObjectMapper();
    static final String PATH = "./SAVEITHDATAITH.json";
    private SaveManager()
    {

    }
    public static void saveGame(PlayerData dataToSave){ 
        mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        try {
            // Java objects to JSON string
            String jsonString = mapper.writeValueAsString(dataToSave);
            //System.out.println(jsonString);
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            //String json = ow.writeValueAsString(dataToSave);
            File f = new File(PATH);
            mapper.writeValue(f, dataToSave);
         } catch (Exception e) {
            e.printStackTrace();
         }
    }
    public static PlayerData loadGame(){
        mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

        File f = new File(PATH);
        try{
            return mapper.readValue(f, PlayerData.class);
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
