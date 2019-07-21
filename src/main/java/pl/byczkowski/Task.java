package pl.byczkowski;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Task implements Runnable {
    @Override
    public void run() {
        File file = new File("C:\\Users\\MateuszByczkowski\\Documents\\SDA\\Programowanie 2\\pesele.txt");

        List<String> peseles = new ArrayList<>();

        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                peseles.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Problem z odczytywaniem z pliku");
            e.printStackTrace();
        }
        List<Boolean> booleans = validatePeseles(peseles);
        displayPeseles(booleans);
    }

    public List<Boolean> validatePeseles(List<String> peseles) {
        List<Boolean> results = new ArrayList<>();
        peseles.forEach(pesel -> {
            int first = Integer.parseInt(String.valueOf(pesel.charAt(0)));
            int second = Integer.parseInt(String.valueOf(pesel.charAt(1)));
            int third = Integer.parseInt(String.valueOf(pesel.charAt(2)));
            int fourth = Integer.parseInt(String.valueOf(pesel.charAt(3)));
            int fifth = Integer.parseInt(String.valueOf(pesel.charAt(4)));
            int sixth = Integer.parseInt(String.valueOf(pesel.charAt(5)));
            int seventh = Integer.parseInt(String.valueOf(pesel.charAt(6)));
            int eight = Integer.parseInt(String.valueOf(pesel.charAt(7)));
            int ninth = Integer.parseInt(String.valueOf(pesel.charAt(8)));
            int tenth = Integer.parseInt(String.valueOf(pesel.charAt(9)));
            int eleventh = Integer.parseInt(String.valueOf(pesel.charAt(10)));

            String result = String.valueOf(first * 1 + second * 3 + third * 7 + fourth * 9 + fifth * 1
                    + sixth * 3 + seventh * 7 + eight * 9 + ninth * 1 + tenth * 3 + eleventh * 1);

            if (result.charAt(result.length() - 1) == '0') {
                results.add(true);
            } else {
                results.add(false);
            }

            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return results;
    }

    private void displayPeseles(List<Boolean> booleans) {
        for (int i = 0; i < booleans.size(); i++) {
            Boolean result = booleans.get(i);
            String peselCorectness;
            if (result) {
                peselCorectness = "poprawny";
            } else {
                peselCorectness = "niepoprawny";
            }
            System.out.println("Pesel numer " + i + " jest " + peselCorectness);
        }
    }
}
