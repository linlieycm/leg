package org.leg.siteweb.page.steer;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.leg.library.type.Record;
import org.leg.library.type.core.IList;
import org.leg.siteweb.common.Storage;
import org.leg.siteweb.common.User;
import org.leg.siteweb.common.dao.OutStockDao;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志记录动作
 */
public class SteerMOutWarnAction extends ActionSupport {
	private static final long serialVersionUID = 5165050377715752795L;

	/**
	 * 日志对象
	 */
	protected static Logger logger = Logger.getLogger(SteerMOutWarnAction.class);

    private Integer stockId;

	/**
	 * 执行请求动作
	 */
    public String execute() throws Exception {

        int userId = User.getUserId();
        if(0 == userId) {
        	ActionContext.getContext().put("redirect", ServletActionContext.getRequest().getRequestURI()); return "NOLOGIN";
        }

        OutStockDao outStockDao = new OutStockDao();

        IList<Record> outStockList = Storage.executor().select(outStockDao.getOutStockBySuperId(userId));
        if(null != outStockList) {
            List<Record> resultList = new ArrayList<Record>();
            for (Record outStock : outStockList) {
                resultList.add(outStock);
            }
            ServletActionContext.getContext().put("outStockList", resultList);
        }

        ServletActionContext.getContext().put("userId", userId);

        return "SUCCESS";

    }

    //查询断货信息
    public String getOne() {

        int userId = User.getUserId();
        if(0 == userId) {
        	ActionContext.getContext().put("redirect", ServletActionContext.getRequest().getRequestURI());
            ActionContext.getContext().put("redirect", ServletActionContext.getRequest().getRequestURI()); return "NOLOGIN";
        }

        OutStockDao outStockDao = new OutStockDao();
        Record outStock = Storage.executor().load(outStockDao.getSubOutStockById(stockId));
        ServletActionContext.getContext().put("outStock", outStock);
        ServletActionContext.getContext().put("userId", userId);
        return "SUCCESS";
    }

    //更新状态
    public String doUpdate() {
        OutStockDao outStockDao =  new OutStockDao();
        Storage.executor().alter(outStockDao.updateOutStatusById(stockId, 2));
        String result = "";

        try {
            result = this.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
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

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }
}
