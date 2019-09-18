package beans;

public class Duck {

    private Fly fly;
    private Quack quack;

    public void fly(){
        fly.doFly();
    }
    public void quack() {
        quack.doQuack();
    }

    public Fly getFly() {
        return fly;
    }

    public void setFly(Fly fly) {
        this.fly = fly;
    }

    public Quack getQuack() {
        return quack;
    }

    public void setQuack(Quack quack) {
        this.quack = quack;
    }
}
