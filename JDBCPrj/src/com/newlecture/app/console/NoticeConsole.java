package com.newlecture.app.console;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.newlecture.app.entitiy.Notice;
import com.newlecture.app.service.NoticeService;

public class NoticeConsole {
	private NoticeService service;
	private int page; 
	private String searchWord;
	private String searchField;
	
	public NoticeConsole() {
		service =new NoticeService();
		page =1;
		searchWord = "";
		searchField ="TITLE";
	}
	public void printNoticeList() throws ClassNotFoundException, SQLException {
		int count; 
		List<Notice> list = new ArrayList<>();
		list = service.getList(page,searchField, searchWord);
		count = service.getCount(searchField,searchWord);
		int lastPage = (count%10==0) ?count/10 : count/10+1;
		System.out.println("───────────────────────────────────────────");
		System.out.printf("<공지사항> 총 %d 게시글\n",count);
		System.out.println("───────────────────────────────────────────");
	
		
		for (Notice notice:list) {
		System.out.printf("%d. %s  / %s  / %s \n",notice.getId(),notice.getTitle(),notice.getContent(),notice.getRegDate());
		System.out.println("───────────────────────────────────────────");		
		}
		System.out.printf("	   %d/%d pages\n\n",page,lastPage);

	}

	public int inputNoticeMenu() {
		System.out.println("1.상세조회 / 2.이전 / 3.다음 / 4.글쓰기 / 5.검색 / 6. 홈 / 7.종료  >");
		Scanner sc = new Scanner(System.in);
		String menu_ = sc.nextLine();
		int menu = Integer.parseInt(menu_);
		return menu;
	}
	
	public void nextPage() throws ClassNotFoundException, SQLException {
		int count = service.getCount(searchField,searchWord);
		int lastPage = (count%10==0) ?count/10 : count/10+1;
		if(page == lastPage) {
			System.out.println("==================");
			System.out.println("이전 페이지가 없습니다.");
			System.out.println("==================");

			return ;
		}
		page++;
		printNoticeList();
	}

	public void prevPage() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		if(page==1) {
			System.out.println("==================");
			System.out.println("다음 페이지가 없습니다.");
			System.out.println("==================");

			return ;
		}
		page--;
		printNoticeList();
	}
	public void InputSearchWord() throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);

		System.out.println("검색 범주(title/content/writerId 중에 하나를 입력하세요.");
		System.out.print(" > ");
		searchField =sc.nextLine();
		
		System.out.println("검색어 > ");
		searchWord = sc.nextLine();
		printNoticeList();
		
	}
	public void writePost() throws ClassNotFoundException, SQLException {
		Notice notice = new Notice();
		Scanner sc = new Scanner (System.in);
		String title="";
		String content ="";
		
		System.out.println("제목 > ");
		title = sc.nextLine();
		notice.setTitle(title); 
		
		System.out.println("내용 > ");
		content = sc.nextLine();
		notice.setContent(content); 
		
		service.insert(notice);
	}

}
