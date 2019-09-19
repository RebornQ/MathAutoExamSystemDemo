package gui;

import bean.User;
import utils.IOUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fram {
    private User user;
    private JFrame jFrame = new JFrame("题目");//创建一个窗口标题为“题目”的窗口框架
    private Container c = jFrame.getContentPane();//初始化容器
    private JLabel[][] questionLabels;
    private JTextField[][] answerTextFields;
    private CardLayout cardLayout;

    private int panelCount = 0;
    private int page = 1;

    private int[] answers;

    private static final String FILE_PATH = "/Users/reborn/Desktop/成绩.txt";

    public Fram(User user) {
        this.user = user;
        //设置窗体的位置及大小
        jFrame.setBounds(550, 230, 300, 270);
        //设置一层相当于桌布的东西
        c.setLayout(new BorderLayout());//布局管理器
        //设置按下右上角X号后关闭
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //初始化--往窗体里放其他控件
        init();
        //设置窗体可见
        jFrame.setVisible(true);
        // 设置点击关闭窗口后的操作，这里是设置窗口关闭则关闭程序
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置禁止窗口放大缩小，防止改变窗口大小后布局变型
        jFrame.setResizable(false);
    }

    public void init() {
        answers = new int[user.getQuestionCount()];
        IOUtil.createFile(FILE_PATH);

        /*标题部分--North*/
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(new JLabel(user.getGrade() + "年级" + user.getClazz() + "班" + user.getUsername() + " 正在答题"));
        c.add(titlePanel, "North");

        JPanel questionPanel = new JPanel();
        cardLayout = new CardLayout(5, 5);  // 创建一个水平间距和垂直间距均为5的卡片布局
        questionPanel.setLayout(cardLayout);
        panelCount = user.getQuestionCount() / 5;   // 面板数量，5为每页面板要显示的题目数量
        JPanel[] questionPanels = new JPanel[panelCount];
        questionLabels = new JLabel[panelCount][5];
        answerTextFields = new JTextField[panelCount][5];
        c.add(questionPanel, "Center");

        // 初始化控件数组
        if (user.getUserQuestions() != null) {
            for (int i = 0; i < panelCount; i++) {
                questionPanels[i] = new JPanel();
                questionPanels[i].setLayout(null);
                for (int j = 0; j < 5; j++) {
                    questionLabels[i][j] = new JLabel(user.getUserQuestions().get(5 * i + j).getQuestion().toString());
                    answerTextFields[i][j] = new JTextField();
                    questionLabels[i][j].setBounds(50, (j + 1) * 20, 150, 20);
                    questionPanels[i].add(questionLabels[i][j]);
                    answerTextFields[i][j].setBounds(190, (j + 1) * 20, 50, 20);
                    questionPanels[i].add(answerTextFields[i][j]);
                }
                questionPanel.add(questionPanels[i]);
            }
        }

        /*按钮部分--South*/
        JPanel buttonPanel = new JPanel();
        JPanel buttonPanel1 = new JPanel();
        JPanel buttonPanel2 = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel1.setLayout(new FlowLayout());
        buttonPanel2.setLayout(new FlowLayout());
        // 执行操作：输入题目的答案 然后点击下一页
        JButton previous = new JButton("上一页");
        JLabel pageLabel = new JLabel(page + "/" + panelCount);
        JButton next = new JButton("下一页");
        JButton sbbtn = new JButton("提交");
        buttonPanel1.add(previous);
        buttonPanel1.add(pageLabel);
        buttonPanel1.add(next);
        buttonPanel2.add(sbbtn);
        buttonPanel.add(buttonPanel1);
        buttonPanel.add(buttonPanel2);
        c.add(buttonPanel, "South");
        previous.setEnabled(false);

        sbbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // 存最后一页答案
                enterAnswers();

                user.calculateScore();

                IOUtil.writeLine(FILE_PATH, user.toString());

                JOptionPane.showMessageDialog(null,
                        "答对 " + user.getRightCount() + " 题\n"
                                + "成绩是：" + user.getScore());
            }
        });

        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page--;
                pageLabel.setText(page + "/" + panelCount);
                // 存答案
                enterAnswers();

                // 显示上一页的内容
                cardLayout.previous(questionPanel);
                next.setEnabled(true);

                if (page == 1) {
                    previous.setEnabled(false);
                }
            }
        });

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page++;
                pageLabel.setText(page + "/" + panelCount);

                // 存答案
                enterAnswers();

                // 显示下一页的内容
                cardLayout.next(questionPanel);
                previous.setEnabled(true);

                if (page == panelCount) {
                    next.setEnabled(false);
                }
            }

        });
    }

    private void enterAnswers() {
        for (int i = 0; i < panelCount; i++) {
            for (int j = 0; j < 5; j++) {
                try {
                    answers[5 * i + j] = Integer.parseInt(answerTextFields[i][j].getText());
                } catch (NumberFormatException e1) {
                    answers[5 * i + j] = -1;    // 避免数组初始化为0且发生类型转换错误时答案输入为0
                    e1.printStackTrace();
                }
            }
        }
        user.enterAnswer(answers);  //存答案
    }
}

