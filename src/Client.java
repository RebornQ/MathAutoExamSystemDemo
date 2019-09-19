import bean.User;
import gui.FrameTest;

public class Client {

    public static void main(String[] args) {

        // 声明用户
        User user = new User();
        new FrameTest(user);

        // 生成题目
        user.generate50Questions();
    }
}
