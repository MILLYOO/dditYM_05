package kr.or.ddit.task.simple;

import java.util.Timer;

public class SimpleThreadTest {
	public static void main(String[] args) {
		PrintNumberJob job = new PrintNumberJob();
		
		Timer timer = new Timer();
		timer.schedule(job, 0, 1000);
	}
}
