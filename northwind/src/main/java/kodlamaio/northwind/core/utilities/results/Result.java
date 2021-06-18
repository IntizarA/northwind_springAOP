package kodlamaio.northwind.core.utilities.results;

public class Result {
private boolean success;
private String message;
public Result(boolean success) {//super type
	
	this.success=success;
}
public Result(boolean success,String message) {
	this(success);//call constructor 
	this.success=success;
	this.message=message;
}
public boolean isSuccess() {
	return this.success;
}
public String getMessage() {
	return this.message;
}

}
