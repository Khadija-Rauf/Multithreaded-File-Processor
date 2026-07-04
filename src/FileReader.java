import java.io.*;
import java.util.concurrent.Callable;

public class FileReader implements Callable<Long> {

    private final File file;
    private final long start;
    private final long end;

    public FileReader(File file, long start, long end) {
        this.file = file;
        this.start = start;
        this.end = end;
    }

    @Override
    public Long call() {
        long lineCount = 0L;
        String fileName = file.getAbsolutePath();
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "r")) {
            raf.seek(start);
            if (start != 0) {
                raf.readLine();
            }

            String line;
            while (raf.getFilePointer() < end &&
                    (line = raf.readLine()) != null) {

                lineCount++;
            }
            System.out.println(Thread.currentThread().getName()
                    + " counted: " + lineCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineCount;
    }
}