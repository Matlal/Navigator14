package se.ltu.student.navigator.navigator14.json;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
    Gson gson;

    public Parser() {
        gson = new Gson();
    }

    public Object parseFile(File f, Class output) throws IOException {
        // Pretty sure this shits itself on large files. How do we solve that?
        // TODO: Fix potential memory issues in the parser.
        BufferedReader in = new BufferedReader(new FileReader(f));
        StringBuilder s = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            s.append(line);
        }
        return parse(s.toString(), output);
    }

    public Object parse(String json, Class output) {
        try {
            return gson.fromJson(json, output);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            Log.wtf("JSON.Parser","Malformed string? Have a look: \n"+json);
            return null;
        }
    }
}