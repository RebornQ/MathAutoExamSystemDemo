package bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class User {

    private String username;    // 用户名

    private String grade;   // 年级

    private String clazz;   // 班级

    private List<UserQuestion> userQuestions;   // 用户的题目

    private int questionCount = 0;  // 题目数

    private int score = 0;  // 用户的成绩

    private int rightCount = 0; // 答对的题数

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public List<UserQuestion> getUserQuestions() {
        return userQuestions;
    }

    public void setUserQuestions(List<UserQuestion> userQuestions) {
        this.userQuestions = userQuestions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRightCount() {
        return rightCount;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    /**
     * 随机生成n道题目
     */
    public void generateQuestions(int n) {
        userQuestions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Random random = new Random();
            int[] nums = new int[3];
            int[] operationType = new int[2];
            UserQuestion userQuestion = new UserQuestion();
            Question question = userQuestion.getQuestion();

            // 随机生成三个数
            for (int j = 0; j < 3; j++) {
                nums[j] = random.nextInt(101);
            }
            question.setNums(nums);

            // 随机生成两个运算符
            for (int j = 0; j < 2; j++) {
                operationType[j] = random.nextInt(2);
            }
            question.setOperationTypes(operationType);

            // 判断是否符合题目规则，不符合则跳过不添加进待做题目列表里
            if (question.canBeQuestion()) {
                question.setID(i + 1);
                userQuestions.add(userQuestion);
            } else {
                i--;    // 生成的题目不符合规则（运算结果 < 0），所以跳过
            }
        }
        questionCount = userQuestions.size();
    }

    /**
     * 生成50道题目
     */
    public void generate50Questions() {
        generateQuestions(50);
    }

    /**
     * 输入答案（非递归法）
     *
     * @param answers 外部传入的答案数组
     */
    public void enterAnswer(int[] answers) {
        for (int i = 0; i < answers.length; i++) {
            userQuestions.get(i).setUserAnswer(answers[i]);
        }
    }

    /**
     * 计算该用户的成绩
     *
     * @return 用户成绩
     */
    public int calculateScore() {
        score = 0;  // 每次计算都初始化为0，避免重复计算的时候累加成绩
        rightCount = 0;
        // 迭代 userQuestions 对象
        for (int i = 0; i < userQuestions.size(); i++) {
            UserQuestion userQuestion = userQuestions.get(i);
            if (userQuestion.checkAnswer()) {
                score = score + userQuestion.getQuestion().SCORE;
                rightCount++;
            }
        }
        return score;
    }

    @Override
    public String toString() {
        return username + " " + grade + " " + clazz + " " + score + "\r\n";
    }

}
