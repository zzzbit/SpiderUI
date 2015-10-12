import java.awt.Checkbox;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.Box;
import java.awt.Component;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

import org.eclipse.swt.internal.LONG;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;

public class SpiderUI {

	private JFrame frame;
	private JTextField textStartURL;
	private JTextField textSearchURL;
	private JTextField textRegContentURL;
	private JTextField textRegNextURL;
	private JTextField textContentURL;
	private JTextField textRegPicURL;
	private JTextField textRegDesciption;
	private JTextField textRegPrice;
	private JTextField textPicPath;
	private JTextField textFieldIP;
	private JTextField textFieldPort;
	private JTextField textFieldURLNum;
	private JTextField textFieldTime;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JTextField textFieldDatabaseName;
	private JCheckBox checkBoxAgent;
	private JCheckBox checkBoxNum;
	private JCheckBox checkBoxTime;
	private JRadioButton radioButtonLocal;
	private Spider mySpider;
	private String tmpFileString = "tmp\\"; // 临时文件夹地址，存放源码和测试结果

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpiderUI window = new SpiderUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SpiderUI() {
		// 新建爬虫对象
		mySpider = new Spider();
		new File(tmpFileString).mkdir();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setTitle("\u56FE\u7247\u722C\u866B\u7A0B\u5E8F");
		frame.setBounds(100, 100, 567, 468);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel panel1 = new JPanel();
		tabbedPane.addTab("爬虫主页面", panel1);
		panel1.setLayout(null);

		JLabel lblurl = new JLabel(
				"\u8D77\u59CB\u7F51\u5740\uFF08\u5E26\u641C\u7D22URL\uFF09(*):");
		lblurl.setBounds(23, 22, 159, 15);
		panel1.add(lblurl);

		textStartURL = new JTextField();
		textStartURL.setBounds(193, 19, 326, 21);
		panel1.add(textStartURL);
		textStartURL.setColumns(10);

		JLabel lblurl_1 = new JLabel("\u641C\u7D22\u9875\u9762URL:");
		lblurl_1.setBounds(23, 63, 96, 15);
		panel1.add(lblurl_1);

		textSearchURL = new JTextField();
		textSearchURL.setColumns(10);
		textSearchURL.setBounds(127, 60, 279, 21);
		panel1.add(textSearchURL);

		// 查看搜索页面源码
		JButton buttonSearchSource = new JButton("\u67E5\u770B\u6E90\u7801");
		buttonSearchSource.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (mySpider.addHtmlFile(textSearchURL.getText(), tmpFileString
						+ "searchSourceCode.txt")) {
					try {
						Runtime.getRuntime().exec(
								"iexplore.exe " + tmpFileString
										+ "searchSourceCode.txt");
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "打开源码失败!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "获取源码失败!");
				}
			}
		});
		buttonSearchSource.setBounds(416, 59, 103, 23);
		panel1.add(buttonSearchSource);

		JLabel lblurl_2 = new JLabel(
				"\u5B9A\u4E49\u5185\u5BB9\u9875URL\u6B63\u5219\u5F0F(*):");
		lblurl_2.setBounds(63, 101, 159, 15);
		panel1.add(lblurl_2);

		textRegContentURL = new JTextField();
		textRegContentURL.setColumns(10);
		textRegContentURL.setBounds(232, 98, 287, 21);
		panel1.add(textRegContentURL);

		JLabel lblurl_3 = new JLabel(
				"\u5B9A\u4E49\u4E0B\u4E00\u9875URL\u6B63\u5219\u5F0F(*):");
		lblurl_3.setBounds(63, 129, 159, 15);
		panel1.add(lblurl_3);

		textRegNextURL = new JTextField();
		textRegNextURL.setColumns(10);
		textRegNextURL.setBounds(232, 126, 287, 21);
		panel1.add(textRegNextURL);

		JLabel lblurl_4 = new JLabel("\u5185\u5BB9\u9875\u9762URL:");
		lblurl_4.setBounds(23, 181, 96, 15);
		panel1.add(lblurl_4);

		textContentURL = new JTextField();
		textContentURL.setColumns(10);
		textContentURL.setBounds(127, 178, 279, 21);
		panel1.add(textContentURL);

		// 查看内容页面源码
		JButton buttonContentSource = new JButton("\u67E5\u770B\u6E90\u7801");
		buttonContentSource.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (mySpider.addHtmlFile(textSearchURL.getText(), tmpFileString
						+ "contentSourceCode.txt")) {
					try {
						Runtime.getRuntime().exec(
								"notepad.exe " + tmpFileString
										+ "contentSourceCode.txt");
//						Desktop desktop = Desktop.getDesktop(); 
//						try {
//						    //创建URI统一资源标识符
//						    URI uri = new URI("http://www.bit.edu.cn");
//						    //使用默认浏览器打开超链接
//						    desktop.browse(uri);
//						} catch(Exception ex) {
//						    ex.printStackTrace();
//						}

					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "打开源码失败!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "获取源码失败!");
				}
			}
		});
		buttonContentSource.setBounds(416, 177, 103, 23);
		panel1.add(buttonContentSource);

		JLabel lblurl_5 = new JLabel(
				"\u5B9A\u4E49\u56FE\u7247URL\u6B63\u5219\u5F0F(*):");
		lblurl_5.setBounds(63, 219, 140, 15);
		panel1.add(lblurl_5);

		JLabel label_2 = new JLabel(
				"\u5B9A\u4E49\"\u7B80\u4ECB\"\u6B63\u5219\u5F0F:");
		label_2.setBounds(76, 247, 127, 15);
		panel1.add(label_2);

		textRegPicURL = new JTextField();
		textRegPicURL.setColumns(10);
		textRegPicURL.setBounds(232, 216, 287, 21);
		panel1.add(textRegPicURL);

		textRegDesciption = new JTextField();
		textRegDesciption.setColumns(10);
		textRegDesciption.setBounds(232, 244, 287, 21);
		panel1.add(textRegDesciption);

		JLabel label = new JLabel(
				"\u5B9A\u4E49\"\u4EF7\u683C\"\u6B63\u5219\u5F0F:");
		label.setBounds(76, 275, 127, 15);
		panel1.add(label);

		textRegPrice = new JTextField();
		textRegPrice.setColumns(10);
		textRegPrice.setBounds(232, 272, 287, 21);
		panel1.add(textRegPrice);

		JLabel label_1 = new JLabel("\u56FE\u7247\u4FDD\u5B58\u5730\u5740(*):");
		label_1.setBounds(23, 329, 96, 15);
		panel1.add(label_1);

		textPicPath = new JTextField();
		textPicPath.setColumns(10);
		textPicPath.setBounds(127, 326, 279, 21);
		panel1.add(textPicPath);

		// 打开文件夹
		JButton buttonFilePath = new JButton("\u6D4F\u89C8...");
		buttonFilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser
							.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					fileChooser.setDialogTitle("选择导入路径");
					fileChooser.showOpenDialog(frame);
					fileChooser.setVisible(true);
					textPicPath
							.setText(fileChooser.getSelectedFile().getPath());
				} catch (Exception e) {
				}
			}
		});
		buttonFilePath.setBounds(416, 325, 103, 23);
		panel1.add(buttonFilePath);

		// 搜索页面测试
		JButton buttonSearchTest = new JButton("\u6D4B\u8BD5");
		buttonSearchTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					mySpider.regContentUrl = textRegContentURL.getText();
					mySpider.regNextPageUrl = textRegNextURL.getText();
					mySpider.regPicUrl = textRegPicURL.getText();
					mySpider.regDescription = textRegDesciption.getText();
					mySpider.regPrice = textRegPrice.getText();
					mySpider.testFlag = true;
					mySpider.tmpSearchResult = tmpFileString
							+ "searchTestResult.txt";
					mySpider.getWebByUrl(textSearchURL.getText());
					try {
						Runtime.getRuntime().exec(
								"notepad.exe " + tmpFileString
										+ "searchTestResult.txt");
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "打开测试结果失败!");
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "测试失败!");
				}
			}
		});
		buttonSearchTest.setBounds(160, 148, 72, 23);
		panel1.add(buttonSearchTest);

		// 开始爬虫
		JButton buttonStart = new JButton("\u5F00\u59CB");
		buttonStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mySpider.fileDirectory = textPicPath.getText();
				mySpider.testFlag = false;
				mySpider.regContentUrl = textRegContentURL.getText();
				mySpider.regNextPageUrl = textRegNextURL.getText();
				mySpider.regPicUrl = textRegPicURL.getText();
				mySpider.regDescription = textRegDesciption.getText();
				mySpider.regPrice = textRegPrice.getText();
				// 高级设置
				try {
					if (checkBoxAgent.isSelected()) {
						mySpider.AgentFlag = true;
						mySpider.IpAddress = textFieldIP.getText();
						mySpider.Port = textFieldPort.getText();
					}
					if (checkBoxNum.isSelected()) {
						mySpider.MaxUrlFlag = true;
						mySpider.MaxUrl = Integer.parseInt(textFieldURLNum
								.getText());
					}
					if (checkBoxTime.isSelected()) {
						mySpider.TimeFlag = true;
						mySpider.MaxTime = Integer.parseInt(textFieldTime
								.getText());
					}
					if (radioButtonLocal.isSelected()) {
						mySpider.exportFlag = true;
						mySpider.dbName = textFieldDatabaseName.getText();
						mySpider.usrName = textFieldUsername.getText();
						mySpider.pwd = textFieldPassword.getText();
					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "设置参数错误!");
				}
				mySpider.strHomePage = textStartURL.getText();
				new Thread(mySpider).start();
			}
		});
		buttonStart.setBounds(206, 357, 93, 34);
		panel1.add(buttonStart);

		JButton buttonSearchClear = new JButton(
				"\u6E05\u9664\u6B63\u5219\u5F0F");
		// 清除正则式
		buttonSearchClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textRegContentURL.setText("");
				textRegNextURL.setText("");
			}
		});
		buttonSearchClear.setBounds(309, 148, 109, 23);
		panel1.add(buttonSearchClear);

		// 内容页面测试
		JButton buttonContentTest = new JButton("\u6D4B\u8BD5");
		buttonContentTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					mySpider.regContentUrl = textRegContentURL.getText();
					mySpider.regNextPageUrl = textRegNextURL.getText();
					mySpider.regPicUrl = textRegPicURL.getText();
					mySpider.regDescription = textRegDesciption.getText();
					mySpider.regPrice = textRegPrice.getText();
					mySpider.testFlag = true;
					mySpider.tmpSearchResult = tmpFileString
							+ "contentTestResult.txt";
					mySpider.getWebByUrl(textSearchURL.getText());
					try {
						Runtime.getRuntime().exec(
								"notepad.exe " + tmpFileString
										+ "contentTestResult.txt");
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "打开测试结果失败!");
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "测试失败!");
				}
			}
		});
		buttonContentTest.setBounds(160, 299, 72, 23);
		panel1.add(buttonContentTest);

		// 清除正则式
		JButton buttonContentClear = new JButton(
				"\u6E05\u9664\u6B63\u5219\u5F0F");
		buttonContentClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textRegPicURL.setText("");
				textRegDesciption.setText("");
				textRegPrice.setText("");
			}
		});
		buttonContentClear.setBounds(309, 299, 109, 23);
		panel1.add(buttonContentClear);

		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		tabbedPane.addTab("高级设置", panel2);

		JLabel label_3 = new JLabel("\u4EE3\u7406");
		label_3.setBounds(36, 71, 54, 15);
		panel2.add(label_3);

		checkBoxAgent = new JCheckBox("\u542F\u7528");
		checkBoxAgent.setBounds(132, 67, 54, 23);
		panel2.add(checkBoxAgent);

		JLabel lblNewLabel = new JLabel("IP\uFF1A");
		lblNewLabel.setBounds(191, 71, 24, 15);
		panel2.add(lblNewLabel);

		textFieldIP = new JTextField();
		textFieldIP.setBounds(224, 68, 144, 21);
		panel2.add(textFieldIP);
		textFieldIP.setColumns(10);

		JLabel label_4 = new JLabel("\u7AEF\u53E3\uFF1A");
		label_4.setBounds(378, 71, 36, 15);
		panel2.add(label_4);

		textFieldPort = new JTextField();
		textFieldPort.setColumns(10);
		textFieldPort.setBounds(424, 67, 93, 21);
		panel2.add(textFieldPort);

		JLabel lblurl_6 = new JLabel("\u6700\u5927URL\u603B\u6570");
		lblurl_6.setBounds(36, 112, 71, 15);
		panel2.add(lblurl_6);

		checkBoxNum = new JCheckBox("\u542F\u7528");
		checkBoxNum.setBounds(132, 108, 54, 23);
		panel2.add(checkBoxNum);

		textFieldURLNum = new JTextField();
		textFieldURLNum.setColumns(10);
		textFieldURLNum.setBounds(263, 109, 105, 21);
		panel2.add(textFieldURLNum);

		JLabel lblUrl = new JLabel("URL\u6570\uFF1A");
		lblUrl.setBounds(191, 112, 54, 15);
		panel2.add(lblUrl);

		JLabel label_5 = new JLabel("\u603B\u65F6\u957F");
		label_5.setBounds(36, 156, 71, 15);
		panel2.add(label_5);

		checkBoxTime = new JCheckBox("\u542F\u7528");
		checkBoxTime.setBounds(132, 152, 54, 23);
		panel2.add(checkBoxTime);

		JLabel label_6 = new JLabel("\u65F6\u95F4\uFF1A");
		label_6.setBounds(191, 156, 54, 15);
		panel2.add(label_6);

		textFieldTime = new JTextField();
		textFieldTime.setColumns(10);
		textFieldTime.setBounds(263, 153, 105, 21);
		panel2.add(textFieldTime);

		JLabel label_7 = new JLabel("\u53D1\u5E03\u65B9\u5F0F");
		label_7.setBounds(36, 205, 71, 15);
		panel2.add(label_7);

		radioButtonLocal = new JRadioButton("\u4FDD\u5B58\u672C\u5730");
		radioButtonLocal.setSelected(true);
		radioButtonLocal.setBounds(132, 201, 85, 23);
		panel2.add(radioButtonLocal);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(radioButtonLocal);

		JRadioButton radioButtonDatabase = new JRadioButton(
				"\u5BFC\u5165\u6570\u636E\u5E93");
		radioButtonDatabase.setBounds(132, 240, 93, 23);
		panel2.add(radioButtonDatabase);
		buttonGroup.add(radioButtonDatabase);

		JLabel label_8 = new JLabel("\u7528\u6237\u540D\uFF1A");
		label_8.setBounds(161, 278, 54, 15);
		panel2.add(label_8);

		textFieldUsername = new JTextField();
		textFieldUsername.setColumns(10);
		textFieldUsername.setBounds(224, 275, 93, 21);
		panel2.add(textFieldUsername);

		JLabel label_9 = new JLabel("\u5BC6\u7801\uFF1A");
		label_9.setBounds(332, 278, 36, 15);
		panel2.add(label_9);

		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(391, 275, 93, 21);
		panel2.add(textFieldPassword);

		JLabel label_10 = new JLabel("\u6570\u636E\u5E93\u540D\uFF1A");
		label_10.setBounds(246, 244, 71, 15);
		panel2.add(label_10);

		textFieldDatabaseName = new JTextField();
		textFieldDatabaseName.setColumns(10);
		textFieldDatabaseName.setBounds(327, 241, 93, 21);
		panel2.add(textFieldDatabaseName);
	}
}
