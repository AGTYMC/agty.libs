package org.agty.utils;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static java.lang.Math.PI;

/**
 * #	Developer: AgArtY M.C.                                                 #
 * #	E-mail: amc@agty.org                                                   #
 * #	Site: <a href="https://agty.org">https://agty.org</a>                  #
 * #	                                                                       #
 * #	Copying, distribution, sale of an initial code without the written     #
 * #	approval of the developer is forbidden.                                #
 */

public class AgtyUtils {
    private static final String version = "1.7.1";

    private static HashMap<String, String> fileSizeLangRU = null;
    private static HashMap<String, String> fileSizeLangEN = null;

    /**
     * Выводит текст на новой строке
     *
     * @param txt String
     */
    public static void echo(String txt) {
        echo(txt, true);
    }

    /**
     * Выводит цифры
     *
     * @param txt Integer
     */
    public static void echo(Integer txt) {
        echo(String.valueOf(txt), false);
    }

    /**
     * Выводит цифры
     *
     * @param txt Double
     */
    public static void echo(Double txt) {
        echo(String.valueOf(txt), false);
    }

    /**
     * Выводит цифры
     *
     * @param txt Long
     */
    public static void echo(long txt) {
        echo(String.valueOf(txt), false);
    }

    /**
     * Выводит цифры
     *
     * @param txt BigInteger
     */
    public static void echo(BigInteger txt) {
        echo(String.valueOf(txt), true);
    }

    /**
     * Выводит цифры
     *
     * @param txt BigInteger
     */
    public static void echo(BigDecimal txt) {
        echo(String.valueOf(txt), true);
    }

    /**
     * Выводит текст
     *
     * @param txt String
     * @param ln if = true text in newline
     */
    public static void echo(String txt, boolean ln) {
        if (ln) {
            System.out.println(txt);
        } else {
            System.out.print(txt);
        }
    }

    /**
     * Возвращает размер в байтах исходя из переданных данных
     *
     * echo func::filesize_title(1024); //1 КБ
     * echo func::filesize_title(1024*1024*5); //5 МБ
     * echo func::filesize_title(1024*1024*1024*100); //100 ГБ
     * echo func::filesize_title(1); //1 Байт
     *
     *
     * @param total_size Double
     * @param lang String
     * @return string
     */
    static public String filesize_title(long total_size, String lang) {
        String returns = null;

        BigDecimal bt1024 =  BigDecimal.valueOf(1024);

        BigDecimal size = BigDecimal.valueOf(total_size);

        BigDecimal TB = BigDecimal.valueOf(1073741824);
        TB = TB.multiply(BigDecimal.valueOf(1024));

        BigDecimal GB = BigDecimal.valueOf(1024*1024*1024);

        BigDecimal MB = BigDecimal.valueOf(1024*1024);

        BigDecimal KB = BigDecimal.valueOf(1024);

        if (size.compareTo(TB) > 0) {
            BigDecimal result = (((size.divide(bt1024)).divide(bt1024)).divide(bt1024)).divide(bt1024);
            returns = round(result.doubleValue()) + fileSizeLang("tb", lang);
        }
        else if (size.compareTo(GB) > 0) {
            BigDecimal result = ((size.divide(bt1024)).divide(bt1024)).divide(bt1024);
            returns = round(result.doubleValue()) + fileSizeLang("gb", lang);
        }
        else if (size.compareTo(MB) > 0) {
            BigDecimal result = (size.divide(bt1024)).divide(bt1024);
            returns = round(result.doubleValue()) + fileSizeLang("mb", lang);
        }
        else if (size.compareTo(KB) > 0) {
            BigDecimal result = size.divide(bt1024);
            returns = round(result.floatValue()) + fileSizeLang("kb", lang);
        }
        else if (size.compareTo(BigDecimal.valueOf(0)) > 0){
            returns = size  + fileSizeLang("b", lang);
        }
        else returns = "0";

        return returns;

    }

    /**
     * Вспомогательный метод для размеров файла
     *
     * @param type String
     * @param lang String
     * @return String | Null
     */
    private static String fileSizeLang(String type, String lang) {
        String returns = null;
        switch (lang) {
            case "ru": returns = fileSizeLangRU().get(type); break;
            case "en": returns = fileSizeLangEN().get(type);  break;
        };
        return returns;
    }

    /**
     * Языковой RU метод для размеров файла
     *
     * @return  Map
     */
    private static Map<String, String> fileSizeLangRU() {
        if (fileSizeLangRU == null) {
            fileSizeLangRU = new HashMap<>();
            fileSizeLangRU.put("tb", " ТБ");
            fileSizeLangRU.put("gb", " ГБ");
            fileSizeLangRU.put("mb", " МБ");
            fileSizeLangRU.put("kb", " КБ");
            fileSizeLangRU.put("b", " Байт");
        }

        return fileSizeLangRU;
    }

    /**
     * Языковой EN метод для размеров файла
     *
     * @return  Map
     */
    private static Map<String, String> fileSizeLangEN() {
        if (fileSizeLangEN == null) {
            fileSizeLangEN = new HashMap<>();
            fileSizeLangEN.put("tb", " TB");
            fileSizeLangEN.put("gb", " GB");
            fileSizeLangEN.put("mb", " MB");
            fileSizeLangEN.put("kb", " KB");
            fileSizeLangEN.put("b", " Byte");
        }

        return fileSizeLangEN;
    }

    /**
     * Округление до двух десятичных
     *
     * @param number float
     * @return float
     */
    static public String round(float number) {
        return String.format("%.2f", number);
    }

    static public String round(double number) {
        return String.format("%.2f", number);
    }

    static public String round(String number) {
        return String.format("%.2f", number);
    }

    /**
     * Возвращает md5-строку
     *
     * @param st String
     * @return String
     */
    public static String md5(String st) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            // тут можно обработать ошибку
            // возникает она если в передаваемый алгоритм в getInstance(,,,) не существует
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while( md5Hex.length() < 32 ){
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }

    /**
     * Вывести на экран
     *
     * *** - начало и конец блока сообщений
     * ~ - обычное сообщение
     * !! - ошибка
     * !~ - предупреждение
     */
    public static void echoMSG(String type, String txt) {
        System.out.print(date("yyyy-MM-dd HH:mm:ss ") + "|" + type + "| " +  txt + "\n");
    }
    public static void echoMSG(String txt) {
        System.out.print(date("yyyy-MM-dd HH:mm:ss ") + "| " +  txt + "\n");
    }
    public static void echoMSG() {
        System.out.print("\n");
    }

    /**
     * Работа с датой
     *
     * @param format
     * @return
     */
    public static String date(String format) {

        /*
            https://github.com/crab-cr/php-strtotime

            G	эра (в английской локализации — AD и BC)	н.э.
            y	год (4-х значное число)	2020
            yy	год (последние 2 цифры)	20
            yyyy	год (4-х значное число)	2020
            M	номер месяца (без лидирующих нулей)	8
            MM	номер месяца (с лидирующими нулями, если порядковый номер месяца < 10)	04
            MMM	трехбуквенное сокращение месяца (в соответствии с локализацией)	янв
            MMMM	полное название месяца	Июнь
            w	неделя в году (без лидирующих нулей)	4
            ww	неделя в году (с лидирующими нулями)	04
            W	неделя в месяце (без лидирующих нулей)	3
            WW	неделя в месяце (с лидирующим нулем)	03
            D	день в году	67
            d	день месяца (без лидирующих нулей)	9
            dd	день месяца (с лидирующими нулями)	09
            F	день недели в месяце (без лидирующих нулей)	9
            FF	день недели в месяце (с лидирующими нулями)	09
            E	день недели (сокращение)	Вт
            EEEE	день недели (полностью)	пятница
            u	номер дня недели (без лидирующих нулей)	5
            uu	номер дня недели (с лидирующими нулями)	05
            a	маркер AM/PM	AM
            H	часы в 24-часовом формате без лидирующих нулей	6
            HH	часы в 24-часовом формате с лидирующим нулем	06
            k	количество часов в 24-часовом формате	18
            K	количество часов в 12-часовом формате	6
            h	время в 12-часовом формате без лидирующих нулей	6
            hh	время в 12-часовом формате с лидирующим нулем	06
            m	минуты без лидирующих нулей	32
            mm	минуты с лидирующим нулем	32
            s	секунды без лидирующих нулей	11
            ss	секунды с лидирующим нулем	11
            S	миллисекунды	297
            z	часовой пояс	EET
            Z	часовой пояс в формате RFC 822	300

         * dd-MM-yyyy	01-11-2020
         * yyyy-MM-dd	2019-10-01
         * HH:mm:ss.SSS	23:59.59.999
         * yyyy-MM-dd HH:mm:ss	2018-11-30 03:09:02
         * yyyy-MM-dd HH:mm:ss.SSS	2016-03-01 01:20:47.999
         * yyyy-MM-dd HH:mm:ss.SSS Z	2013-13-13 23:59:59.999 +0100
         */

        //Некоторые форматы как PHP
        switch (format) {
            case "Y-m-d H:i:s":
                format = "yyyy-MM-dd HH:mm:ss";
                break;

            case "Y-m-d H:i":
                format = "yyyy-MM-dd HH:mm";
                break;

            case "Y-m-d":
                format = "yyyy-MM-dd";
                break;

            case "d.m.Y H:i:s":
                format = "dd.MM.yyyy HH:mm:ss";
                break;

            case "d.m.Y H:i":
                format = "dd.MM.yyyy HH:mm";
                break;

            case "d.m.Y":
                format = "dd.MM.yyyy";
                break;

            case "d.m.y H:i:s":
                format = "dd.MM.yy HH:mm:ss";
                break;

            case "d.m.y H:i":
                format = "dd.MM.yy HH:mm";
                break;

            case "d.m.y":
                format = "dd.MM.yy";
                break;
        }

        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat(format);
        return formatForDateNow.format(dateNow);
    }

    /**
     * Конвертор формата даты
     *
     * @return
     */
    public static String dateConvert(String oldFormat, String newFormat, String dateString) {

        if (dateString.isEmpty()) return "";

        if (newFormat.isEmpty()) {
            newFormat = "yyyy-MM-dd";
        }

        if (oldFormat.isEmpty()) {
            oldFormat = "dd.MM.yyyy";
        }

        SimpleDateFormat oldDateFormat = new SimpleDateFormat(oldFormat, Locale.getDefault());
        SimpleDateFormat newDateFormat = new SimpleDateFormat(newFormat, Locale.getDefault());

        Date date;
        try {
            date = oldDateFormat.parse(dateString);
        } catch (ParseException e) {
            return "";
        }
        return newDateFormat.format(date);
    }

    /**
     * Обрезает строку с конца
     *
     * @return string
     */
    public static String sub_str(String txt, int len) {
        if (len == 0) {
            len = 1;
        }
        return txt.substring(0, txt.length() - len);
    }

    /**
     * Кодирует в html-сущности
     *
     * @param source
     * @return
     */
    public static String hencode(String source) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            switch (c) {
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                case '&':
                    sb.append("&amp;");
                    break;
                case '"':
                    sb.append("&quot;");
                    break;
                case '\'':
                    sb.append("&apos;");
                    break;
                default:
                    sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * Декодирует из html-сущностей
     *
     * @param body String
     * @return String
     */
    public static String hdecode(String body) {
        return body.replace("&amp;", "&").replace("&quot;", "\"").replace( "&apos;", "'").replace( "&#039;", "'").replace( "&lt", "<").replace( "&gt;", ">");
    }

    /**
     * Вспомогательная функция, которая очищает запрос where
     *
     *   //`id_news` = '5.0123'
     *   func.where("id_news", 5.0123);
     *
     * @param field FieldName
     * @param value Value
     * @return String
     */
    public static String where(String field, double value) {
        return "`" + field + "` = '" + value + "'";
    }

    /**
     * Вспомогательная функция, которая очищает запрос where
     *
     *   //`id_news` = 5
     *   func.where("id_news", 5);
     *
     * @param field FieldName
     * @param value Value
     * @return String
     */
    public static String where(String field, int value) {
        return "`" + field + "` = " + value;
    }

    /**
     * Вспомогательная функция, которая очищает запрос where
     *
     *   //`id_news` = '5'
     *   func.where("id_news", "5");
     *
     *   //`id_news` IS NULL
     *   func.where("id_news", "" | null);
     *
     *   //`id_news` = '&quot;LCC&quot;, &apos;ABC&apos; &gt; 5 AND &lt; 6 &amp; hello'
     *   func.where("id_news", "\"LCC\", 'ABC' > 5 AND < 6 & hello");
     *
     * @param field FieldName
     * @param value Value
     * @return String
     */
    public static String where(String field, String value) {
        if (value == null || value.equals("")) {
            value = "IS NULL";
        } else {
            value = "= '" + AgtyUtils.hencode(value) + "'";
        }
        return "`" + field + "` " + value;
    }

    /**
     * Удаляет все не цифровые значения
     *
     * @param text String
     * @return Integer
     */
    public static String numeric(String text) {
        return text.replaceAll("[^0-9]", "");
    }

    /**
     * Расстояние от точки до дочки исходя из координат
     *
     * func.distance(56.123, 37.123, 56.321, 37.321)
     *
     * @param lng1, lat1, lng2, lat2  Координаты первой и второй точек
     * @return float|int
     */
    public static Double distance(Double lng1, Double lat1, Double lng2, Double lat2) {
        if (lng1 == null || lat1 == null || lng2 == null || lat2 == null) return null;

        int earth_radius = 6372797; //Средний радиус Земли в метрах

        double dLat = (lat2 - lat1) / 180.0 * PI;
        double dLon = (lng2 - lng1) / 180.0 * PI;
        double radLat1 = lat1 / 180.0 * PI;
        double radLng1 = lng1 / 180.0 * PI;

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(radLat1) * Math.cos(radLng1) * Math.sin(dLon/2) * Math.sin(dLon/2);

        return earth_radius * 2 * Math.asin(Math.sqrt(a));
    }

    /**
     * Конвертирует строку запроса в коллекцию
     * Аналог PHP parse_str
     *
     * @param string
     * @return
     */
    public static HashMap<String, String> arguments(String string) {
        HashMap<String, String> returns = new HashMap<String, String>();

        String[] args = string.split("&");

        for(String arg : args) {
            if (arg.contains("=")) {
                String[] val = arg.split("=");
                returns.put(val[0], val[1]);
            }
        }

        return returns;
    }

    /**
     * Проверяет наличие файла
     * @param path
     * @return
     */
    public static boolean isFile(String path) {
        return new File(path).isFile();
    }

    /**
     * Проверяет наличие директории
     * @param path
     * @return
     */
    public static boolean isDir(String path) {
        return new File(path).isDirectory();
    }

    /**
     * Аналог функции implode
     * HashMap hm = new HashMap();
     * hm.put("1", "123");
     * hm.put("aaa", "bbb");
     *
     * //123,bbb
     * String implode = func.implode(hm, ',');
     */
    public static String implode(HashMap<String, String> collect, String glue) {
        StringBuilder returns = new StringBuilder();

        for (Object key : collect.keySet()) {
            returns.append(collect.get(key));
            returns.append(glue);
        }

        if (returns.length() > 0) {
            returns.setLength(returns.length() - glue.length());
        }

        return returns.toString();
    }

    /**
     * Возвращает случайное число из заданного диапазона.
     *
     * @param min минимальное значение.
     * @param max максимальное значение.
     * @return случайное число.
     */
    public static int rand(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    /**
     * Проверяет наличие данных в строке.
     *
     * @param string строка.
     * @return true если строка есть и она не пустая.
     */
    public static boolean stringIsSet(String string) {
        return string != null && !string.trim().isEmpty();
    }
}


