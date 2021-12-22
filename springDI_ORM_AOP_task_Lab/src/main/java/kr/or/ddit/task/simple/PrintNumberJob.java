package kr.or.ddit.task.simple;

import java.util.TimerTask;

// 스레드 하나의 작업 정의
public class PrintNumberJob extends TimerTask {
	private int number;

	@Override
	public void run() {
			System.out.println(++number);
	}

}
