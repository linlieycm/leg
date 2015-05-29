package org.leg.siteweb.page;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.leg.library.type.Record;
import org.leg.siteweb.common.Storage;
import org.leg.siteweb.common.User;
import org.leg.siteweb.common.dao.UserinfoDao;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 日志记录动作
 */
public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = 5165050349215525795L;

	/**
	 * 日志对象
	 */
	protected static Logger logger = Logger.getLogger(LoginAction.class);

	private String redirect = null ;
    public String getRedirect() {
		return redirect;
	}
	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	private Integer userid;
    private String password;

	/**
	 * 执行请求动作
	 */
    public String execute() throws Exception {
        UserinfoDao userinfoDao = new UserinfoDao();

        if(null == userid ) {
            ServletActionContext.getContext().put("message", "请输入用户ID！");
            return "FAIL";
        }

        if(null == password || password.length() <= 0 ) {
            ServletActionContext.getContext().put("message", "请输入密码！");
            return "FAIL";
        }

		Record record = Storage.executor().load(userinfoDao.checkUserPass(userid, password));

        if(null == record || null == record.get("ID")) {
            ServletActionContext.getContext().put("message", "用户不存在或者密码错误！");
            return "FAIL";
        }

        ServletActionContext.getContext().put("userId", record.get("ID"));
        User.setUserId(Integer.parseInt(record.get("ID").toString()));

        String userType = record.get("UserType").toString();
        
        if(null != redirect && redirect.length() > 0) {
        	ServletActionContext.getContext().put("redirect", redirect);
        	return "redirect";
        }
        if("1".equals(userType)) {
            return "promoterLogin";
        } else if("2".equals(userType)) {
            return "steerLogin";
        }
		return "";
    }

    public String executeEx() throws Exception {
        UserinfoDao userinfoDao = new UserinfoDao();

        if(null == userid ) {
            ServletActionContext.getContext().put("message", "请输入用户ID！");
            return "FAIL";
        }

        if(null == password || password.length() <= 0 ) {
            ServletActionContext.getContext().put("message", "请输入密码！");
            return "FAIL";
        }

        Record record = Storage.executor().load(userinfoDao.checkUserPass(userid, password));

        if(null == record || null == record.get("ID")) {
            ServletActionContext.getContext().put("message", "用户不存在或者密码错误！");
            return "FAIL";
        }

        ServletActionContext.getContext().put("userId", record.get("ID"));
        User.setUserId(Integer.parseInt(record.get("ID").toString()));

        String userType = record.get("UserType").toString();

//        if(null != redirect) {
//            ServletActionContext.getContext().put("redirect", redirect);
//            return "redirect";
//        }

        if("1".equals(userType)) {
            return "promoterLogin";
        } else if("2".equals(userType)) {
            return "steerLogin";
        }
        return "";
    }
    /**
     * 执行请求动作
     */
    public String loginPre() {
        return "ftl";
    }

    public String loginPreEx() {
        return "ftl";
    }


	/**
	 * 输出控制台内容
	 * 
	 * @param content 控制台内容
	 */
	public void output(String content) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		}
		catch (IOException ex) {
			logger.error("call Response.getWriter() failed", ex);
			return;
		}
		writer.write(content);
		writer.close();
	}


    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
