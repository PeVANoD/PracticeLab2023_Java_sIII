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