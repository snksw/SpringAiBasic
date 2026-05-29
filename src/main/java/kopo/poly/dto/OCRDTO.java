package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OCRDTO {
    private String filePath;
    private String fileName;
    private String result;
    public OCRDTO(String path, String name) {
        this.filePath = path;
        this.fileName = name;
    }
}
