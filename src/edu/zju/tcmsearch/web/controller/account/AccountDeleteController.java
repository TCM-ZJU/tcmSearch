package edu.zju.tcmsearch.web.controller.account;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import edu.zju.tcmsearch.secure.domain.account.Account;
import edu.zju.tcmsearch.secure.domain.account.UserInfo;
import edu.zju.tcmsearch.secure.service.account.IAccountManager;
import edu.zju.tcmsearch.secure.service.account.UserInfoManager;

public class AccountDeleteController implements Controller{
	
	private IAccountManager accountManager;
	private UserInfoManager userInfoManager;
	
	 private static final Logger logger = Logger
     .getLogger(RegisterAffirmController.class);
	
	public IAccountManager getAccountManager() {
		return accountManager;
	}


	public void setAccountManager(IAccountManager accountManager) {
		this.accountManager = accountManager;
	}


	public UserInfoManager getUserInfoManager() {
		return userInfoManager;
	}


	public void setUserInfoManager(UserInfoManager userInfoManager) {
		this.userInfoManager = userInfoManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String idString = request.getParameter("userid");
		logger.debug("idString"+idString);
		Integer idInteger = new Integer(idString);
		int idInt = idInteger.intValue();
		//Account account = accountManager.findById(idInt);
		
	//	UserInfo userInfo = userInfoManager.userInfoLoadById(idInt);
		//account.setStatus("正常");
		Account account = new Account();
		account.setId(idInt);
		UserInfo userInfo = new UserInfo();
		userInfo.setId(idInt);
		userInfoManager.delete(userInfo);		
		accountManager.delete(account);
		logger.debug("delete status success!");
		logger.debug("idInt"+idInt);
		View backView=new RedirectView(request.getContextPath()+"/secure/accountResults.luc");
		return new ModelAndView(backView);
	}

	
}