import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String target = sc.nextLine();
        int r = Integer.parseInt(sc.nextLine());
        int c = Integer.parseInt(sc.nextLine());
        String[][] grid = new String[r][c];

        for (int i = 0; i < r; i++) {
            String str = sc.nextLine();
            String[] temp = str.split(" ");
            for (int j = 0; j < c; j++) {
                grid[i][j] = temp[j];
            }

        }
        sc.close();

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(horizontalCheck(target, grid));
    }

    public static int horizontalCheck(String target, String[][] g) {
        int num = 0;
        String s = target.substring(0, 1);
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[0].length; j++) {
                if (g[i][j].equals(s)) {
                    if (check(i, j, target, g, "hr")) {
                        num++;
                    }
                    if (check(i, j, target, g, "hl")) {
                        num++;
                    }
                    if (check(i, j, target, g, "vd")) {
                        num++;
                    }
                    if (check(i, j, target, g, "vu")) {
                        num++;
                    }
                    /*
                     * int index = 1;
                     * if ((j + target.length()) <= g[0].length + 1) {
                     * for (int k = j + 1; k < j + target.length(); k++) {
                     * if (!g[i][k].equals(target.substring(index, index + 1))) {
                     * break;
                     * } else {
                     * index++;
                     * }
                     * }
                     * if (index == target.length()) {
                     * num++;
                     * }
                     * }
                     * 
                     * index = 1;
                     * if ((j - target.length()) >= -1) {
                     * for (int k = j - 1; k > j - target.length(); k--) {
                     * if (!g[i][k].equals(target.substring(index, index + 1))) {
                     * break;
                     * } else {
                     * index++;
                     * }
                     * }
                     * if (index == target.length()) {
                     * num++;
                     * }
                     * }
                     * 
                     * index = 1;
                     * if ((i + target.length()) <= g.length + 1) {
                     * for (int k = i + 1; k < i + target.length(); k++) {
                     * if (!g[k][j].equals(target.substring(index, index + 1))) {
                     * break;
                     * } else {
                     * index++;
                     * }
                     * }
                     * if (index == target.length()) {
                     * num++;
                     * }
                     * }
                     * 
                     * index = 1;
                     * if ((i - target.length()) >= -1) {
                     * for (int k = i - 1; k > i - target.length(); k--) {
                     * if (!g[k][j].equals(target.substring(index, index + 1))) {
                     * break;
                     * } else {
                     * index++;
                     * }
                     * }
                     * if (index == target.length()) {
                     * num++;
                     * }
                     * }
                     */

                }
            }
        }
        return num;
    }

    public static boolean check(int i, int j, String target, String[][] g, String direction) {
        int index = 1;
        int roomRequiredX = 0;
        int roomRequiredY = 0;

        int stepx = 0;
        int stepy = 0;

        if (direction.equals("hr")) {
            stepx = 1;
        } else if (direction.equals("hl")) {
            stepx = -1;
        } else if (direction.equals("vd")) {
            stepy = 1;
        } else if (direction.equals("vu")) {
            stepy = -1;
        }

        roomRequiredX = j + stepx * target.length() - stepx;
        roomRequiredY = i + stepy * target.length() - stepy;

        if (roomRequiredX < g.length && roomRequiredX >= 0 && roomRequiredY < g[0].length && roomRequiredY >= 0) {
            for (int count = 0; count < target.length()-1; count++) {
                String checkingCell = g[i + stepy*(count+1)][j + stepx*(count+1)];
                boolean matched = false;
                if (index < target.length()) {
                    matched = checkingCell.equals(target.substring(index, index + 1));
                } else {
                    matched = checkingCell.equals(target.substring(index));
                }
                if(matched){
                    index++;
                }else{
                    break;
                }
            }

            if(index==target.length()){
                return true;
            }
            /*
             * for (int r = i + stepy; !(r > roomRequiredY + stepy || r < 0); r += stepy) {
             * for (int c = j + stepx; !(c > j + roomRequiredX + stepx || c < 0); c +=
             * stepx) {
             * String checkingCell = g[r][c];
             * boolean matched=false;
             * if(index<4){
             * matched=checkingCell.equals(target.substring(index, index + 1));
             * }else{
             * matched=checkingCell.equals(target.substring(index));
             * }
             * if (!matched) {
             * break;
             * } else {
             * index++;
             * }
             * }
             * if (stepy == 0) {
             * break;
             * }
             * }
             */
        }

        /*
         * if ((vh + roomRequired) <= roomHad) {
         * for (int k = vh + indexDirection; k < vh + roomRequired; k += indexDirection)
         * {
         * String checkingCell = "";
         * if (direction.equals("hl") || direction.equals("hr")) {
         * checkingCell = g[i][k];
         * } else {
         * checkingCell = g[k][j];
         * }
         * if (!checkingCell.equals(target.substring(index, index + 1))) {
         * break;
         * } else {
         * index++;
         * }
         * }
         * if (index == target.length()) {
         * return true;
         * }
         * }
         */

        return false;
    }

}
