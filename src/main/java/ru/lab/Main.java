package ru.lab;

import ru.lab.builder.ApplicationBuilder;
import ru.lab.builder.Console;


/**
 * Главный класс для запуска приложения.
 */
public class Main {
    private static String connectionString = "jdbc:postgresql://pg:5432/studs?user=s406435&password=6GfE0Z3kbSXCOWmR";
    private static String username = "user=s406435";
    private static String password = "password=6GfE0Z3kbSXCOWmR";
    private static String db = "studs";
    private static int port = 5432;
    private static String host = "pg";

    /**
     * Точка входа в приложение.
     *
     * @param args аргументы командной строки, которыми можно переопределить строку подключения.
     * user=XXX - имя пользователя БД
     * password=YYY - пароль пользователя БД
     * db=ZZZ - название БД
     * host=ABC - IP адрес или имя хоста на котором расположена база
     * port=5432 - порт БД
     * можно указывать в любом порядке и комбинации
     * для пропущенных параметров будут использоваться значения по умолчанию
     */
    public static void main(String[] args) {
        try {
            for(String arg : args) {
                String parameterName = arg.substring(0, arg.indexOf("="));
                if(parameterName.equals("host")) {
                    host = arg.substring(arg.indexOf("=") + 1);
                }
                if(parameterName.equals("port")) {
                    port = Integer.parseInt(arg.substring(arg.indexOf("=") + 1));
                }
                if(parameterName.equals("db")) {
                    db = arg.substring(arg.indexOf("=") + 1);;
                }
                if(parameterName.equals("user")) {
                   username = arg;
                }
                if(parameterName.equals("password")) {
                    password = arg;
                }
                //System.out.println(arg);
            }

            connectionString = "jdbc:postgresql://" + host + ":" + port + "/" + db + "?" + username + "&" + password;
            //System.out.println(connectionString);
            ApplicationBuilder builder = new ApplicationBuilder(args);
            Console console = builder.createConsole();
            console.start();
        } catch (Exception e) {
            System.err.println("Ошибка запуска приложения: " + e.getMessage());
        }
    }

    public static String getConnectionString() {
        return connectionString;
    }
}
