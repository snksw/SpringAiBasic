package kopo.poly.service;

import kopo.poly.dto.NLPDTO;

public interface INLPService {
    NLPDTO getPlainText(String text);
    NLPDTO getNouns(String text);
}
