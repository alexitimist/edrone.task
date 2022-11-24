package string.randomizer.Service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import string.randomizer.Repository.RandomizerRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;


@Service
public class RandomizerService {

    private final RandomizerRepository randomizerRepository;
    private final ThreadPoolTaskExecutor taskExecutor;

    public RandomizerService(RandomizerRepository randomizerRepository, ThreadPoolTaskExecutor taskExecutor) {
        this.randomizerRepository = randomizerRepository;
        this.taskExecutor = taskExecutor;
    }

    public ResponseEntity<Integer> getJobs() {
        Integer count = taskExecutor.getActiveCount();
        return ResponseEntity.ok(count);
    }

    public ResponseEntity getResults(String path) {
        try {
            Path path1 = Paths.get("src/outputs/" + path + ".txt");
            Resource resource = new UrlResource(path1.toUri());
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("text/plain"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Async("threadPoolExecutor")
    public ResponseEntity newJob(int minlen, int maxlen, String alphabet, int lines) {

        try {
            FileWriter fw = new FileWriter("src/outputs/output" + minlen + '-' + maxlen + '-' + lines + ".txt");
            Random random = new Random();
            List<String> combinations = new ArrayList<>();
            createString(maxlen, alphabet, "", combinations, minlen);

            for (int i = 0; i < lines; i++) {

                int line = random.nextInt(combinations.size() - i);

                fw.write(combinations.get(line) + '\n');
                combinations.remove(line);
            }
            fw.close();
            return ResponseEntity.status(200).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Async("threadPoolExecutor")
    String createString(int len, String letters, String text, List<String> combinations, int minlen) {          //This function fills an array
        String help = text;                                                                               //with every possible
        if (len != 0) {                                                                                    //combinaion of strings with max length
            for (int i = 0; i < letters.length(); i++) {
                text += letters.charAt(i);
                if (text.length() >= minlen) {
                    combinations.add(text);
                }
                createString(len - 1, letters, text, combinations, minlen);
                text = help;
            }

        }
        return text;
    }
}
