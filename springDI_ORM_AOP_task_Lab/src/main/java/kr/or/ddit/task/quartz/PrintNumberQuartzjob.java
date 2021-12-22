package kr.or.ddit.task.quartz;


public class PrintNumberQuartzjob { //상속받을 필요 없다.
   private int number;
   
   // fixedDelay = 정해진 시간마다 시작
   // fixedRate = 끝난 시점에서 정해진 시간 쉬고 시작.
   // cron = 초 분 시 일 월 요일
   public void execute() {
      System.out.printf("%d - %s \n", ++number, Thread.currentThread().getName());
   }
   
}