package org.leg.siteweb.page.steer;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.leg.library.type.Record;
import org.leg.library.type.core.IList;
import org.leg.siteweb.common.Storage;
import org.leg.siteweb.common.User;
import org.leg.siteweb.common.dao.UserinfoDao;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志记录动作
 */
public class SteerMInfoAdjustAction extends ActionSupport {
	private static final long serialVersionUID = 5165050377715752795L;

	/**
	 * 日志对象
	 */
	protected static Logger logger = Logger.getLogger(SteerMInfoAdjustAction.class);

	/**
	 * 执行请求动作
	 */
    public String execute() throws Exception {

        int userId = User.getUserId();
        if(0 == userId) {
            ActionContext.getContext().put("redirect", ServletActionContext.getRequest().getRequestURI()); return "NOLOGIN";
        }

        UserinfoDao userinfoDao = new UserinfoDao();

        //查询所辖员工
        IList<Record> subUserList = Storage.executor().select(userinfoDao.getSubUserInfo(userId));
        if(null != subUserList && subUserList.size() > 0) {
            List<Record> resultList = new ArrayList<Record>();
            for (Record subUser : subUserList) {
                resultList.add(subUser);
            }
            ServletActionContext.getContext().put("subUserList", resultList);
        }

        ServletActionContext.getContext().put("userId", userId);

        return "SUCCESS";

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

}
