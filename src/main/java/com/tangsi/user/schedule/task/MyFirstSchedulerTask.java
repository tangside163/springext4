package com.tangsi.user.schedule.task;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyFirstSchedulerTask {
	
	public void run() {
		System.out.println("执行调度"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
}

