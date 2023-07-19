package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {
//    static String[] courses = {"Краткая история феминизма", "Песни русской эмиграции"};
//    public static String getRandomItemFromArray(String[] values){
//        int index = getRandomInt(0, values.length   - 1);
//        return values[index];
//    }
//    public static int getRandomInt(int min, int max) {
//        return ThreadLocalRandom.current().nextInt(min, max + 1);
//    }
//    public static String getRandomCourse(){
//        return getRandomItemFromArray(courses);
//    }
public static String getRandomValueFromCSV(String csvFile) {
    List<String[]> data = readCSV(csvFile);

    if (data != null && data.size() > 0) {
        String[] randomRow = getRandomRow(data);
        if (randomRow != null && randomRow.length > 0) {
            String randomValue = getRandomValue(randomRow);
            return randomValue;
        } else {
            return "No data found in the CSV file.";
        }
    } else {
        return "Failed to read the CSV file.";
    }
}

    private static List<String[]> readCSV(String csvFile) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                data.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return data;
    }

    private static String[] getRandomRow(List<String[]> data) {
        if (data != null && data.size() > 0) {
            Random random = new Random();
            int randomIndex = random.nextInt(data.size());
            return data.get(randomIndex);
        }
        return null;
    }

    private static String getRandomValue(String[] row) {
        if (row != null && row.length > 0) {
            Random random = new Random();
            int randomIndex = random.nextInt(row.length);
            return row[randomIndex];
        }
        return null;
    }
}
