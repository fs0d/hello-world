package lockTest;

public class ProcessOne implements Runnable {

    private MethodLock methodLock;

    public ProcessOne(MethodLock methodLock){
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
        methodLock.deposit(10000d);
        System.out.println(System.currentTimeMillis() + ":" + Thread.currentThread().getName() + " dead");
    }
}
