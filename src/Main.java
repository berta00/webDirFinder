import java.io.IOException;
import java.util.*;

// program main interface
public class Main {
    public static String target = "http://www.example.com/";
    public static String dirList = "../../../dirList/topimDir.txt";

    public static void main(String[] args){
        // parse arguments
        for(int a = 0; a < args.length -1; a++){
            if(args[a].equals("-t") && (!args[a +1].equals("") || !args[a +1].equals("-l") || !args[a +1].equals("-t"))){
                target = args[a +1];
            }
            if(args[a].equals("-l") && (!args[a +1].equals("") || !args[a +1].equals("-t") || !args[a +1].equals("-l"))){
                dirList = args[a +1];
            }
        }
        menu();
    }
    public static void menu(){
        Scanner menuInput = new Scanner(System.in);

        System.out.print(getBanner(true));

        System.out.print("" +
        "1. Set target                  3. Try ping    \n" +
        "2. Set directory list          4. Start attack\n" +
        "5. Exit                                       \n" +
        "\n" +
        "Current target:          " + target  + "\n" +
        "Current directory list:  " + dirList + "\n" +
        "()> ");

        int mainMenuOpt = menuInput.nextInt();
        switch(mainMenuOpt){
            case 1:
                System.out.print("(target)> ");

                Scanner targetIn = new Scanner(System.in);
                target = targetIn.nextLine();

                menu();
                break;
            case 2:
                System.out.print("(dir list)> ");

                Scanner dirListIn = new Scanner(System.in);
                dirList = dirListIn.nextLine();

                menu();
                break;
            case 3:
                System.out.print(getBanner(true));
                System.out.print("Starting ping ...");

                WebDirCrawler ping = new WebDirCrawler(target, dirList);
                int reqCode = ping.testPing();

                System.out.print("\nPing done, response code: " + reqCode);

                System.out.print("\n\n" + "Press [Enter] to return to the menu");

                Scanner returnToMenu1 = new Scanner(System.in);
                returnToMenu1.nextLine();

                menu();
                break;
            case 4:
                System.out.print("\n" + getBanner(true));
                System.out.print("\nStarting the attack ...");

                WebDirCrawler attack = new WebDirCrawler(target, dirList);
                String attackStatus = attack.startAttack();

                System.out.print("\n\n" + "Press [Enter] to return to the menu");

                Scanner returnToMenu2 = new Scanner(System.in);
                returnToMenu2.nextLine();

                menu();
                break;
            case 5:
                System.out.print("Bye");
                System.exit(0);
        }
    }
    public static String getBanner(boolean cls){
        String author         = "berta00";
        String currentVersion = "1.0";


        if(cls){
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        return "" +
        "██████╗ ██╗██████╗      ██████╗██████╗  █████╗ ██╗    ██╗██╗     ███████╗██████╗ \n" +
        "██╔══██╗██║██╔══██╗    ██╔════╝██╔══██╗██╔══██╗██║    ██║██║     ██╔════╝██╔══██╗\n" +
        "██║  ██║██║██████╔╝    ██║     ██████╔╝███████║██║ █╗ ██║██║     █████╗  ██████╔╝\n" +
        "██║  ██║██║██╔══██╗    ██║     ██╔══██╗██╔══██║██║███╗██║██║     ██╔══╝  ██╔══██╗\n" +
        "██████╔╝██║██║  ██║    ╚██████╗██║  ██║██║  ██║╚███╔███╔╝███████╗███████╗██║  ██║\n" +
        "╚═════╝ ╚═╝╚═╝  ╚═╝     ╚═════╝╚═╝  ╚═╝╚═╝  ╚═╝ ╚══╝╚══╝ ╚══════╝╚══════╝╚═╝  ╚═╝\n" +
        "Author:  " + author         + "               " +
        "Version: " + currentVersion + "\n\n" +
        "";
    }
}
