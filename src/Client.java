import bean.User;

import java.util.*;

public class Client {
    public static void main(String[] args) {

//        Map<String, User> users = new HashMap<>();    // 用户名对应User对象

        Scanner scanner = new Scanner(System.in);
        User user = new User("小明");
        user.generate50Questions();
        System.out.printf("共%d道题\n", user.getQuestionCount());
        user.enterAnswer(scanner, 0, 5);
        scanner.close();
        user.calculateScore();
        System.out.printf("你做对了%d道题，你的成绩是%d\n",user.getRightCount(), user.getScore());
        System.out.print("你答对了的题目：");
        int[] answerSituation = user.getAnswerSituation();
        for (int i = 0; i < answerSituation.length; i++) {
            if (answerSituation[i] == 1) {
                System.out.print((i+1) + " ");
            }
        }
    }
}
