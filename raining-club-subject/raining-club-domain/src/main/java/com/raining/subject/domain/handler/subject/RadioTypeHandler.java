package com.raining.subject.domain.handler.subject;

import com.raining.subject.common.enums.IsDeletedFlagEnum;
import com.raining.subject.common.enums.SubjectInfoTypeEnum;
import com.raining.subject.domain.convert.RadioSubjectConverter;
import com.raining.subject.domain.entity.SubjectAnswerBO;
import com.raining.subject.domain.entity.SubjectInfoBO;
import com.raining.subject.domain.entity.SubjectOptionBO;
import com.raining.subject.infra.basic.entity.SubjectRadio;
import com.raining.subject.infra.basic.service.SubjectRadioService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 单选题目的策略类
 *
 * @author: raining
 * @date: 2023/10/5
 */
@Component
public class RadioTypeHandler implements SubjectTypeHandler {

    @Resource
    private SubjectRadioService subjectRadioService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.RADIO;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //单选题目的插入
        List<SubjectRadio> subjectRadioList = new LinkedList<>();
        subjectInfoBO.getOptionList().forEach(option -> {
            SubjectRadio subjectRadio = RadioSubjectConverter.INSTANCE.convertBoToEntity(option);
            subjectRadio.setSubjectId(subjectInfoBO.getId());
            subjectRadio.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
            subjectRadioList.add(subjectRadio);
        });
        subjectRadioService.batchInsert(subjectRadioList);
    }

    @Override
    public SubjectOptionBO query(int subjectId) {
        SubjectRadio subjectRadio = new SubjectRadio();
        subjectRadio.setSubjectId(Long.valueOf(subjectId));
        List<SubjectRadio> result = subjectRadioService.queryByCondition(subjectRadio);
        List<SubjectAnswerBO> subjectAnswerBOList = RadioSubjectConverter.INSTANCE.convertEntityToBoList(result);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);

        //选择题的答案要组装
        StringBuilder answer = new StringBuilder();
        StringBuilder correct_option = new StringBuilder();
        for (SubjectAnswerBO subjectAnswerBO : subjectAnswerBOList) {
            String opt = String.valueOf((char)(subjectAnswerBO.getOptionType() + 'A' - 1));
            if (subjectAnswerBO.getIsCorrect().equals(1)) {
                correct_option.append(opt).append(" ");
            }
            answer.append(opt).append(": ").append(subjectAnswerBO.getOptionContent());
        }
        answer.append("正确选项：").append(correct_option);
        subjectOptionBO.setSubjectAnswer(answer.toString());
        //刷题应该提供解析


        return subjectOptionBO;
    }

}
