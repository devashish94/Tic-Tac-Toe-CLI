import java.util.Scanner;

public class TicTacToe {

    static void board(char[][] arr) {
        /* This function prints out the board along with borders and dividers, in other words with decoration */
        String board = "+---+---+---+\n"
                     + "| " + arr[0][0] + " | " + arr[0][1] + " | " + arr[0][2] + " |\n"
                     + "+---+---+---+\n"
                     + "| " + arr[1][0] + " | " + arr[1][1] + " | " + arr[1][2] + " |\n"
                     + "+---+---+---+\n"
                     + "| " + arr[2][0] + " | " + arr[2][1] + " | " + arr[2][2] + " |\n"
                     + "+---+---+---+\n";
        System.out.println(board);
    }

    static int gameWin(char[][] arr, int round) {
        // check for the left diagonal
        if (arr[0][0] != ' ' && (arr[0][0] == arr[1][1]) && (arr[1][1] == arr[2][2])) {
            return 1;
        }

        // check for the right diagonal
        if (arr[0][2] != ' ' && (arr[0][2] == arr[1][1]) && (arr[1][1] == arr[2][0])) {
            return 1;
        }

        for (int i = 0; i < arr.length; i++) {
            // check for the rows
            if (arr[i][0] != ' ' && (arr[i][0] == arr[i][1]) && (arr[i][1] == arr[i][2])) {
                return 1;
            }
            // check for the columns
            if (arr[0][i] != ' ' && (arr[0][i] == arr[1][i]) && (arr[1][i] == arr[2][i])) {
                return 1;
            }
        }

        // check for TIE
        if (round == 8) {
            return 404;
        }

        return 0;
    }

    static int playerTurn (int round) {
        return round % 2 + 1;
    }

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        char[][] arr = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = ' ';
            }
        }

        // Setting the symbols for both the players
        System.out.print("PLAYER 1, Enter your chosen symbol from 'o' and 'x': ");
        char playerOne_value = read.next().charAt(0);

        while (playerOne_value != 'o' && playerOne_value != 'x') {
            System.out.print("[INVALID-INPUT] Enter valid value: ");
            playerOne_value = read.next().charAt(0);
        }
        char playerTwo_value = (playerOne_value == 'o') ? 'x' : 'o';

        // Printing the board once for the first player
        board(arr);

        /* Game Loop */
        int round = 0;
        while (round < 9) {
            System.out.println("-----------------------------------------------");
            System.out.println("[ROUND : " + round + "]\n");


            // Alternating between Player 1 and Player 2
            System.out.print("Player " + playerTurn(round) + ": Enter the cell number (1 to 9): ");

            // Location input
            int location = read.nextInt() - 1;
            if (location < 0 || location > 8) { // Handling invalid location input
                while (location < 0 || location > 8) {
                    System.out.print("[INVALID-INPUT] Enter correct location (1-9): ");
                    location = read.nextInt() - 1;
                }
            }

            // Checking if input cell is already filled
            while (arr[location / 3][location % 3 ] != ' ') {
                System.out.println("[INVALID-MOVE] The entered Location is already filled");
                System.out.print("Player " + playerTurn(round) + ": Enter the cell number (1 to 9): ");
                location = read.nextInt() - 1; // Location input
            }

            // Putting the 'o' or 'x' inside the cell
            arr[location / 3][location % 3] = (round % 2 == 0) ? playerOne_value : playerTwo_value;

            // Print the board with Decoration
            board(arr);

            /* GAME WIN LOGIC WILL BE PLACED HERE */
            int condition = gameWin(arr, round);
            if (condition == 1) {
                System.out.println("Player " + playerTurn(round) + " WINS the GAME !!");
                return;
            }
            else if (condition == 404) {
                System.out.println("The Game is TIED");
                return;
            }

            // increment the round number
            round++;
        }
    }
}

/*      => TODO-list <=
 *   -> [IMPORTANT] Create a script or Test that runs all possible test cases and gives Summary
 *   -> [DONE] Set symbols for both Players
 *   -> [DONE] Board Print with decoration
 *   -> [DONE] Player Location Input
 *   -> [DONE] Handling cell already filled logic
 *   -> [DONE] Game Win logic with TIE
 *   -> [DIFFICULT TO-DO] Decorate the Board with the WIN condition
 *   -> [DONE-KINDA]Exception Handling for invalid data type input
 *   -> [OPTIONAL] Refine Decorations for every round (starting borders, etc)
*/
