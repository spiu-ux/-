public class Main {
    public static void main(String[] args) {
        Generator generator = new Generator();
        Result res = generator.generateValidTask();

        printTask(res);
    }

    private static void printTask(Result res) {
        System.out.println("Задание");
        System.out.print(res.getCode());
        System.out.println("\nОтвет на задание ");
        System.out.println(res.getAnswer());
    }
}

