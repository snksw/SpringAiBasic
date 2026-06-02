package kopo.poly.service.impl;

import kopo.poly.dto.StudentDTO;
import kopo.poly.mapper.IStudentMapper;
import kopo.poly.service.IStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentService implements IStudentService {
    private final IStudentMapper studentMapper;

    @Override
    public List<StudentDTO> insertStudent(StudentDTO pDTO) throws Exception {
        log.info("{}.insertStudent Start", this.getClass().getName());
        Optional<StudentDTO> result = Optional.ofNullable(studentMapper.getStudent(pDTO));
        if (result.isEmpty()) {
            studentMapper.insertStudent(pDTO);
            log.info("학생 등록 완료 - ID: {}", pDTO.getUserId());
        } else log.warn("학생 등록 실패 - ID 중복: {}", pDTO.getUserId());
        return Optional.ofNullable(studentMapper.getStudentList()).orElseGet(ArrayList::new);
    }

    @Override
    public List<StudentDTO> updateStudent(StudentDTO pDTO) throws Exception {
        log.info("{}.updateStudent Start", this.getClass().getName());
        Optional<StudentDTO> result = Optional.ofNullable(studentMapper.getStudent(pDTO));
        if (result.isPresent()) {
            studentMapper.updateStudent(pDTO);
            log.info("학생 수정 완료 - ID: {}", pDTO.getUserId());
        } else log.warn("학생 수정 실패 - ID: {}", pDTO.getUserId());
        return Optional.ofNullable(studentMapper.getStudentList()).orElseGet(ArrayList::new);
    }

    @Override
    public List<StudentDTO> deleteStudent(StudentDTO pDTO) throws Exception {
        log.info("{}.deleteStudent Start", this.getClass().getName());
        Optional<StudentDTO> result = Optional.ofNullable(studentMapper.getStudent(pDTO));
        if (result.isPresent()) {
            studentMapper.deleteStudent(pDTO);
            log.info("학생 삭제 성공 - ID: {}", pDTO.getUserId());
        } else log.warn("학생 삭제 실패 - 존재하지 않는 학생");
        return Optional.ofNullable(studentMapper.getStudentList()).orElseGet(ArrayList::new);
    }
}
