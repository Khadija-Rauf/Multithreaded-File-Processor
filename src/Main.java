import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(FileChunker.Chunks);
        File file = new File("src/resources/file.txt");
        List<Future<Long>> futures = new ArrayList<>();
        List<Chunk> chunks = FileChunker.createChunks(file);
        for (Chunk chunk : chunks) {
            futures.add(executor.submit(
                    new FileReader(file, chunk.getStart(), chunk.getEnd()))
            );
        }
        int totalLines = 0;
        try {
            for (Future<Long> future : futures) {
                totalLines += future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("Total Lines = " + totalLines);
        executor.shutdown();
    }
}