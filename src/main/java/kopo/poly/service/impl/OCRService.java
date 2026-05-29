package kopo.poly.service.impl;

import kopo.poly.dto.OCRDTO;
import kopo.poly.service.IOCRService;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OCRService implements IOCRService {
    @Override
    public OCRDTO getReadForImageText(OCRDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".getReadForImageText Start!");
        ClassPathResource resource = new ClassPathResource(pDTO.getFilePath() + "/" + pDTO.getFileName());
        ITesseract instance = new Tesseract();
        instance.setDatapath(IOCRService.modelFile);
        instance.setLanguage("kor");
        String result = instance.doOCR(resource.getFile());
        pDTO.setResult(result);
        log.info(this.getClass().getName() + ".getReadForImageText End!");
        return pDTO;
    }
}
