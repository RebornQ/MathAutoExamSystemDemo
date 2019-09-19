package bean;

public class UserQuestion {

    private Question question;  // 用户问题

    private int userAnswer = -1; // 用户答案

    public UserQuestion() {
        question = new Question();
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(int userAnswer) {
        this.userAnswer = userAnswer;
    }

    /**
     * 检查答案是否正确
     */
    public boolean checkAnswer() {
        return userAnswer == question.getResult();
    }
}
