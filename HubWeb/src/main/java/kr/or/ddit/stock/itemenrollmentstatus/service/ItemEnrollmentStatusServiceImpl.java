package kr.or.ddit.stock.itemenrollmentstatus.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.stock.itemenrollmentstatus.dao.ItemEnrollmentStatusDAO;

@Service
public class ItemEnrollmentStatusServiceImpl implements ItemEnrollmentStatusService {
	@Inject
	private ItemEnrollmentStatusDAO dao;
	


}
