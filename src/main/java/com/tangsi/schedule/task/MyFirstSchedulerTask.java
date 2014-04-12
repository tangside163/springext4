package com.tangsi.schedule.task;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyFirstSchedulerTask {
	
	public void run() {
		System.out.println("执行定时任务,时间为"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
}

