package ex1;

import java.sql.SQLException;

import com.newlecture.app.console.NoticeConsole;

public class Program {
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		NoticeConsole console = new NoticeConsole();
		
		console.printNoticeList();

		EXIT: 
		while(true) {
			int menu = console.inputNoticeMenu();

		switch (menu) {
		case 1: // �� ��ȸ
			break;
		case 2: // ����
			console.prevPage();
			break;
		case 3: // ����
			console.nextPage();
			break;
		case 4: // �۾���
			console.writePost();
			break;
		case 5:
			//�˻�
			console.InputSearchWord();
			break;
		case 6:
			console.printNoticeList();
			break;
		case 7:
			break EXIT;
		default:
			System.out.println("<<�����>> �޴��� 1~4������ �Է��� �� �ֽ��ϴ�.");
			break;
		}
		}
	}
}
