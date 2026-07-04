import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileChunker {

    public final static int Chunks = 4;

    public static List<Chunk> createChunks(File file) {
        List<Chunk> chunks = new ArrayList<>();
        long fileSize = file.length();
        long chunkSize = fileSize / Chunks;
        for (int i = 0; i < Chunks; i++) {
            final long start = i * chunkSize;
            final long end = (i == Chunks - 1) ? fileSize : (start + chunkSize);
            chunks.add(new Chunk(start, end));
        }
        return chunks;
    }
}
