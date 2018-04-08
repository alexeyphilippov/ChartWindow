package projectspace;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtils {
    public static void delete(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        } else {
            System.out.println("No such file");
        }
    }

    public static void copy(String source, String dest) {
        File sourceFile = new File(source);
        File destFile = new File(dest);
        StringBuilder stringBuilder = new StringBuilder();

        String line = null;
        if (sourceFile.exists()) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(sourceFile))) {
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);


        if (!destFile.exists()) {
            try (FileWriter fileWriter = new FileWriter(destFile)) {
                destFile.createNewFile();

                String[] strArr = stringBuilder.toString().split("\n");
                for (String temp :
                        strArr) {
                    fileWriter.append(temp);
                    fileWriter.append("\n");
                }

            } catch (IOException e) {
                System.out.println("dest is not directory");
            }
        } else {
            System.out.println("File already exixts");
        }

    }

    public static void move(String source, String dest) {
        copy(source, dest);
        delete(source);
    }

    public static List<String> readAll(String path) {
        File file = new File(path);
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        List<String> arrayList = new ArrayList<String>();
        if (file.exists()) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                while ((line = bufferedReader.readLine()) != null) {
                    arrayList.add(line);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File doesn't exist");
        }
        return arrayList;
    }

    public static void writeLines(String path, List<String> list) {
        File file = new File(path);
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        if (file.exists()) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try (FileWriter fileWriter = new FileWriter(path)) {

                for (String temp :
                        stringBuilder.toString().split("\n")) {
                    fileWriter.append(temp);
                    fileWriter.append("\n");
                }

                for (String temp :
                        list) {
                    fileWriter.append(temp);
                    fileWriter.append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("File doesn't exist");
        }
    }

    public static List<String> list(String path) {
        File dir = new File(path);
        File[] arrFiles = dir.listFiles();
        List<File> lst = Arrays.asList(arrFiles);
        List<String> listNames = new ArrayList<String>() {
        };
        for (int i = 0; i < lst.size(); i++) {
            listNames.add(lst.get(i).toString());
        }
        return listNames;
    }
}

