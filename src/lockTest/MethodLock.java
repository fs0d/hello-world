package lockTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MethodLock {

    private DataLock data;
    private Lock lock = new ReentrantLock();

    public void deposit(double money){
        try{
            lock.lock();
            System.out.println("获取锁");
            System.out.println("账户余额：" + checkBalance());
            double balance = data.getBalance();
            balance += money;
            data.setBalance(balance);
            System.out.println("存款：" + money);
            System.out.println("账户余额：" + checkBalance());
        }  finally {
            System.out.println("释放锁");
            lock.unlock();
        }
    }

    public void draw(double money) {
       /* if(lock.tryLock()){*/
            try{
                lock.lock();
                System.out.println("获取锁");
                System.out.println("账户余额：" + checkBalance());
                double balance = data.getBalance();
                if(balance > money){
                    balance -= money;
                    data.setBalance(balance);
                    System.out.println("取款：" + money);
                }else {
                    System.out.println("余额不足");
                }
                System.out.println("账户余额：" + checkBalance());
            }finally {
                System.out.println("释放锁");
                lock.unlock();
            }
        /*}else {
            System.out.println("未获取到锁");
        }*/
    }

    public double checkBalance() {
        /*lock.lock();*/
        return data.getBalance();
    }

    public DataLock getData() {
        return data;
    }

    public void setData(DataLock data) {
        this.data = data;
    }
}
