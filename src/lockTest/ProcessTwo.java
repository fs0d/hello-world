package lockTest;

public class ProcessTwo implements Runnable {


    private MethodLock methodLock;

    public ProcessTwo(MethodLock methodLock){
        this.methodLock = methodLock;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() + ":" + Thread.currentThread().getName() + " running");
        methodLock.draw(700d);
        System.out.println(System.currentTimeMillis() + ":" + Thread.currentThread().getName() + " dead");
    }
}
