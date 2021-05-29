package com.executorFramework;

public class ReturningValueListnerImpl implements ReturningValueListner<Integer> {
  private String taskId;

	public ReturningValueListnerImpl(String taskId){
		this.taskId=taskId;
	}
	public void getResult(Integer result) {
		// TODO Auto-generated method stub
		System.out.println(" Getting Value From "+taskId+" is "+result);
	}

}
