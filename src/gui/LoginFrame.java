package gui;

import bean.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame {

    private JFrame jFrame = new JFrame("登录");//创建一个窗口标题为“登录”的窗口框架
    private Container c = jFrame.getContentPane();//初始化容器
    private JLabel usernameLabel = new JLabel("姓名");//“JLabel”标签组件，不对输入事件作出反应，无法获得键盘焦点
    private JTextField usernameTextField = new JTextField();//“JTextField”文本框，接收用户键盘输入
    private JLabel gradeLabel = new JLabel("年级");
    private JTextField gradeTextField = new JTextField();
    private JLabel classLabel = new JLabel("班级");
    private JTextField clazzTextField = new JTextField();
    private JButton okbtn = new JButton("确定");//按钮
    private User user;


    public LoginFrame(User user) {
        this.user = user;
        //设置窗体的位置及大小
        jFrame.setBounds(550, 230, 300, 220);
        //设置一层相当于桌布的东西
        c.setLayout(new BorderLayout());//布局管理器
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


        /*标题部分--North*/
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(new JLabel("登记学生信息"));
        c.add(titlePanel, "North");

        /*输入部分--Center*/
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(null);
        usernameLabel.setBounds(50, 20, 50, 20);
        gradeLabel.setBounds(50, 40, 50, 20);
        classLabel.setBounds(50, 60, 50, 20);
        fieldPanel.add(usernameLabel);
        fieldPanel.add(gradeLabel);
        fieldPanel.add(classLabel);
        usernameTextField.setBounds(110, 20, 120, 20);
        gradeTextField.setBounds(110, 40, 120, 20);
        clazzTextField.setBounds(110, 60, 120, 20);
        fieldPanel.add(usernameTextField);
        fieldPanel.add(gradeTextField);
        fieldPanel.add(clazzTextField);
        c.add(fieldPanel, "Center");

        /*按钮部分--South*/
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(okbtn);
        c.add(buttonPanel, "South");

        okbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = getUsernameText();
                String grade = getGradeText();
                String clazz = getClazzText();
                if ((username == null || clazz == null || grade == null) || (username.equals("") || grade.equals("") || clazz.equals(""))) {
                    JOptionPane.showMessageDialog(null, "请填写内容");
                } else {
                    user.setUsername(username);
                    user.setGrade(grade);
                    user.setClazz(clazz);
                    new MainFrame(user);
                    jFrame.dispose();
                }
            }
        });
    }

    public String getUsernameText() {
        return usernameTextField.getText();
    }

    public String getGradeText() {
        return gradeTextField.getText();
    }

    public String getClazzText() {
        return clazzTextField.getText();
    }
}
