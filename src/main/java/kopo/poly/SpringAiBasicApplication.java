package kopo.poly;

import kopo.poly.dto.NLPDTO;
import kopo.poly.dto.OCRDTO;
import kopo.poly.service.INLPService;
import kopo.poly.service.IOCRService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class SpringAiBasicApplication implements CommandLineRunner {
    private final IOCRService ocrService;
    private final INLPService nlpService;
    @Override
    public void run(String... args) throws Exception {
        log.info("Start =/");
        OCRDTO pDTO = new OCRDTO("image", "sample01.png");
        OCRDTO rDTO = ocrService.getReadForImageText(pDTO);
        String result = rDTO.getResult();
        log.info("인식된 문자열");
        log.info(result);
        log.info("---------------- ");
        NLPDTO nlpDTO = nlpService.getPlainText(result);
        log.info("형태소별 품사 분석 결과: " + nlpDTO.getResult());
        nlpDTO = nlpService.getNouns(result);
        List<String> nouns = nlpDTO.getNouns();
        Set<String> distinct = new HashSet<String>(nouns);
        log.info("중복 제거 수행 전 단어 수: " + nouns.size());
        log.info("중복 제거 수행 후 단어 수: " + distinct.size());
        Map<String, Integer> rMap = new HashMap<String, Integer>();
        distinct.forEach(noun -> {
            int count = Collections.frequency(nouns, noun);
            rMap.put(noun, count);
            log.info(noun + ": " + count);
        });
        List<Map.Entry<String, Integer>> sortResult = new LinkedList<>(rMap.entrySet());
        sortResult.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        log.info("가장 많이 사용된 단어는?");
        log.info(sortResult.toString());
        log.info("End ===/");
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringAiBasicApplication.class, args);
    }

}
