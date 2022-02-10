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
		System.out.println("��������������������������������������������������������������������������������������");
		System.out.printf("<��������> �� %d �Խñ�\n",count);
		System.out.println("��������������������������������������������������������������������������������������");
	
		
		for (Notice notice:list) {
		System.out.printf("%d. %s  / %s  / %s \n",notice.getId(),notice.getTitle(),notice.getContent(),notice.getRegDate());
		System.out.println("��������������������������������������������������������������������������������������");		
		}
		System.out.printf("	   %d/%d pages\n\n",page,lastPage);

	}

	public int inputNoticeMenu() {
		System.out.println("1.����ȸ / 2.���� / 3.���� / 4.�۾��� / 5.�˻� / 6. Ȩ / 7.����  >");
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
			System.out.println("���� �������� �����ϴ�.");
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
			System.out.println("���� �������� �����ϴ�.");
			System.out.println("==================");

			return ;
		}
		page--;
		printNoticeList();
	}
	public void InputSearchWord() throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);

		System.out.println("�˻� ����(title/content/writerId �߿� �ϳ��� �Է��ϼ���.");
		System.out.print(" > ");
		searchField =sc.nextLine();
		
		System.out.println("�˻��� > ");
		searchWord = sc.nextLine();
		printNoticeList();
		
	}
	public void writePost() throws ClassNotFoundException, SQLException {
		Notice notice = new Notice();
		Scanner sc = new Scanner (System.in);
		String title="";
		String content ="";
		
		System.out.println("���� > ");
		title = sc.nextLine();
		notice.setTitle(title); 
		
		System.out.println("���� > ");
		content = sc.nextLine();
		notice.setContent(content); 
		
		service.insert(notice);
	}

}
