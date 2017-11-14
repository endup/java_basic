import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
 
public class Login extends JFrame {
    private JPanel contentPane;
    private JTextField userField;
    private JTextField pwdField;
 
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 249, 180);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        JLabel lblUsername = new JLabel("�û���");
        lblUsername.setBounds(12, 13, 54, 15);
        contentPane.add(lblUsername);
        JLabel lblPassword = new JLabel("����");
        lblPassword.setBounds(12, 38, 54, 15);
        contentPane.add(lblPassword);
        userField = new JTextField();
        userField.setBounds(76, 10, 144, 21);
        contentPane.add(userField);
        userField.setColumns(10);
        pwdField = new JTextField();
        pwdField.setBounds(76, 35, 144, 21);
        contentPane.add(pwdField);
        pwdField.setColumns(10);
        JButton btnNewButton = new JButton("��¼");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	//��½
            	String name=userField.getText();
            	String pwd=pwdField.getText();
       
                checkUser(name,pwd);
            }
        });
        btnNewButton.setBounds(10, 92, 93, 23);
        contentPane.add(btnNewButton);
         
        JButton btnNewButton_1 = new JButton("ע��");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	//ע��
                System.out.println("������������з��С�����");
            }
        });
        btnNewButton_1.setBounds(127, 92, 93, 23);
        contentPane.add(btnNewButton_1);
    }
    public boolean checkUser(String name,String pwd){
    	DBConnection(name,pwd);
    	return true;
    }
    
    private void DBConnection (String n,String p) {
		String url = "jdbc:mysql://127.0.0.1:3306/mydb";   
        Connection conn;    
        Statement stat;  
        ResultSet results;    
        String str; 
        try   {   
            Class.forName("com.mysql.jdbc.Driver"); //����MySQL���� 
            conn = DriverManager.getConnection(url, "root", ""); //������MySQL������ 
            stat =  conn.createStatement();   
            results = stat.executeQuery("select * from   userlogin"); //   SQL   code:   
	        boolean checkLogin = false ;
	        String name = n ;
	        String pwd = p ;
	        String dbname;
	        String dbpwd;
			while(results.next())   {
				dbname = results.getString("name") ;
				dbpwd = results.getString("pwd") ;
				if(name.equals(dbname)){
					if(dbpwd.equals(pwd)){
						System.out.println("��½�ɹ�");
						break;
					}else{
						System.out.println("�������");
						break;
					}
				}else{
					System.out.println("û������û�");
					break;
				}
			}
			
			results.close() ;
			stat.close();   //   Also   closes   ResultSet
			conn.close();
		}   catch(Exception   e)   {
			
		}   
	}

}