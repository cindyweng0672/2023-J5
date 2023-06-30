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
        System.out.println(wordHunt(target, grid));
    }

    public static int wordHunt(String target, String[][] g) {
        int num = 0;
        String s = target.substring(0, 1);
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[0].length; j++) {
                if (g[i][j].equals(s)) {
                    int[] steps = { 0, -1, 1 };
                    for (int k = 0; k < steps.length; k++) {
                        for (int l = 0; l < steps.length; l++) {
                            if (steps[k] == 0 && steps[l] == 0) {
                                continue;
                            }
                            if(check(i, j, target, g, steps[k], steps[l])){
                                num++;
                            }
                        }
                    }

                }
            }
        }
        return num;
    }

    public static boolean check(int i, int j, String target, String[][] g, int stepX, int stepY) {
        int index = 1;
        int roomRequiredX = 0;
        int roomRequiredY = 0;

        roomRequiredX = j + stepX * target.length() - stepX;
        roomRequiredY = i + stepY * target.length() - stepY;

        int currentR = i;
        int currentC = j;

        if (roomRequiredX < g[0].length && roomRequiredX >= 0 && roomRequiredY < g.length && roomRequiredY >= 0) {
            for (int count = 0; count < target.length() - 1; count++) {
                currentR += stepY;
                currentC += stepX;
                String checkingCell = g[currentR][currentC];
                String targetLetter = target.substring(index);
                if (index < target.length()) {
                    targetLetter = target.substring(index, index + 1);
                }
                boolean matched = targetLetter.equals(checkingCell);
                if (matched) {
                    index++;
                } else {
                    return false;
                }
            }

            if (index == target.length()) {
                return true;
            }
        }

        return false;
    }

}
