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
                            num += check(i, j, target, g, steps[k], steps[l], true);
                        }
                    }

                }
            }
        }
        return num;
    }

    public static int check(int i, int j, String target, String[][] g, int stepX, int stepY, boolean turnable) {
        int wordFound = 0;
        int index = 1;
        // int roomRequiredX = 0;
        // int roomRequiredY = 0;

        // roomRequiredX = j + stepX * target.length() - stepX;
        // roomRequiredY = i + stepY * target.length() - stepY;

        int currentR = i;
        int currentC = j;

        // if (roomRequiredX < g[0].length && roomRequiredX >= 0 && roomRequiredY <
        // g.length && roomRequiredY >= 0) {
        for (int count = 0; count < target.length() - 1; count++) {
            currentR += stepY;
            currentC += stepX;
            if (currentR < g.length && currentR>-1 && currentC < g[0].length && currentC>-1) {
                String checkingCell = g[currentR][currentC];
                String targetLetter = target.substring(index);
                if (index < target.length()) {
                    targetLetter = target.substring(index, index + 1);
                }

                boolean matched = targetLetter.equals(checkingCell);

                if (turnable && matched && index != target.length() - 1) {
                    int[][] nextSteps = { { -stepY, stepX }, { stepY, -stepX } };
                    for (int c = 0; c < 2; c++) {
                        String nextTarget = target.substring(index);
                        int temp = check(currentR, currentC, nextTarget, g, nextSteps[c][0],
                                nextSteps[c][1], false);
                        wordFound += temp;
                    }
                }

                if (matched) {
                    index++;
                } else {
                    break;
                }
            }

            if (index == target.length()) {
                wordFound++;
            }
        }
        // }

        return wordFound;
    }

}
