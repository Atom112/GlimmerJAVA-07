import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
public class Main {
    public static void main(String[] args) {

        String fileName = "data.txt";             //需要读取的文件
        double sum = 0;     // 初始化“总和”和计数
        int count = 0;
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                throw new FileNotFoundException2("找不到文件：" + fileName);

            }
        } catch (FileNotFoundException2 e){
            System.err.println(e.getMessage());
            return;
        }


        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;                // 逐行读取文件
            while ((line = reader.readLine()) != null) {
                try {
                    int number = Integer.parseInt(line.trim());    // 将读取的行转换为整数
                    sum += number;          // 累加到总和
                    count++;
                } catch (NumberFormatException e) {
                    System.err.println("读取到的内容格式错误: " + line); // 如果转换失败，捕获并打印错误信息
                }
            }

            if (count == 0) {
                throw new EmptyFileException("文件数据内容为空");// 如果计数器为 0，没有可读取的数字，抛出 EmptyFileException
            }


            double average = sum / count;// 计算平均值
            System.out.println("整数的平均值是: " + average);
        } catch (EmptyFileException e) {
            System.out.println(e.getMessage());// 如果文件为空，捕获并打印的错误信息
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            System.out.println("程序结束");
        }
    }
}