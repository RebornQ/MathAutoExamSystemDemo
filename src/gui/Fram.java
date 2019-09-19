package gui;

import bean.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fram {

    private static int start = 0, end = 5;

    private JFrame jFrame = new JFrame("题目");//创建一个窗口标题为“题目”的窗口框架
    private Container c = jFrame.getContentPane();//初始化容器
    private User user;
    private JLabel[] questionLabel = new JLabel[5];
    private JTextField[] answerTextField = new JTextField[5];

    public Fram(User user) {
        this.user = user;
        //设置窗体的位置及大小
        jFrame.setBounds(550, 230, 300, 220);
        //设置一层相当于桌布的东西
        c.setLayout(new BorderLayout());//布局管理器
        //设置按下右上角X号后关闭
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //初始化--往窗体里放其他控件
        init();
        //设置窗体可见
        jFrame.setVisible(true);
    }

    public void init() {

        /*标题部分--North*/
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(new JLabel(user.getGrade() + "年级" + user.getClazz() + "班" + user.getUsername() + " 正在答题"));
        c.add(titlePanel, "North");

        JPanel questionpanel = new JPanel();
        questionpanel.setLayout(null);
        c.add(questionpanel, "Center");

        // 初始化控件数组
        if (user.getUserQuestions() != null) {
            for (int i = 0; i < 5; i++) {
                questionLabel[i] = new JLabel(user.getUserQuestions().get(i).getQuestion().toString());
                answerTextField[i] = new JTextField();
                questionLabel[i].setBounds(50, (i + 1) * 20, 150, 20);
                questionpanel.add(questionLabel[i]);
                answerTextField[i].setBounds(150, (i + 1) * 20, 50, 20);
                questionpanel.add(answerTextField[i]);
            }
        }

        /*按钮部分--South*/
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        // 执行操作：输入题目的答案 然后点击下一页
        JButton next = new JButton("下一页");
        JButton sbbtn = new JButton("提交");
        buttonPanel.add(next);
        buttonPanel.add(sbbtn);
        c.add(buttonPanel, "South");

        sbbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // 存最后一页答案
                enterAnswer(start, end, false);

                user.calculateScore();
                JOptionPane.showMessageDialog(null,
                        "答对 " + user.getRightCount() + " 题\n"
                                + "成绩是：" + user.getScore());
            }
        });

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (end > user.getQuestionCount()) {
                    return;
                }

                // 存答案
                enterAnswer(start, end, true);

                start = start + 5;
                end = end + 5;

                // 显示下一页的内容
                // 给控件重新赋值
                for (int i = 0, j = start; i < 5; i++, j++) {//start是为了控制从第几题开始显示
                    questionLabel[i].setText(user.getUserQuestions().get(j).getQuestion().toString());
                }//显示变的五道题

                if (end == user.getQuestionCount()) {
                    next.setEnabled(false);
                }
            }

        });
    }

    private void enterAnswer(int start, int end, boolean cleanTextFlag) {   // cleanTextFlag清空是否答案输入框的标志
        // 存答案
        int[] answers = new int[5];   // 50题每一页答案暂存区
        for (int i = 0; i < 5; i++) {
            try {
                answers[i] = Integer.parseInt(answerTextField[i].getText());
            } catch (NumberFormatException e1) {
                answers[i] = -1;    // 避免数组初始化为0且发生类型转换错误时答案输入为0
                e1.printStackTrace();
            }
            if (cleanTextFlag) {
                answerTextField[i].setText("");
            }
        }
        user.enterAnswer(answers, start, end);//存答案
    }
}

