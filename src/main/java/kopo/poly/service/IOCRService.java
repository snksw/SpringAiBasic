package kopo.poly.service;

import kopo.poly.dto.OCRDTO;

public interface IOCRService {
    String modelFile = "C:\\Users\\data8320-16\\tessdata";
    OCRDTO getReadForImageText(OCRDTO pDTO) throws Exception;
}
