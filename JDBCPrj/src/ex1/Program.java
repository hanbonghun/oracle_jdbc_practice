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
		case 1: // 상세 조회
			break;
		case 2: // 이전
			console.prevPage();
			break;
		case 3: // 다음
			console.nextPage();
			break;
		case 4: // 글쓰기
			console.writePost();
			break;
		case 5:
			//검색
			console.InputSearchWord();
			break;
		case 6:
			console.printNoticeList();
			break;
		case 7:
			break EXIT;
		default:
			System.out.println("<<사용방법>> 메뉴는 1~4까지만 입력할 수 있습니다.");
			break;
		}
		}
	}
}
