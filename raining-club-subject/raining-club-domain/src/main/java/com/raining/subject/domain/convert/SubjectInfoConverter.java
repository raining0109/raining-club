package com.raining.subject.domain.convert;

import com.raining.subject.domain.entity.SubjectContributeRankBO;
import com.raining.subject.domain.entity.SubjectInfoBO;
import com.raining.subject.domain.entity.SubjectOptionBO;
import com.raining.subject.infra.basic.entity.SubjectInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectInfoConverter {

    SubjectInfoConverter INSTANCE = Mappers.getMapper(SubjectInfoConverter.class);

    SubjectInfo convertBoToInfo(SubjectInfoBO subjectInfoBO);

    SubjectInfoBO convertOptionToBo(SubjectOptionBO subjectOptionBO);

    SubjectInfoBO convertOptionAndInfoToBo(SubjectOptionBO subjectOptionBO,SubjectInfo subjectInfo);

    List<SubjectInfoBO> convertListInfoToBO(List<SubjectInfo> subjectInfoList);

    List<SubjectContributeRankBO> convertListInfoToContributeRankBO(List<SubjectInfo> infoList);
}
