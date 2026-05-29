package kopo.poly.service.impl;

import kopo.poly.dto.NLPDTO;
import kopo.poly.service.INLPService;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NLPService implements INLPService {
    Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
    @Override
    public NLPDTO getPlainText(String text) {
        log.info(this.getClass().getName() + ".getPlainText Start!");
        KomoranResult komoranResult = komoran.analyze(text);
        String result = komoranResult.getPlainText();
        NLPDTO rDTO = new NLPDTO();
        rDTO.setResult(result);
        log.info(this.getClass().getName() + ".getPlainText End!");
        return rDTO;
    }

    @Override
    public NLPDTO getNouns(String text) {
        log.info(this.getClass().getName() + ".getNouns Start!");
        KomoranResult komoranResult = komoran.analyze(text);
        List<String> result = komoranResult.getNouns();
        NLPDTO rDTO = new NLPDTO();
        rDTO.setNouns(result);
        log.info(this.getClass().getName() + ".getNouns End!");
        return rDTO;
    }
}
