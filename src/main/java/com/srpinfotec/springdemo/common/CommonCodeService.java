package com.srpinfotec.springdemo.common;


import com.srpinfotec.springdemo.UserException;
import com.srpinfotec.springdemo.domain.basicinfo.CommonCode;
import com.srpinfotec.springdemo.dto.CmCodeDto;
import com.srpinfotec.springdemo.repository.CommonCodeRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Transactional(readOnly = true)
@Component
@RequiredArgsConstructor
public class CommonCodeService {
    private final CommonCodeRepository codeRepository;

    @PostConstruct
    private void loadCodes(){
        List<CommonCode> findCodes = codeRepository.findAllWithParent();
    }

    public Map<String, CmCodeDto> searchCommonCode(String cmCode){
        List<CommonCode> childs = codeRepository.findChildByParentCmCode(cmCode);

        return childs.stream().collect(Collectors.toMap(CommonCode::getCmCode, CommonCode::toDto));
    }

    @Transactional
    public void updateCode(CmCodeDto codeDto){
        CommonCode code = codeRepository.findByCmCode(codeDto.getCmCode())
                .orElseThrow(() -> new UserException("공통 코드가 없음"));

        Optional<CommonCode> parent = codeRepository.findByCmCode(codeDto.getUpCode());
        parent.ifPresent(code::setParent);

        code.setNameEng(codeDto.getNameEng());
        code.setNameKor(codeDto.getNameKor());
        code.setSection1(codeDto.getSection1());
        code.setSection2(codeDto.getSection2());
        code.setSection3(codeDto.getSection3());
    }

    @Transactional
    public void insertCode(CmCodeDto codeDto){
        CommonCode code = new CommonCode(codeDto);

        codeRepository.findByCmCode(codeDto.getUpCode())
                .ifPresent(code::setParent);

        codeRepository.save(code);
    }

    @Transactional
    public void deleteCode(String cmCode){
        CommonCode code = codeRepository.findByCmCode(cmCode)
                .orElseThrow(() -> new UserException("공통 코드 없음"));

        codeRepository.delete(code);
    }

    @Transactional
    public void deleteCode(CmCodeDto codeDto){
        deleteCode(codeDto.getCmCode());
    }
}
