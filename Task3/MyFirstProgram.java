package Lab;

class MyFirstClass {
    public static void main(String[] args) {
        MySecondClass o = new MySecondClass();
        System.out.println(o.bit_and());
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                o.setValue1(i);
                o.setValue2(j);
                System.out.print(o.bit_and());
                System.out.print(" ");
            }
            System.out.println();
        }

    }
}
class MySecondClass {
    private int num1, num2;

    public void setValue1(int i) {
        num1 = i;
    }
    public int getValue1(){
        return num1;
    }

    public void setValue2(int j) {
        num2 = j;
    }
    public int getValue2(){
        return num2;
    }

    public int bit_and() {
        return (num1&num2);
    }

}
