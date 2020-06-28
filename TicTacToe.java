
import java.util.*;
public class Test {
    public static int playerturn = 1;
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        char [][]arr = new char[3][3];
        for(int x = 0; x < 3; x++){
            for(int j = 0; j < 3; j++){
                arr[x][j] = '_';
            }
        }
        aiMove(arr);
        while(checkForWin(arr).equals("not")) {
            boolean check = false;
            while (!check) {
                if(!checkForWin(arr).equals("not")){
                    break;
                }
                printBoard(arr);
                System.out.println("Enter the coordinates");
                String input = reader.nextLine();
                if(input.length() == 3) {
                    if(Character.isDigit(input.charAt(0)) && Character.isDigit(input.charAt(2)) &&
                            input.charAt(1) == ' ') {
                        int x = Character.getNumericValue(input.charAt(0));
                        int y = Character.getNumericValue(input.charAt(2));
                        if (x >= 1 && x <= 3 && y >= 1 && y <= 3) {
                            if (!isOccupied(x, y, arr)) {
                                placeX(x, y, arr);
                                check = true;
                            } else {
                                System.out.println("This cell is occupied! Choose another one!");
                            }
                        } else {
                            System.out.println("Coordinates should be from 1 to 3!");
                        }
                    } else {
                        System.out.println("You should enter numbers!");
                    }
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            }
            aiMove(arr);
        }
        printBoard(arr);
        result(arr);
    }

    public static void placeX(int a, int b, char[][] arr){
        arr[3-b][a-1] = 'X';
    }

    public static void placeY(int a, int b, char[][] arr){
        arr[3-b][a-1] = 'O';
    }

    public static void placeLogicalY(int a, int b, char[][] arr){
        arr[a][b] = 'O';
    }

    public static void aiMove(char[][] arr){
        if(!checkForWin(arr).equals("not")){
            return;
        }
        String attack = attackPlayer(arr);
        if(attack != null){
            placeLogicalY(Character.getNumericValue(attack.charAt(0)),
                    Character.getNumericValue(attack.charAt(2)), arr);
            return;
        }
        String logic = counterPlayer(arr);
        if(logic != null){
            placeLogicalY(Character.getNumericValue(logic.charAt(0)),
                    Character.getNumericValue(logic.charAt(2)), arr);
            return;
        }
        Random random = new Random();
        while(true){
            int x = random.nextInt(3)+1;
            int y = random.nextInt(3)+1;
            if(!isOccupied(x,y,arr)){
                placeY(x,y,arr);
                break;
            }
        }
    }

    public static boolean isOccupied(int a, int b, char[][] arr){
        return arr[3 - b][a - 1] != '_';
    }

    public static void result(char[][] arr){
        String fin = checkForWin(arr);
        switch(fin){
            case "x":
                System.out.println("X wins");
                break;
            case "y":
                System.out.println("O wins");
                break;
            case "tie":
                System.out.println("Draw");
                break;
        }
    }

    public static String attackPlayer(char[][] arr){
        char X = 'O';
        int count = 0;
        //checks player rows
        for(int x = 0; x < 3; x++){
            if(arr[count][0] == X && arr[count][1] == X && arr[count][2] == '_') {
                return  String.valueOf(count) + " 2";
            }else if(arr[count][0] == X && arr[count][2] == X && arr[count][1] == '_'){
                return String.valueOf(count) + " 1";
            }else if(arr[count][1] == X && arr[count][2] == X && arr[count][0] == '_'){
                return String.valueOf(count) + " 0";
            }
            count += 1;
        }

        //checks player columns
        count = 0;
        for(int x = 0; x < 3; x++){
            if(arr[0][count] == X && arr[1][count] == X && arr[2][count] == '_'){
                return "2 " + String.valueOf(count);
            }else if(arr[0][count] == X && arr[2][count] == X && arr[1][count] == '_'){
                return "1 " + String.valueOf(count);
            }else if(arr[1][count] == X && arr[2][count] == X && arr[0][count] == '_'){
                return "0 " + String.valueOf(count);
            }
            count += 1;
        }

        //checks player diagonals
        if((arr[2][0] == X && arr[0][2] == X && arr[1][1] == '_') ||
                (arr[0][0] == X && arr[2][2]== X && arr[1][1] == '_')){
            return "1 1";
        }else if(arr[0][0] == X && arr[1][1] == X && arr[2][2] == '_'){
            return "2 2";
        }else if(arr[2][0] == X && arr[1][1]== X && arr[0][2] == '_'){
            return "0 2";
        }else if(arr[2][2] == X && arr[1][1] == X && arr[0][0] == '_'){
            return "0 0";
        }

        return null;

    }

    public static String counterPlayer(char[][] arr){
        char X = 'X';
        int count = 0;
        //checks player rows
        for(int x = 0; x < 3; x++){
            if(arr[count][0] == X && arr[count][1] == X && arr[count][2] == '_') {
                return  String.valueOf(count) + " 2";
            }else if(arr[count][0] == X && arr[count][2] == X && arr[count][1] == '_'){
                return String.valueOf(count) + " 1";
            }else if(arr[count][1] == X && arr[count][2] == X && arr[count][0] == '_'){
                return String.valueOf(count) + " 0";
            }
            count += 1;
        }

        //checks player columns
        count = 0;
        for(int x = 0; x < 3; x++){
            if(arr[0][count] == X && arr[1][count] == X && arr[2][count] == '_'){
                return "2 " + String.valueOf(count);
            }else if(arr[0][count] == X && arr[2][count] == X && arr[1][count] == '_'){
                return "1 " + String.valueOf(count);
            }else if(arr[1][count] == X && arr[2][count] == X && arr[0][count] == '_'){
                return "0 " + String.valueOf(count);
            }
            count += 1;
        }

        //checks player diagonals
        if((arr[2][0] == X && arr[0][2] == X && arr[1][1] == '_') ||
                (arr[0][0] == X && arr[2][2]== X && arr[1][1] == '_')){
            return "1 1";
        }else if(arr[0][0] == X && arr[1][1] == X && arr[2][2] == '_'){
            return "2 2";
        }else if(arr[2][0] == X && arr[1][1]== X && arr[0][2] == '_'){
            return "0 2";
        }else if(arr[2][2] == X && arr[1][1] == X && arr[0][0] == '_'){
            return "0 0";
        }

        return null;

    }

    public static String checkForWin(char[][] arr){
        boolean X = false;
        boolean O = false;

        //check rows
        int count = 0;
        for(int x = 0; x < 3; x++){
            if(arr[count][0] == arr[count][1] && arr[count][0]== arr[count][2]) {
                if(arr[count][0] == 'X'){
                    X = true;
                } else if(arr[count][0] == 'O'){
                    O = true;
                }
            }
            count += 1;
        }

        //check columns
        count = 0;
        for(int x = 0; x < 3; x++){
            if(arr[0][count] == arr[1][count] && arr[0][count] == arr[2][count]){
                if(arr[0][count] == 'X'){
                    X = true;
                } else if(arr[0][count] == 'O'){
                    O = true;
                }
            }
            count += 1;
        }

        //check diagonals
        if((arr[0][0] == arr[1][1] && arr[0][0] == arr[2][2]) || (arr[0][2] == arr[1][1]
            && arr[0][2] == arr[2][0])){
            if(arr[1][1] == 'X'){
                X = true;
            } else if(arr[1][1] == 'O'){
                O = true;
            }
        }

        if(X){
            return "x";
        }else if(O){
            return "y";
        }else if(getDashCount(arr) > 0){
            return "not";
        }else{
            return "tie";
        }
    }

    public static int getDashCount(char[][] arr){
        int count = 0;
        for(int x = 0; x < 3; x++){
            for(int j = 0; j < 3; j++){
                if(arr[x][j] == '_'){
                    count += 1;
                }
            }
        }
        return count;
    }

    public static void printBoard(char[][] arr){
        System.out.println("---------");
        for(int x = 0; x < 3; x++){
            System.out.print("| ");
            for(int j = 0; j < 3; j++){
                System.out.print(arr[x][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }

}
