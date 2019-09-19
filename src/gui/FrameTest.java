package gui;

import bean.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameTest {

    private JFrame jFrame = new JFrame("登录");//创建一个窗口标题为“登录”的窗口框架
    private Container c = jFrame.getContentPane();//初始化容器
    private JLabel a1 = new JLabel("姓名");//“JLabel”标签组件，不对输入事件作出反应，无法获得键盘焦点
    private JTextField username = new JTextField();//“JTextField”文本框，接收用户键盘输入
    private JLabel a2 = new JLabel("年级");
    private JTextField grade = new JTextField();
    private JLabel a3 = new JLabel("班级");
    private JTextField clazz = new JTextField();
    private JLabel a4 = new JLabel("成绩");
    private JTextField score = new JTextField();
    private JButton okbtn = new JButton("确定");//按钮
    //private JButton cancelbtn = new JButton("取消");
    private User user;


    public FrameTest(User user) {
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
        titlePanel.add(new JLabel("登记学生信息"));
        c.add(titlePanel, "North");

        /*输入部分--Center*/
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(null);
        a1.setBounds(50, 20, 50, 20);
        a2.setBounds(50, 40, 50, 20);
        a3.setBounds(50, 60, 50, 20);
        //   a4.setBounds(50, 80, 50, 20);
        fieldPanel.add(a1);
        fieldPanel.add(a2);
        fieldPanel.add(a3);
        //   fieldPanel.add(a4);
        username.setBounds(110, 20, 120, 20);
        grade.setBounds(110, 40, 120, 20);
        clazz.setBounds(110, 60, 120, 20);
        //   score.setBounds(110, 80, 120, 20);
        fieldPanel.add(username);
        fieldPanel.add(grade);
        fieldPanel.add(clazz);
        fieldPanel.add(score);
        c.add(fieldPanel, "Center");

        /*按钮部分--South*/
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(okbtn);
        //  buttonPanel.add(cancelbtn);
        c.add(buttonPanel, "South");

        okbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = getUsername();
                String grade = getGrade();
                String clazz = getClazz();
                // String score = getScore();
                if ((username == null || clazz == null || grade == null) || (username.equals("") || grade.equals("") || clazz.equals(""))) {
                    JOptionPane.showMessageDialog(null, "请填写内容");
                } else {
                    user.setUsername(username);
                    user.setGrade(grade);
                    user.setClazz(clazz);
                    new Fram(user);
                    jFrame.setVisible(false);
                }
            }
        });
    }

    public String getUsername() {
        return username.getText();
    }

    public String getGrade() {
        return grade.getText();
    }

    public String getClazz() {
        return clazz.getText();
    }

    public String getScore() {
        return score.getText();
    }
}
