import java.io.*;
import java.util.*;

public class unit3AssignmentCode {
    static long start= 0; //global timer. Ends at the end of the game // 11/18/2022
    public static int xFS = 0; //Global variables that can be used in functions // 11/19/2022
    public static int yFS = 0;
    //BS meaning back side
    public static int[][] mineSweeperBS = //Global 2D array for the game. This is the backside. That means that all bombs and numbers are placed here  11/11/2022
            {
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
            };
    //ARRAY FS. FS meaning front side
    public static String[][] mineSweeperFS = //Global 2D array for the front side of the game. If the user picks one of the astericks', then it would replace the asteriks with the same position from the back side.  11/11/2022
            {
                    {"*", "*", "*", "*", "*", "*", "*", "*"},
                    {"*", "*", "*", "*", "*", "*", "*", "*"},
                    {"*", "*", "*", "*", "*", "*", "*", "*"},
                    {"*", "*", "*", "*", "*", "*", "*", "*"},
                    {"*", "*", "*", "*", "*", "*", "*", "*"},
                    {"*", "*", "*", "*", "*", "*", "*", "*"},
                    {"*", "*", "*", "*", "*", "*", "*", "*"},
                    {"*", "*", "*", "*", "*", "*", "*", "*"},
            };
    static String[][] scoreboard2d = { //2D array for scoreboard
            {"",""},
            {"",""},
            {"",""},
            {"",""},
            {"",""},
    };
    public static int verifyTopBottomRow(int x, int y) { // 11/11/2022
        int n = 0; //A function in which verifys if the square/cell is on the top or bottom row, which is then used to check certain squares around the square/cell
        if (y == 0 || y == 7) {
            if ((y == 0) && (x == 1 || x == 2 || x == 3 || x == 4 || x == 5 || x == 6)) {
                n = 1;
            } else if ((y == 7) && (x == 1 || x == 2 || x == 3 || x == 4 || x == 5 || x == 6)) {
                n = 2;
            } else {
                n = 0;
            }
        }
        return n;
    }

    public static int verifyLeftRightColumn(int x, int y) { // 11/11/2022
        int n = 0; //A function in which verifys if the square/cell is on the left or right column, which is then used to check certain squares around the square/cell
        if (x == 0 || x == 7) {
            if ((x == 0) && (y == 1 || y == 2 || y == 3 || y == 4 || y == 5 || y == 6)) {
                n = 1;
            } else if ((x == 7) && (y == 1 || y == 2 || y == 3 || y == 4 || y == 5 || y == 6)) {
                n = 2;
            } else {
                n = 0;
            }
        }
        return n;
    }

    public static int verifyCorner(int x, int y) { // 11/11/2022
        int n = 0; //A function in which verifys if the square/cell is either one of the corners, which is then used to check certain squares around the square/cell
        if (x == 0) {
            if (y == 0) {
                n = 1;
            } else if (y == 7) {
                n = 2;
            } else {
                n = 0;
            }
        } else if (x == 7) {
            if (y == 0) {
                n = 3;
            } else if (y == 7) {
                n = 4;
            } else {
                n = 0;
            }
        }
        return n;
    }
    public static void checkForZero(int y, int x) // This functions purpose is to reveal all zeros surrounding the user inputted coordinates.  11/17/2022
    {
        for (int i=x-1; i<=x+1;i++)
        {
            for(int j= y-1;j<=y+1;j++)
            {
                if (i>=0 && i<8 && j>=0 && j<8)//to prevent the array from going out of bounds.
                {
                    if(mineSweeperFS[j][i]=="*")//Condition used oly to expose '*'
                    {
                        mineSweeperFS[j][i] = String.valueOf(mineSweeperBS[j][i]); //exposes the set coordinates
                        if (mineSweeperBS[j][i]==0)// If the coordinate is 0 then...
                        {
                            checkForZero(j,i);//reoccurs the function to expose more zeros
                        }
                        squareColour(i,j); // 11/19/2022
                    }
                }
            }
        }
    }
    public static boolean victoryCheck() //function used to check if the user won // 11/16/2022
    {
        int count=0;
        for(int z = 0; z<=7; z++)
        {
            for(int v = 0; v<=7; v++)// these two for loops are used to check every position of the game to
            {
                if (!mineSweeperFS[v][z].equals("*") && !mineSweeperFS[v][z].equals("F"))//if the coordinate does NOT equal to an '*' or 'F', then...
                {
                    count++;//add 1
                }
            }
        }
        if(count==56)//if the count is equal to 56 then you win. This is because there is only 8 unexposed positions, which are the bombs
        {

            System.out.println(DARK_YELLOW + "  ___    ___  ________   ___  ___          ___       __    ___   ________    ___       \n" +
                    " |\\  \\  /  /||\\   __  \\ |\\  \\|\\  \\        |\\  \\     |\\  \\ |\\  \\ |\\   ___  \\ |\\  \\      \n" +
                    " \\ \\  \\/  / /\\ \\  \\|\\  \\\\ \\  \\\\\\  \\       \\ \\  \\    \\ \\  \\\\ \\  \\\\ \\  \\\\ \\  \\\\ \\  \\     \n" +
                    "  \\ \\    / /  \\ \\  \\\\\\  \\\\ \\  \\\\\\  \\       \\ \\  \\  __\\ \\  \\\\ \\  \\\\ \\  \\\\ \\  \\\\ \\  \\    \n" +
                    "   \\/  /  /    \\ \\  \\\\\\  \\\\ \\  \\\\\\  \\       \\ \\  \\|\\__\\_\\  \\\\ \\  \\\\ \\  \\\\ \\  \\\\ \\__\\   \n" +
                    " __/  / /       \\ \\_______\\\\ \\_______\\       \\ \\____________\\\\ \\__\\\\ \\__\\\\ \\__\\\\|__|   \n" +
                    "|\\___/ /         \\|_______| \\|_______|        \\|____________| \\|__| \\|__| \\|__|    ___ \n" +
                    "\\|___|/                                                                           |\\__\\\n" +
                    "                                                                                  \\|__|\n" +
                    "                                                                                       " + RESET);//This is some art, IGNORE!!!!!!
            return true;
        }
        else {
            return false;
        }
    }
    public static boolean defeatCheck(int y, int x) //This function is used to check if you won the game. // 11/16/2022
    {
        if (mineSweeperBS[y][x]==9)//9 is the integer code for the bomb. Thus if the user selected the bomb, then its a loss.
        {
            clearScreen();
            System.out.println(RED + " ________   ________   _____ ______    _______           ________   ___      ___  _______    ________   ___       \n" +
                    "|\\   ____\\ |\\   __  \\ |\\   _ \\  _   \\ |\\  ___ \\         |\\   __  \\ |\\  \\    /  /||\\  ___ \\  |\\   __  \\ |\\  \\      \n" +
                    "\\ \\  \\___| \\ \\  \\|\\  \\\\ \\  \\\\\\__\\ \\  \\\\ \\   __/|        \\ \\  \\|\\  \\\\ \\  \\  /  / /\\ \\   __/| \\ \\  \\|\\  \\\\ \\  \\     \n" +
                    " \\ \\  \\  ___\\ \\   __  \\\\ \\  \\\\|__| \\  \\\\ \\  \\_|/__       \\ \\  \\\\\\  \\\\ \\  \\/  / /  \\ \\  \\_|/__\\ \\   _  _\\\\ \\  \\    \n" +
                    "  \\ \\  \\|\\  \\\\ \\  \\ \\  \\\\ \\  \\    \\ \\  \\\\ \\  \\_|\\ \\       \\ \\  \\\\\\  \\\\ \\    / /    \\ \\  \\_|\\ \\\\ \\  \\\\  \\|\\ \\__\\   \n" +
                    "   \\ \\_______\\\\ \\__\\ \\__\\\\ \\__\\    \\ \\__\\\\ \\_______\\       \\ \\_______\\\\ \\__/ /      \\ \\_______\\\\ \\__\\\\ _\\ \\|__|   \n" +
                    "    \\|_______| \\|__|\\|__| \\|__|     \\|__| \\|_______|        \\|_______| \\|__|/        \\|_______| \\|__|\\|__|    ___ \n" +
                    "                                                                                                             |\\__\\\n" +
                    "                                                                                                             \\|__|\n" +
                    "                                                                                                                  " + RESET);//some art
            for(int x2=0;x2<8;x2++)// these 2 for loops expose all bombs to show the user
            {
                for(int y2=0;y2<8;y2++)
                {
                    if (mineSweeperBS[y2][x2]==9)
                    {
                        mineSweeperFS[y2][x2]=BLACK + "B" + RESET;//changes the secret integer 9 to the letter B to represent the bomb
                    }
                }
            }
            System.out.println(BLUE + "____________________________________________________");
            System.out.println(DARK_YELLOW + "                       FINAL TURN ");
            System.out.println(BLUE + "____________________________________________________" + RESET);
            System.out.println();
            System.out.println(LIGHT_GREY + "  | 0 1 2 3 4 5 6 7 |\n" + RESET +//this is the visual for the gamer
                    "  |_________________|\n" +
                    LIGHT_GREY + "0 | " + RESET + mineSweeperFS[0][0] + " " + mineSweeperFS[0][1] + " " + mineSweeperFS[0][2] + " " + mineSweeperFS[0][3] + " " + mineSweeperFS[0][4] + " " + mineSweeperFS[0][5] + " " + mineSweeperFS[0][6] + " " + mineSweeperFS[0][7] + " |\n" +
                    LIGHT_GREY + "1 | " + RESET + mineSweeperFS[1][0] + " " + mineSweeperFS[1][1] + " " + mineSweeperFS[1][2] + " " + mineSweeperFS[1][3] + " " + mineSweeperFS[1][4] + " " + mineSweeperFS[1][5] + " " + mineSweeperFS[1][6] + " " + mineSweeperFS[1][7] + " |\n" +
                    LIGHT_GREY + "2 | " + RESET + mineSweeperFS[2][0] + " " + mineSweeperFS[2][1] + " " + mineSweeperFS[2][2] + " " + mineSweeperFS[2][3] + " " + mineSweeperFS[2][4] + " " + mineSweeperFS[2][5] + " " + mineSweeperFS[2][6] + " " + mineSweeperFS[2][7] + " |\n" +
                    LIGHT_GREY + "3 | " + RESET + mineSweeperFS[3][0] + " " + mineSweeperFS[3][1] + " " + mineSweeperFS[3][2] + " " + mineSweeperFS[3][3] + " " + mineSweeperFS[3][4] + " " + mineSweeperFS[3][5] + " " + mineSweeperFS[3][6] + " " + mineSweeperFS[3][7] + " |\n" +
                    LIGHT_GREY + "4 | " + RESET + mineSweeperFS[4][0] + " " + mineSweeperFS[4][1] + " " + mineSweeperFS[4][2] + " " + mineSweeperFS[4][3] + " " + mineSweeperFS[4][4] + " " + mineSweeperFS[4][5] + " " + mineSweeperFS[4][6] + " " + mineSweeperFS[4][7] + " |\n" +
                    LIGHT_GREY + "5 | " + RESET + mineSweeperFS[5][0] + " " + mineSweeperFS[5][1] + " " + mineSweeperFS[5][2] + " " + mineSweeperFS[5][3] + " " + mineSweeperFS[5][4] + " " + mineSweeperFS[5][5] + " " + mineSweeperFS[5][6] + " " + mineSweeperFS[5][7] + " |\n" +
                    LIGHT_GREY + "6 | " + RESET + mineSweeperFS[6][0] + " " + mineSweeperFS[6][1] + " " + mineSweeperFS[6][2] + " " + mineSweeperFS[6][3] + " " + mineSweeperFS[6][4] + " " + mineSweeperFS[6][5] + " " + mineSweeperFS[6][6] + " " + mineSweeperFS[6][7] + " |\n" +
                    LIGHT_GREY + "7 | " + RESET + mineSweeperFS[7][0] + " " + mineSweeperFS[7][1] + " " + mineSweeperFS[7][2] + " " + mineSweeperFS[7][3] + " " + mineSweeperFS[7][4] + " " + mineSweeperFS[7][5] + " " + mineSweeperFS[7][6] + " " + mineSweeperFS[7][7] + " |\n");
            return true;
        }
        else {
            return false;
        }
    }
    public static void clearScreen() {  // 11/17/2022
        System.out.print("\033[H\033[2J"); //To free up RAM, the function moves the cursor to the top left of the console and clears it.
        System.out.flush();
    }

    public static int[] bubbleSorter(int[] array) //function that returns an array and uses an array as a parameter. This bubble sorter was used to sort the scoreboards time score. // 11/20/2022
    {
        int temp=0;
        //gets the length of the array
        int length = array.length;
        for(int q=0; q<length; q++)//for loops for comparison
        {
            for(int w=1;w<length;w++)
            {
                if(array[w-1]>array[w])
                {
                    //changes positions
                    temp=array[w-1];
                    array[w-1] = array[w];
                    array[w] = temp;
                }
            }
        }
        return array;//returns sorted array
    }

    public static String[] scoreboard2DArray(int n, String[] myArray, String score) //Function the returns an array and uses an array as a parameter. The purpose of this function is to add a brand-new score to the scoreboard // 11/20/2022
    {
        String newArray[] = new String[n + 1];
        //copy original array into new array
        for (int i = 0; i<myArray.length; i++)
        {
            newArray[i] = myArray[i];
        }
        //add element to the new array
        newArray[n] = score;
        return newArray;
    }

    public static String[][] orderedScoreboard (String[] scoreboard, String[][] finalScoreboard) //This function returns a 2D array and uses an array as a parameter. Orders the leaderboard in the 2d array. // 11/20/2022
    {

        int[] onlyTimeArray = new int[scoreboard.length];//makes a copy of the array
        String tempStringTime="";
        String temp="";
        int indexNum=0;
        for (int x = 0;x<scoreboard.length;x++)//
        {
            tempStringTime=scoreboard[x];//temporary string value

            onlyTimeArray[x] = Integer.parseInt(tempStringTime.replaceAll("[^0-9]", ""));//extracts integers to put in a array
        }
        for (int y = 0;y<5;y++)//gathers the 5 greatest score
        {
            finalScoreboard[y][1] = String.valueOf(bubbleSorter(onlyTimeArray)[y]);//calls bubble sorter to rearrange the time score. Then, it puts their string value in the 2D array
            temp=","+bubbleSorter(onlyTimeArray)[y]+",";
            for (int z = 0;z<scoreboard.length;z++)//for loop used to check every line of the scoreboard.txt
            {
                while(scoreboard[z].contains(temp))//if this line of scoreboard.txt include the string temp, then
                {
                    indexNum=scoreboard[z].indexOf(",");//this extracts the name of the person from scoreboard.txt
                    if(y<5)
                    {
                        finalScoreboard[y][0] = scoreboard[z].substring(0, indexNum);//Sets the persons name to the final scoreboard
                        break;
                    }
                }
            }
        }
        return finalScoreboard;
    }

    //Colour and Text Constants for decoration,  11/19/2022
    public static final String RESET = "\u001b[0m";    // RESET COLOUR
    public static final String RED = "\u001b[31m";     // RED
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\u001b[38;5;228m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String BRIGHT_RED = "\u001b[31;1m"; //BRIGHT RED
    public static final String BRIGHT_BLACK = "\u001b[30;1m";  //BRIGHT BLACK
    public static final String DARK_GREY = "\u001b[38;5;238m";  //DARK GREY
    public static final String LIGHT_GREY = "\u001b[38;5;253m";  //LIGHT GREY
    public static final String DARK_YELLOW = "\u001b[38;5;220m";  // YELLOW
    public static final String MAROON = "\u001b[38;5;88m";  //MAROON
    //All the constant strings shown above are to decorate the game with colour rather than black and white.
    public static void squareColour(int x, int y){ // 11/19/2022
        if (mineSweeperFS[y][x]=="F") //A long function that determines the number or letter the gameboard is showing, and the colour that is to be set for it
        {
            mineSweeperFS[y][x] = "F";
        }
        else
        {
            if (mineSweeperBS[y][x]==0)
            {
                mineSweeperFS[y][x] = DARK_GREY + String.valueOf(mineSweeperBS[y][x]) + RESET;
            }
            if (mineSweeperBS[y][x]==1)
            {
                mineSweeperFS[y][x] = BLUE + String.valueOf(mineSweeperBS[y][x]) + RESET;
            }
            if (mineSweeperBS[y][x]==2)
            {
                mineSweeperFS[y][x] = GREEN + String.valueOf(mineSweeperBS[y][x]) + RESET;
            }
            if (mineSweeperBS[y][x]==3)
            {
                mineSweeperFS[y][x] = RED + String.valueOf(mineSweeperBS[y][x]) + RESET;
            }
            if (mineSweeperBS[y][x]==4)
            {
                mineSweeperFS[y][x] = PURPLE + String.valueOf(mineSweeperBS[y][x]) + RESET;
            }
            if (mineSweeperBS[y][x]==5)
            {
                mineSweeperFS[y][x] = MAROON + String.valueOf(mineSweeperBS[y][x]) + RESET;
            }
            if (mineSweeperBS[y][x]==6)
            {
                mineSweeperFS[y][x] = CYAN + String.valueOf(mineSweeperBS[y][x]) + RESET;
            }
            if (mineSweeperBS[y][x]==7)
            {
                mineSweeperFS[y][x] = YELLOW + String.valueOf(mineSweeperBS[y][x]) + RESET;
            }
            if (mineSweeperBS[y][x]==8)
            {
                mineSweeperFS[y][x] = BRIGHT_BLACK + String.valueOf(mineSweeperBS[y][x]) + RESET;
            }
        }
    }

    public static void scoreboardWriter(String name, int time)// This is a function that has no returns. Its purpose is to scores to the scoreboard. // 11/20/2022
    {
        try
        {
            FileWriter scoreboard = new FileWriter("scoreboard.txt", true);
            scoreboard.write(name + ",");
            scoreboard.write(time + ",\n");
            scoreboard.close();
            System.out.println("Successfully uploaded score to the game.");
        }
        catch (IOException e)
        {
            System.out.println("ERROR, COULD NOT UPLOAD SCORE TO SCOREBOARD, SORRY!");
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        Scanner userInput = new Scanner(System.in);
        clearScreen();
        System.out.println(GREEN + "Please enter your name:" + RESET); // 11/14/2022
        String userName = userInput.nextLine();
        clearScreen();
        //This just prints 'Welcome To Minesweeper'
        System.out.println(BLUE + " ___       __   _______   ___       ________  ________  _____ ______   _______           _________  ________            \n" +
                "|\\  \\     |\\  \\|\\  ___ \\ |\\  \\     |\\   ____\\|\\   __  \\|\\   _ \\  _   \\|\\  ___ \\         |\\___   ___\\\\   __  \\           \n" +
                "\\ \\  \\    \\ \\  \\ \\   __/|\\ \\  \\    \\ \\  \\___|\\ \\  \\|\\  \\ \\  \\\\\\__\\ \\  \\ \\   __/|        \\|___ \\  \\_\\ \\  \\|\\  \\          \n" +
                " \\ \\  \\  __\\ \\  \\ \\  \\_|/_\\ \\  \\    \\ \\  \\    \\ \\  \\\\\\  \\ \\  \\\\|__| \\  \\ \\  \\_|/__           \\ \\  \\ \\ \\  \\\\\\  \\         \n" +
                "  \\ \\  \\|\\__\\_\\  \\ \\  \\_|\\ \\ \\  \\____\\ \\  \\____\\ \\  \\\\\\  \\ \\  \\    \\ \\  \\ \\  \\_|\\ \\           \\ \\  \\ \\ \\  \\\\\\  \\        \n" +
                "   \\ \\____________\\ \\_______\\ \\_______\\ \\_______\\ \\_______\\ \\__\\    \\ \\__\\ \\_______\\           \\ \\__\\ \\ \\_______\\       \n" +
                "    \\|____________|\\|_______|\\|_______|\\|_______|\\|_______|\\|__|     \\|__|\\|_______|            \\|__|  \\|_______|       \n" +
                "                                                                                                                        \n" +
                "                                                                                                                        \n" +
                "                                                                                                                        \n" +
                " _____ ______   ___  ________   _______   ________  ___       __   _______   _______   ________  _______   ________     \n" +
                "|\\   _ \\  _   \\|\\  \\|\\   ___  \\|\\  ___ \\ |\\   ____\\|\\  \\     |\\  \\|\\  ___ \\ |\\  ___ \\ |\\   __  \\|\\  ___ \\ |\\   __  \\    \n" +
                "\\ \\  \\\\\\__\\ \\  \\ \\  \\ \\  \\\\ \\  \\ \\   __/|\\ \\  \\___|\\ \\  \\    \\ \\  \\ \\   __/|\\ \\   __/|\\ \\  \\|\\  \\ \\   __/|\\ \\  \\|\\  \\   \n" +
                " \\ \\  \\\\|__| \\  \\ \\  \\ \\  \\\\ \\  \\ \\  \\_|/_\\ \\_____  \\ \\  \\  __\\ \\  \\ \\  \\_|/_\\ \\  \\_|/_\\ \\   ____\\ \\  \\_|/_\\ \\   _  _\\  \n" +
                "  \\ \\  \\    \\ \\  \\ \\  \\ \\  \\\\ \\  \\ \\  \\_|\\ \\|____|\\  \\ \\  \\|\\__\\_\\  \\ \\  \\_|\\ \\ \\  \\_|\\ \\ \\  \\___|\\ \\  \\_|\\ \\ \\  \\\\  \\| \n" +
                "   \\ \\__\\    \\ \\__\\ \\__\\ \\__\\\\ \\__\\ \\_______\\____\\_\\  \\ \\____________\\ \\_______\\ \\_______\\ \\__\\    \\ \\_______\\ \\__\\\\ _\\ \n" +
                "    \\|__|     \\|__|\\|__|\\|__| \\|__|\\|_______|\\_________\\|____________|\\|_______|\\|_______|\\|__|     \\|_______|\\|__|\\|__|\n" +
                "                                            \\|_________|                                                                \n" +
                "                                                                                                                        \n" +
                "                                                                                                                        " + RESET);//more art

        System.out.println(GREEN + "Welcome " + userName + GREEN + " to our Minesweeper Game. Look at the menu to decide what you want to do." + RESET);
        //Variables
        int bombsNear = 0;
        int bombCoordX = 0;
        int bombCoordY = 0;
        int topBottomRow = 0;
        int leftRightColumn = 0;
        int corner = 0;
        boolean gameComplete = false;
        final int max = 8;
        int turns = 0;
        start = System.currentTimeMillis();
        String breakOrFlag;
        String menuSelection;
        String[] unorderedScoreboard = {};
        Random rand = new Random();

        //Places the bombs on the BS array. // 11/11/2022
        for (int i = 0; i < 8; i++) {//places 8 bombs
            bombCoordX = rand.nextInt(max);//randomizes x coord
            bombCoordY = rand.nextInt(max);//randomizes y coord
            if (mineSweeperBS[bombCoordY][bombCoordX] == 9) { //Once again, 9 is the secret code for the bomb. So if there's a bomb already on that coord, then it loops again.
                i--;
            } else {
                mineSweeperBS[bombCoordY][bombCoordX] = 9;//Places bomb
            }
        }
        //Places the number that surround the bombs
        for (int x = 0; x <= 7; x++) {
            for (int y = 0; y <= 7; y++) {
                if (mineSweeperBS[y][x]!=9)
                {
                    //Checking near the coordinates the user has chosen to see whether there are bombs nearby in the raidus
                    bombsNear = 0;//Reassign this value to 0 every loop
                    topBottomRow = verifyTopBottomRow(x, y);//1 = top row, 2 = bottom row, 0 = none
                    leftRightColumn = verifyLeftRightColumn(x, y);// 1 = left row, 2 = right row, 0 = none
                    corner = verifyCorner(x, y); // 1 = Top left corner, 2 = bottom left corner, 3 = Top right corner, 4 = bottom right corner, 0 = none
                    // System.out.println(x + ", " + y);
                    // System.out.println("Top/Bottom Row:" + topBottomRow);
                    // System.out.println("Left/Right Row:" + leftRightColumn);
                    // System.out.println("Corner:" + corner);
                    if (topBottomRow == 0 && leftRightColumn == 0 && corner == 0) // 11/11/2022
                    {
                        for (int i = -1; i <= 1; i++) { ///////////////Middle Cells
                            if (mineSweeperBS[y - 1][x + i] == 9) {
                                bombsNear += 1;
                            }
                            if (mineSweeperBS[y][x + i] == 9) {
                                bombsNear += 1;
                            }
                            if (mineSweeperBS[y + 1][x + i] == 9) {
                                bombsNear += 1;
                            }
                        }
                    } else if (topBottomRow > 0)///////////////////////////TOP/BOTTOM ROW  11/11/2022
                    {
                        if (topBottomRow == 1)///Top Row
                        {
                            for (int i = -1; i <= 1; i++) {
                                if (mineSweeperBS[y][x + i] == 9) {
                                    bombsNear += 1;
                                }
                                if (mineSweeperBS[y + 1][x + i] == 9) {
                                    bombsNear += 1;
                                }
                            }
                        } else if (topBottomRow == 2)///Bottom Row
                        {
                            for (int i = -1; i <= 1; i++) {
                                if (mineSweeperBS[y][x + i] == 9) {
                                    bombsNear += 1;
                                }
                                if (mineSweeperBS[y - 1][x + i] == 9) {
                                    bombsNear += 1;
                                }
                            }
                        }
                    } else if (leftRightColumn > 0)///////////////////////////LEFT/RIGHT COLUMN  11/11/2022
                    {
                        if (leftRightColumn == 1)///Left Column
                        {
                            for (int i = 0; i <= 1; i++) {
                                if (mineSweeperBS[y][x + i] == 9) {
                                    bombsNear += 1;
                                }
                                if (mineSweeperBS[y - 1][x + i] == 9) {
                                    bombsNear += 1;
                                }
                                if (mineSweeperBS[y + 1][x + i] == 9) {
                                    bombsNear += 1;
                                }
                            }
                        } else if (leftRightColumn == 2)///Right Column  11/11/2022
                        {
                            for (int i = -1; i <= 0; i++) {
                                if (mineSweeperBS[y][x + i] == 9) {
                                    bombsNear += 1;
                                }
                                if (mineSweeperBS[y - 1][x + i] == 9) {
                                    bombsNear += 1;
                                }
                                if (mineSweeperBS[y + 1][x + i] == 9) {
                                    bombsNear += 1;
                                }
                            }
                        }
                    } else if (corner > 0)///////////////////////////CORNERS  11/11/2022
                    {
                        if (corner == 1)///Top Left Corner
                        {
                            for (int i = 0; i <= 1; i++) {
                                if (mineSweeperBS[y][x + i] == 9) {
                                    bombsNear += 1;
                                }
                                if (mineSweeperBS[y + 1][x + i] == 9) {
                                    bombsNear += 1;
                                }
                            }
                        } else if (corner == 2)///Bottom Left Corner
                        {
                            for (int i = 0; i <= 1; i++) {
                                if (mineSweeperBS[y][x + i] == 9) {
                                    bombsNear += 1;
                                }
                                if (mineSweeperBS[y - 1][x + i] == 9) {
                                    bombsNear += 1;
                                }
                            }
                        } else if (corner == 3)///Top Right Corner
                        {
                            for (int i = -1; i <= 0; i++) {
                                if (mineSweeperBS[y][x + i] == 9) {
                                    bombsNear += 1;
                                }
                                if (mineSweeperBS[y + 1][x + i] == 9) {
                                    bombsNear += 1;
                                }
                            }
                        } else if (corner == 4)///Bottom Right Corner
                        {
                            for (int i = -1; i <= 0; i++) {
                                if (mineSweeperBS[y][x + i] == 9) {
                                    bombsNear += 1;
                                }
                                if (mineSweeperBS[y - 1][x + i] == 9) {
                                    bombsNear += 1;
                                }
                            }
                        }
                    }
                    if (bombsNear > 0)//If the coordinates have bombs in radius  11/11/2022
                    {
                        // System.out.println("Value: " + mineSweeperBS[y][x]);
                        mineSweeperBS[y][x] = Integer.parseInt(String.valueOf(bombsNear));
                    }
                }
            }
        }

        //Write some code to have the option to play and some brief instructions. This code will loop until the player wants to play (Possibly a while or a do while loop)
        do { //  11/17/2022
            System.out.println(BLUE + "____________________________________________________");
            System.out.println(DARK_YELLOW + "                       MENU ");
            System.out.println(BLUE + "____________________________________________________");
            System.out.println("");
            System.out.println(YELLOW + "i - Instructions/How To Play" + RESET);
            System.out.println(PURPLE + "p - Play Game" + RESET);
            do { // 11/18/2022
                System.out.println("");
                System.out.println(DARK_GREY + "Select an action:");
                menuSelection = userInput.nextLine().toLowerCase();
                if (!menuSelection.equals("i") && !menuSelection.equals("p"))
                {
                    System.out.println("");
                    System.out.println(RED + "Invalid Selection. Please re-enter:" + RESET);
                }
            } while (!menuSelection.equals("i") && !menuSelection.equals("p"));
            if (menuSelection.equals("i"))
            {
                System.out.println("");
                System.out.println(GREEN + "The goal of the logic game Minesweeper is to identify and locate the squares or cells of a set number of mines as quickly as you can. There are 8 mines in this game, and the player must select the squares or cells that don't have mines in order to win. If the player doesn't choose a mine, it will then show a square or cell with a number (0–8) that represents the total number of mines in the eight adjacent squares. You must expose every square without a mine if you want to win." + RESET);
                System.out.println("");
                System.out.println(YELLOW + "Here are some terms/symbols you will need to know:" + RESET);
                System.out.println("* - Unrevealed Square/Cell");
                System.out.println(BRIGHT_RED + "F - A flag that can be placed by you to indicate the possibility of a mine. It serves no purpose or logic to the game, but helps remember where you think the mines are.");
                System.out.println(DARK_GREY + "0 - Represents a dead space or a square/cell that has no mines in any of it's 8 neighbouring squares around it.");
                System.out.println(BLUE + "1" + "-" + MAROON + "8" + PURPLE + " - A square/cell that has 1-8 mines in the adjacent squares around it." + RESET);

            }
            if (menuSelection.equals("p")) // 11/18/2022
            {
                System.out.println("");
                System.out.println(GREEN + "Good luck and have fun!" + RESET);
            }
        }while(menuSelection.equals("i"));
        clearScreen();
        while (!gameComplete) {//While loop for the game to repeat. // 11/11/2022
            clearScreen(); //Clears the console every turn to be more memory efficient,  11/18/2022
            turns++;//turn count
            System.out.println(BLUE + "____________________________________________________");
            System.out.println(DARK_YELLOW + "                       TURN " + turns);
            System.out.println(BLUE + "____________________________________________________" + RESET);
            System.out.println();
            System.out.println(LIGHT_GREY + "  | 0 1 2 3 4 5 6 7 |\n" + RESET + //console visual for user
                    "  |_________________|\n" +
                    LIGHT_GREY + "0 | " + RESET + mineSweeperFS[0][0] + " " + mineSweeperFS[0][1] + " " + mineSweeperFS[0][2] + " " + mineSweeperFS[0][3] + " " + mineSweeperFS[0][4] + " " + mineSweeperFS[0][5] + " " + mineSweeperFS[0][6] + " " + mineSweeperFS[0][7] + " |\n" +
                    LIGHT_GREY + "1 | " + RESET + mineSweeperFS[1][0] + " " + mineSweeperFS[1][1] + " " + mineSweeperFS[1][2] + " " + mineSweeperFS[1][3] + " " + mineSweeperFS[1][4] + " " + mineSweeperFS[1][5] + " " + mineSweeperFS[1][6] + " " + mineSweeperFS[1][7] + " |\n" +
                    LIGHT_GREY + "2 | " + RESET + mineSweeperFS[2][0] + " " + mineSweeperFS[2][1] + " " + mineSweeperFS[2][2] + " " + mineSweeperFS[2][3] + " " + mineSweeperFS[2][4] + " " + mineSweeperFS[2][5] + " " + mineSweeperFS[2][6] + " " + mineSweeperFS[2][7] + " |\n" +
                    LIGHT_GREY + "3 | " + RESET + mineSweeperFS[3][0] + " " + mineSweeperFS[3][1] + " " + mineSweeperFS[3][2] + " " + mineSweeperFS[3][3] + " " + mineSweeperFS[3][4] + " " + mineSweeperFS[3][5] + " " + mineSweeperFS[3][6] + " " + mineSweeperFS[3][7] + " |\n" +
                    LIGHT_GREY + "4 | " + RESET + mineSweeperFS[4][0] + " " + mineSweeperFS[4][1] + " " + mineSweeperFS[4][2] + " " + mineSweeperFS[4][3] + " " + mineSweeperFS[4][4] + " " + mineSweeperFS[4][5] + " " + mineSweeperFS[4][6] + " " + mineSweeperFS[4][7] + " |\n" +
                    LIGHT_GREY + "5 | " + RESET + mineSweeperFS[5][0] + " " + mineSweeperFS[5][1] + " " + mineSweeperFS[5][2] + " " + mineSweeperFS[5][3] + " " + mineSweeperFS[5][4] + " " + mineSweeperFS[5][5] + " " + mineSweeperFS[5][6] + " " + mineSweeperFS[5][7] + " |\n" +
                    LIGHT_GREY + "6 | " + RESET + mineSweeperFS[6][0] + " " + mineSweeperFS[6][1] + " " + mineSweeperFS[6][2] + " " + mineSweeperFS[6][3] + " " + mineSweeperFS[6][4] + " " + mineSweeperFS[6][5] + " " + mineSweeperFS[6][6] + " " + mineSweeperFS[6][7] + " |\n" +
                    LIGHT_GREY + "7 | " + RESET + mineSweeperFS[7][0] + " " + mineSweeperFS[7][1] + " " + mineSweeperFS[7][2] + " " + mineSweeperFS[7][3] + " " + mineSweeperFS[7][4] + " " + mineSweeperFS[7][5] + " " + mineSweeperFS[7][6] + " " + mineSweeperFS[7][7] + " |\n");
            do { //a do while loop asking a user to break a block or flag. Loops until the user says one of them. // 11/11/2022
                System.out.println(GREEN + "Would you like to place flag or break a block? Type 'f' to place a flag, and type 'b' to break a block. " + RESET);
                breakOrFlag = userInput.nextLine().toLowerCase();
            } while (!breakOrFlag.equals("f") && !breakOrFlag.equals("b"));
            if (breakOrFlag.equals("f")) {
                do { //Do while loop to catch if user's coordinates to place a flag already has one or if the square/cell has already been revealed,  18/2022
                    do{//Another do while loop used to stop people from entering string or anything greater than 7 and less than 0. // 11/11/2022
                        System.out.println("Enter the x coordinate for flag (Row #):");
                        try {//Try and catch used to stop users from entering strings // 11/11/2022
                            xFS = Integer.parseInt(userInput.nextLine());//Takes anything inputted by the user and uses it in the x coord
                        } catch (Exception e) { //If an error occurs then this message will be sent
                            System.out.println(RED + "Error: Sting input, try again." + RESET);
                            xFS = 100;//makes the x value 100 to loop again
                        }
                        if (xFS < 0)//any negatives will be given error
                        {
                            System.out.println(RED + "Out of bounds, use another number." + RESET);
                            xFS = 100;
                        }
                    } while (xFS >= max);
                    do {//Same concept for the do while loop ABOVE for the x value. // 11/11/2022
                        System.out.println("Enter the y coordinate for flag (Column #):");
                        try {//Same concept
                            yFS = Integer.parseInt(userInput.nextLine());//Same concept
                        } catch (Exception e) {
                            System.out.println(RED + "Error: Sting input, try again." + RESET);
                            yFS = 100;
                        }
                        if (yFS < 0)//Same concept
                        {
                            System.out.println(RED + "Out of bounds, use another number." + RESET);
                            yFS = 100;
                        }
                    } while (yFS >= max);//Same concept
                    if (mineSweeperFS[yFS][xFS] == "F")//prevents user from putting a flag in the same place
                    {
                        System.out.println(RED + "You already have a flag in these coordinates! Retry again" + RESET);
                        System.out.println("");
                    }
                    if (mineSweeperFS[yFS][xFS] != "*")//prevents user from putting a flag on an already revealed cell.
                    {
                        System.out.println(RED + "You already revealed this cell! Retry again" + RESET);
                        System.out.println("");
                    }
                } while (mineSweeperFS[yFS][xFS] == "F" || mineSweeperFS[yFS][xFS] != "*");
                mineSweeperFS[yFS][xFS] = "F";//places the flag // 11/11/2022
                squareColour(xFS,yFS); // 11/19/2022
            }
            else {//Else means break.
                do{//Do while loop to catch if user's coordinates to break a square/cell and the square has already been revealed, catching the error,  18/2022
                    do {//Another do while loop used to stop people from entering string or anything greater than 7 and less than 0. // 11/11/2022
                        System.out.println("Enter the x coordinate (Row #):");
                        try {//Try and catch used to stop users from entering strings // 11/11/2022
                            xFS = Integer.parseInt(userInput.nextLine());//Takes anything inputted by the user and uses it in the x coord
                        } catch (Exception e) {//If an error occurs then this message will be sent
                            System.out.println(RED + "Error: string input" + RESET);
                            xFS = 100;//makes the x value 100 to loop again
                        }
                        if (xFS < 0)//any negatives will be given error
                        {
                            System.out.println(RED + "Error: Invalid number, try again." + RESET);
                            xFS = 100;
                        }
                    } while (xFS >= max);
                    do {//Same concept for the do while loop ABOVE for the x value. // 11/11/2022
                        System.out.println("Enter the y coordinate (Column #):");
                        try {
                            yFS = Integer.parseInt(userInput.nextLine());
                        } catch (Exception e) {
                            System.out.println(RED + "No strings please" + RESET);
                            yFS = 100;
                        }
                        if (yFS < 0)
                        {
                            System.out.println(RED + "Out of bounds, use another number." + RESET);
                            yFS = 100;
                        }
                    } while (yFS >= max);
                    if (!mineSweeperFS[yFS][xFS].equals("F") && !mineSweeperFS[yFS][xFS].equals("*"))// If the user already revealed the cell they will be given the message below.
                    {
                        System.out.println(RED + "You already revealed this cell! Retry again" + RESET);
                        System.out.println("");
                    }
                }while (mineSweeperFS[yFS][xFS] != "F" && mineSweeperFS[yFS][xFS] != "*");
                if (mineSweeperFS[yFS][xFS] == "F") //Condition if user breaks a flag, flag is removed and an asterisk sign is placed,  18/2022
                {
                    mineSweeperFS[yFS][xFS] = "*";
                }
                else
                {
                    mineSweeperFS[yFS][xFS] = String.valueOf(mineSweeperBS[yFS][xFS]);//Exposes the user inputted coordinates.  11/13/2022
                    squareColour(xFS,yFS); // 11/19/2022
                }
                if (mineSweeperFS[yFS][xFS] != "*" && defeatCheck(yFS, xFS)) {
                    long end = System.currentTimeMillis();
                    int total = (int) (end - start) / 1000;
                    System.out.println("The total time elapsed was " + total + " seconds!");
                    gameComplete = true;

                }
                if (mineSweeperBS[yFS][xFS] == 0){
                    checkForZero(yFS, xFS);//Initiates checkForZero function eliminate all 0 near its radius.  11/17/2022
                }
                if(victoryCheck())//Initiates the victory check function. // 11/20/222
                {
                    int scoreboardTime= 0;//time
                    String scoreboardContents="";
                    long end = System.currentTimeMillis();//ends the global timer
                    int total = (int) (end - start) / 1000;// declares the time in total
                    System.out.println("The total time elapsed was " + total + " seconds!");//outputs the time
                    gameComplete=true;//Game completed so game will not be looped again
                    scoreboardTime=total;//scoreboard time = total
                    scoreboardWriter(userName, scoreboardTime);//Scoreboard writer to write all the scores in the txt.
                }
            }
        }
        BufferedReader in = new BufferedReader(new FileReader("scoreboard.txt"));//This reads the scoreboard txt
        String line;//New temporary string variable
        while((line = in.readLine()) != null)//Looks at each line of the scoreboard.txt
        {
            unorderedScoreboard = scoreboard2DArray(unorderedScoreboard.length, unorderedScoreboard, line); //This functions main purpose is to add a new score in the unordered scoreboard.
        }
        in.close();//close
        System.out.println("THE TOP 5 PLAYERS FOR MINESWEEPER\n" +
                "     Name          |       Time\n" +
                "1.   "+orderedScoreboard(unorderedScoreboard,scoreboard2d)[0][0]+"                 "+orderedScoreboard(unorderedScoreboard,scoreboard2d)[0][1]+"          \n" +
                "2.   "+orderedScoreboard(unorderedScoreboard,scoreboard2d)[1][0]+"                 "+orderedScoreboard(unorderedScoreboard,scoreboard2d)[1][1]+"          \n" +
                "3.   "+orderedScoreboard(unorderedScoreboard,scoreboard2d)[2][0]+"                 "+orderedScoreboard(unorderedScoreboard,scoreboard2d)[2][1]+"          \n" +
                "4.   "+orderedScoreboard(unorderedScoreboard,scoreboard2d)[3][0]+"                 "+orderedScoreboard(unorderedScoreboard,scoreboard2d)[3][1]+"          \n" +
                "5.   "+orderedScoreboard(unorderedScoreboard,scoreboard2d)[4][0]+"                 "+orderedScoreboard(unorderedScoreboard,scoreboard2d)[4][1]+"          ");
    }
}