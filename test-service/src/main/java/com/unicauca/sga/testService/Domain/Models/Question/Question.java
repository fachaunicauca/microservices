package com.unicauca.sga.testService.Domain.Models.Question;

import com.unicauca.sga.testService.Domain.Models.Test;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Data
public class Question{
    private Long questionId;
    private String questionText;
    private String questionTitle;
    private String questionImageId;
    private byte[] questionImage;
    private String questionImageUrl;
    private String questionType;
    private String questionStructure;

    private Test test;
}
