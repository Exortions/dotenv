package io.github.exortions.dotenv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Exortions
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class DotEnv {

    public final String separator = "=";
    public final File file;

    private final HashMap<String, String> keysValues;

    public DotEnv(File file) {
        this.file = file;

        keysValues = new HashMap<>();
    }

    public void loadParams() {
        if (!file.exists()) System.err.println("Couldn't find .env file: " + file.getAbsolutePath()); else System.out.println("Loading .env file " + file.getAbsolutePath() + "...");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));

            while (true) {
                String ln = br.readLine();
                if (ln == null) break;
                String[] strs = get(ln);
                if (strs == null) break;
                keysValues.put(strs[0], strs[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Success! All keys have been cached.");
        }
    }

    private String[] get(String ln) {
        if (!ln.contains(separator)) return null;
        if (ln.startsWith("#")) return null;
        String[] parts = ln.split(separator);

        if (parts.length != 2) return null;

        String[] strings = new String[2];
        strings[0] = parts[0];
        strings[1] = parts[1];
        return strings;
    }

    public String getParameter(String parameter) throws EnvParameterNotFoundException {
        if (keysValues.get(parameter) == null) throw new EnvParameterNotFoundException("Could not find parameter '" + parameter + "'"); else return keysValues.get(parameter);
    }

}
