package org.agty.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Аргументы командной строки.
 * Присваивание происходит только один раз, изменить значения нельзя.
 */
public final class MainArgs {
    private static final String version = "1.0.1";

    private static String srcArguments;
    private static final Map<String, String> mainArgs = new HashMap<>();
    private static boolean isParse = false;

    /**
     * Конструктор запрещает инициализацию.
     */
    private MainArgs() {}

    /**
     * Присвоение значений.
     * TODO: ошибки при передачи пустых параметров (-threads <пусто> -param ...)
     * @param args массив аргументов.
     * @param i индекс аргумента.
     * @return число инкременента.
     */
    private static int setArgument(String[] args, int i) {
        switch (args[i]) {
            case "-h":
            case "help":
            case "-help":
                set("help", "true");
                return 1;
            case "-type": set("type", args[i + 1]); break;
            case "-threads": set("threads", args[i + 1]); break;
            case "-archive": set("archive", args[i + 1]); break;
        }

        return 2;
    }

    /**
     * Исходная строка с аргументами.
     * @param srcArguments строка аргументов.
     */
    public static void setSrcArguments(String srcArguments) {
        if (getSrcArguments() == null) MainArgs.srcArguments = srcArguments.trim();
    }

    public static String getSrcArguments() {
        return srcArguments;
    }

    /**
     * Добавить аргумент в массив.
     * @param key имя аргумента.
     * @param val значение аргумента.
     */
    public static void set(String key, String val) {
        if (!isSet(key)) mainArgs.put(key, val.trim());
    }

    /**
     * Получить значение аргумента.
     * @param key имя аргумента.
     */
    public static String get(String key) {
        return mainArgs.get(key);
    }

    /**
     * Проверяет существование аргумента.
     * @param key имя аргумента.
     */
    public static boolean isSet(String key) {
        return get(key) != null && !get(key).isEmpty();
    }

    /**
     * Является ли аргумент булевым значением и содержит true|yes|1.
     * @param key имя аргумента.
     */
    public static boolean getBool(String key) {
        return get(key) != null && (get(key).equalsIgnoreCase("true") || get(key).equalsIgnoreCase("yes") || get(key).equals("1"));
    }

    /**
     * Пытается вернуть значение в виде цифрового значения.
     * @param key имя аргумента.
     */
    public static Integer getInt(String key) {
        //if (get(key) == null) return null;
        return Integer.parseInt(get(key));
    }

    /**
     * Парсинг параметров строки.
     * @param args массив аргументов.
     */
    public static void parse(String[] args) {
        if (isParse) return;

        //Сформировать строку из запросов и сохранить в параметрах
        StringBuilder original = new StringBuilder();

        for (String arg : args) {
            original.append(arg);
            original.append(" ");
        }

        setSrcArguments(original.toString());

        //Обойти все аргументы и собрать объект из параметров
        for (int i = 0; i < args.length;) {
            i += setArgument(args, i);
        }

        isParse = true;
    }

    /**
     * Возвращает содержимое в виде строки.
     */
    public static String inString() {
        StringBuilder toString = new StringBuilder();

        if (mainArgs.isEmpty()) return null;

        toString.append("MainArgs");
        toString.append( " {\n");
        toString.append("\t");
        toString.append("arguments = ");
        toString.append(srcArguments);
        toString.append("\n");
        for(Map.Entry<String, String> entry: mainArgs.entrySet()) {
            toString.append("\t");
            toString.append(entry.getKey());
            toString.append(" = ");
            toString.append(entry.getValue());
            toString.append("\n");
        }
        toString.append("}");

        return toString.toString();
    }
}
