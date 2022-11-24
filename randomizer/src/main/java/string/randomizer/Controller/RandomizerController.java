package string.randomizer.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import string.randomizer.Service.RandomizerService;

import static java.lang.Math.pow;

@RestController
@RequestMapping("/random")
public class RandomizerController {

    private final RandomizerService randomizerService;

    public RandomizerController(RandomizerService randomizerService) {
        this.randomizerService = randomizerService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<Integer> getJobs() {
        return randomizerService.getJobs();
    }

    @GetMapping(value = "/results/{path}")
    public ResponseEntity getResults(@PathVariable String path) {

        return randomizerService.getResults(path);
    }

    @PostMapping("/newJob")
    public ResponseEntity newJob(@RequestParam(value = "minlen") int minlen,
                                 @RequestParam(value = "maxlen") int maxlen,
                                 @RequestParam(value = "alphabet") String alphabet,
                                 @RequestParam(value = "lines") int lines) {
        if (lines > pow(alphabet.length(), maxlen)) {
            return new ResponseEntity<>("I am unable to create this many lines!", HttpStatus.BAD_REQUEST);
        }
        return randomizerService.newJob(minlen, maxlen, alphabet, lines);
    }
}
