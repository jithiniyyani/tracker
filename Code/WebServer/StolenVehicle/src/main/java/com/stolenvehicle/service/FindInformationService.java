package com.stolenvehicle.service;

import java.util.List;

import com.stolenvehicle.constants.FindStatusEnum;
import com.stolenvehicle.dto.FindInformationTo;
import com.stolenvehicle.exception.BusinessException;

public interface FindInformationService {

	public FindInformationTo saveFindInformation(FindInformationTo findInformationTo) throws BusinessException;

	public List<FindInformationTo> getFindInformationListForUser(String user_id) throws BusinessException;

	public List<FindInformationTo> findInformationListReadyForReward(String user_id) throws BusinessException;

	public boolean updateFindInformationStatus(String find_id, FindStatusEnum findStatus) throws BusinessException;

	public FindInformationTo findFindInformationById(String findId) throws BusinessException;

}
