package com.manager.service;

import java.util.List;

import com.manager.bean.ManagerBean;
import com.manager.exception.ManagerNotFound;

public interface ManagerService {

	public ManagerBean insertManagerDetails(ManagerBean bean) ;
	public void deleteManagerDetails(long managerId,String status) throws ManagerNotFound ;
	public ManagerBean getManagerDetailsById(long managerId) throws ManagerNotFound ;
	public List<ManagerBean> getAllManagerDetails() throws ManagerNotFound ;
	public void updateProfileImage(byte[] profile, long managerId) throws ManagerNotFound ;
	public void updateManagerPassword(String managerEmail, String password) throws ManagerNotFound ;


}