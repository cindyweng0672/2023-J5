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
                    if(check(i, j, target, g, "hr")){
                        num++;
                    }
                    if(check(i, j, target, g, "hl")){
                        num++;
                    }
                    if(check(i, j, target, g, "vd")){
                        num++;
                    }
                    if(check(i, j, target, g, "vu")){
                        num++;
                    }
                    /*int index = 1;
                    if ((j + target.length()) <= g[0].length + 1) {
                        for (int k = j + 1; k < j + target.length(); k++) {
                            if (!g[i][k].equals(target.substring(index, index + 1))) {
                                break;
                            } else {
                                index++;
                            }
                        }
                        if (index == target.length()) {
                            num++;
                        }
                    }

                    index = 1;
                    if ((j - target.length()) >= -1) {
                        for (int k = j - 1; k > j - target.length(); k--) {
                            if (!g[i][k].equals(target.substring(index, index + 1))) {
                                break;
                            } else {
                                index++;
                            }
                        }
                        if (index == target.length()) {
                            num++;
                        }
                    }

                    index = 1;
                    if ((i + target.length()) <= g.length + 1) {
                        for (int k = i + 1; k < i + target.length(); k++) {
                            if (!g[k][j].equals(target.substring(index, index + 1))) {
                                break;
                            } else {
                                index++;
                            }
                        }
                        if (index == target.length()) {
                            num++;
                        }
                    }

                    index = 1;
                    if ((i - target.length()) >= -1) {
                        for (int k = i - 1; k > i - target.length(); k--) {
                            if (!g[k][j].equals(target.substring(index, index + 1))) {
                                break;
                            } else {
                                index++;
                            }
                        }
                        if (index == target.length()) {
                            num++;
                        }
                    }*/

                }
            }
        }
        return num;
    }

    public static boolean check(int i, int j, String target, String[][] g, String direction) {
        int index = 1;
        int roomRequired = 0;
        int roomHad = 0;
        int indexDirection=0;
        int vh=j;

        if (direction.equals("hr") || direction.equals("vd")) {
            roomRequired = target.length();
            indexDirection=1;
            if (direction.equals("hr")) {
                roomHad=g[0].length;
            } else {
                roomHad=g.length;
                vh=i;
            }
        } else {
            roomRequired = -target.length();
            indexDirection=-1;
            roomHad=-1;
            if(direction.equals("vu")){
                vh=j;
            }
        }

        if ((vh + roomRequired) <= roomHad) {
            for (int k = vh + indexDirection; k < vh + target.length(); k+=indexDirection) {
                String checkingCell="";
                if(direction.equals("hl")||direction.equals("hr")){
                    checkingCell=g[i][k];
                }else{
                    checkingCell=g[k][j];
                }
                if (!checkingCell.equals(target.substring(index, index + 1))) {
                    break;
                } else {
                    index++;
                }
            }
            if (index == target.length()) {
                return true;
            }
        }
        return false;
    }
}
