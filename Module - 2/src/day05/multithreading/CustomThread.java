package day05.multithreading;

public class CustomThread extends Thread{
	private int delay;
	public CustomThread(String name, int delay){
		super(name);
		this.delay = delay;
	}

	@Override
	public void run(){
		for(int i = 1; i <= 10; i++){
			try{
				Thread.sleep(delay);
				System.out.println(i + " " + this.getName());
			}catch(InterruptedException e){
				System.out.println(e.getMessage());
			}
		}
	}
}