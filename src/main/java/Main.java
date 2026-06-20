public class Main {
    public static void main(String[] args) {
        Generator generator = new Generator();
        Result res = generator.generateValidTask();

        printTask(res);
    }

    private static void printTask(Result res) {
        System.out.printf("Задание\n%s\nОтвет на задание\n%s\n",
                res.getCode(), res.getAnswer());
    }
}

