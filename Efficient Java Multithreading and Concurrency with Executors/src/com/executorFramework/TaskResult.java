package com.executorFramework;

public class TaskResult <Task,Result> {
  public final Task taskId;
  public final Result result;
public TaskResult(Task taskId, Result result) {
	super();
	this.taskId = taskId;
	this.result = result;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
	result = prime * result + ((taskId == null) ? 0 : taskId.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	TaskResult other = (TaskResult) obj;
	if (result == null) {
		if (other.result != null)
			return false;
	} else if (!result.equals(other.result))
		return false;
	if (taskId == null) {
		if (other.taskId != null)
			return false;
	} else if (!taskId.equals(other.taskId))
		return false;
	return true;
}
@Override
public String toString() {
	return "TaskResult [taskId=" + taskId + ", result=" + result + "]";
}
  
}
