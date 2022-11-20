import java.util.Scanner;

public class TicTacToe {
    static final int MAX_ROUNDS = 9; /* Max rounds in the game of standard Tic Tac Toe */
    private static void board(char[][] arr) {
        /* This function prints out the board along with borders and dividers, in other words with decoration */
        String gameBoard = "+---+---+---+\n"
                     + "| " + arr[0][0] + " | " + arr[0][1] + " | " + arr[0][2] + " |\n"
                     + "+---+---+---+\n"
                     + "| " + arr[1][0] + " | " + arr[1][1] + " | " + arr[1][2] + " |\n"
                     + "+---+---+---+\n"
                     + "| " + arr[2][0] + " | " + arr[2][1] + " | " + arr[2][2] + " |\n"
                     + "+---+---+---+\n";
        System.out.println(gameBoard);
    }

    private static int gameCondition(char[][] arr, int round) {
        /* check for the left diagonal */
        if (arr[0][0] != ' ' && (arr[0][0] == arr[1][1]) && (arr[1][1] == arr[2][2])) {
            return 1;
        }

        /* check for the right diagonal */
        if (arr[0][2] != ' ' && (arr[0][2] == arr[1][1]) && (arr[1][1] == arr[2][0])) {
            return 1;
        }

        for (int i = 0; i < arr.length; i++) {
            /* check for the rows */
            if (arr[i][0] != ' ' && (arr[i][0] == arr[i][1]) && (arr[i][1] == arr[i][2])) {
                return 1;
            }
            /* check for the columns */
            if (arr[0][i] != ' ' && (arr[0][i] == arr[1][i]) && (arr[1][i] == arr[2][i])) {
                return 1;
            }
        }

        /* check for TIE */
        if (round == MAX_ROUNDS - 1) {
            return 404;
        }
        return 0;
    }

    private static int inputLocation() {
        Scanner read = new Scanner(System.in);
        int location = 10;
        String l = read.next();
        if (l.length() != 1) {
            /* if the input string has invalid values for an integer then return default
             * value so that it can be handled later on */
            return location;
        } else {
            location = l.charAt(0) - '0';
        }
        return location - 1;
    }

    private static String playerTurn (int round, String nameOne, String nameTwo) {
        if (round % 2 == 1) {
            return nameOne;
        }
        return nameTwo;
    }

    private static boolean validLocation(int location) {
        return location >= 0 && location <= 8;
    }

    private static boolean cellFilled(char[][] arr, int location) {
        return arr[location / 3][location % 3 ] == ' ';
    }

    private static void printInputStatement(int round, String nameOne, String nameTwo) {
        System.out.print("[Player-" + playerTurn(round, nameOne, nameTwo) + "]: Enter the cell number (1 to 9): ");
    }

    private static void startBanner() {
        System.out.println("\n**************************************** Tic Tac Toe ****************************************");
    }
    private static void roundEndBanner() {
        System.out.println("-------------------------------------------------------------------------");
    }
    private static void endBanner() {
        System.out.println("******************************************** END ********************************************");
    }

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        char[][] arr = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = ' ';
            }
        }

        startBanner(); /* shows the Tic Tac Toe banner when the game starts */

        /* Register the Names of Both the Players */
        System.out.print("Player-1: Enter your Name: ");
        String playerOneName = read.next();
        System.out.print("Player-2: Enter your Name: ");
        String playerTwoName = read.next();

        /* Set the symbols for both the players */
        System.out.print("[Player-" + playerOneName + "]: Enter your chosen symbol from 'o' and 'x': ");
        char playerOne_value = read.next().charAt(0);

        /* Handle the invalid player symbol exception */
        while (playerOne_value != 'o' && playerOne_value != 'x') {
            System.out.print("[Invalid-Symbol] Enter valid value: ");
            playerOne_value = read.next().charAt(0);
        }
        char playerTwo_value = (playerOne_value == 'o') ? 'x' : 'o'; /* assign the player symbols */

        /* Print the board for the first player */
        board(arr);

        /* Game Loop */
        int round = 1;

        while (round != MAX_ROUNDS) {

            roundEndBanner(); /* prints a dotted hyphen indicating that the round has ended */
            System.out.println("[ROUND : " + round + "]\n");

            /* Alternating between Player 1 and Player 2 */
            printInputStatement(round, playerOneName, playerTwoName);

            /* Location input */
            int location = inputLocation();

            /* checks for invalid location OR cell already filled condition */
            while (!validLocation(location) || !cellFilled(arr, location)) {
                if (!validLocation(location)) System.out.print("[Invalid-Location] ");
                else System.out.print("[Cell-Already-Filled] ");
                printInputStatement(round, playerOneName, playerTwoName);
                location = inputLocation();
            }

            /* Putting the 'o' or 'x' inside the cell */
            arr[location / 3][location % 3] = (playerTurn(round, playerOneName, playerTwoName).equals(playerOneName)) ? playerOne_value : playerTwo_value;

            /* Print the board with Decoration */
            board(arr);

            /* Store the value of gameCondition method to avoid multiple method calls */
            int condition = gameCondition(arr, round);

            /* checks for WIN condition */
            if (condition == 1) {
                System.out.println("!! Player-" + playerTurn(round, playerOneName, playerTwoName) + " WINS the Game !!");
                endBanner();
                return;
            } else if (condition == 404) { /* check for TIE condition */
                System.out.println("!! The Game is TIED !!");
                endBanner();
                return;
            }

            /* increment the round by 1 */
            round++;
        }
    }
}

/*      TO-DO List:
 *   -> [IMPORTANT] Create a script or Test that runs all possible test cases and gives Summary
 *   -> [DIFFICULT TO-DO] Decorate the Board with the WIN condition
 *   -> [OPTIONAL][PARTIALLY-DONE] Refine Decorations for every round (starting borders, etc)
 *   -> [DONE] Set symbols for both Players
 *   -> [DONE] Board Print with decoration
 *   -> [DONE] Player Location Input
 *   -> [DONE] Handling cell already filled logic
 *   -> [DONE] Game Win logic with TIE
 *   -> [DONE] Exception Handling for invalid data type input
 */
