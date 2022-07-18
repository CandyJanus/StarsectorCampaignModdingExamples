package com.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

//note: why opencsv? because vanilla SS getSettings allows for reading csvs, and writing text files, but not editing csvs directly.

public class csvModifier {
    public static void main() throws IOException {

        String[] items1 = {"thing1", "thing2", "thing3"};
        String[] items2 = {"eh", "ah", "oh"};

        List<String[]> entries = new ArrayList<>();
        entries.add(items1);
        entries.add(items2);

        String fileName = "data/strings/newStrings.csv";

        try (FileOutputStream CSV_fileoutstream = new FileOutputStream(fileName);
             OutputStreamWriter CSV_outputstream = new OutputStreamWriter(CSV_fileoutstream, StandardCharsets.UTF_8);
             CSVWriter CSV_writer = new CSVWriter(CSV_outputstream)) {

            CSV_writer.writeAll(entries);
        }
    }
}
