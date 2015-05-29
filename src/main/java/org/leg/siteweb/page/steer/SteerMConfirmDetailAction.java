package org.leg.siteweb.page.steer;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.leg.library.json.JSONObject;
import org.leg.library.type.Record;
import org.leg.library.type.core.IList;
import org.leg.siteweb.common.Storage;
import org.leg.siteweb.common.User;
import org.leg.siteweb.common.dao.GiftinfoDao;
import org.leg.siteweb.common.dao.SaleinfoDao;
import org.leg.siteweb.common.dao.WorkinfoDao;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志记录动作
 */
public class SteerMConfirmDetailAction extends ActionSupport {
	private static final long serialVersionUID = 5165050377715752795L;

	/**
	 * 日志对象
	 */
	protected static Logger logger = Logger.getLogger(SteerMConfirmDetailAction.class);

    private Integer workInfoId;

	/**
	 * 执行请求动作
	 */
    public String execute() throws Exception {

        int userId = User.getUserId();
        if(0 == userId) {
            ActionContext.getContext().put("redirect", ServletActionContext.getRequest().getRequestURI()); return "NOLOGIN";
        }

        WorkinfoDao workinfoDao = new WorkinfoDao();
        GiftinfoDao giftinfoDao = new GiftinfoDao();
        SaleinfoDao saleinfoDao = new SaleinfoDao();

        //查询赠品列表
        IList<Record> giftList = Storage.executor().select(giftinfoDao.getGiftInfoAll());

        //查询产品列表
        IList<Record> saleList = Storage.executor().select(saleinfoDao.getSaleInfoAll());

        //查询记录
        Record oldRecord = Storage.executor().load(workinfoDao.selWorkinfoById(workInfoId));

        if(null != oldRecord) {
            JSONObject jGift = JSONObject.convert(oldRecord.get("GiftInfo").toString());

            List<Record> giftResultList = new ArrayList<Record>();
            for (Record record : giftList) {
                String giftId = record.get("ID").toString();
                String giftName = record.get("GiftName").toString();
                if (null != jGift.get(giftId)) {
                    Record giftResult = new Record();
                    Double num = Double.parseDouble(jGift.get(giftId).toString());
                    giftResult.put("giftId", giftId);
                    giftResult.put("giftName", giftName);
                    giftResult.put("num", num);
                    giftResultList.add(giftResult);
                }
            }
            ServletActionContext.getContext().put("giftResultList", giftResultList);

            JSONObject jSale = JSONObject.convert(oldRecord.get("SaleInfo").toString());

            List<Record> saleResultList = new ArrayList<Record>();
            for(Record record : saleList) {
                String saleId = record.get("ID").toString();
                String saleName = record.get("ProductName").toString();
                if(null != jSale.get(saleId + "1")) {
                    Record saleResult = new Record();
                    Double price = Double.parseDouble(jSale.get(saleId + "1").toString());
                    Double num = Double.parseDouble(jSale.get(saleId + "2").toString());
                    Double total = Double.parseDouble(jSale.get(saleId + "3").toString());
                    saleResult.put("saleId", saleId);
                    saleResult.put("saleName", saleName);
                    saleResult.put("price", price);
                    saleResult.put("num", num);
                    saleResult.put("total", total);
                    saleResultList.add(saleResult);
                }
            }
            ServletActionContext.getContext().put("saleResultList", saleResultList);
            ServletActionContext.getContext().put("oldRecord", oldRecord);

            ServletActionContext.getContext().put("stockStatus", oldRecord.get("StockStatus"));
            ServletActionContext.getContext().put("status", oldRecord.get("Status"));
        }

        ServletActionContext.getContext().put("workInfoId", workInfoId);
        ServletActionContext.getContext().put("userId", userId);

        return "SUCCESS";


    }

    public String doUpdate() {

        int userId = User.getUserId();
        if(0 == userId) {
            ActionContext.getContext().put("redirect", ServletActionContext.getRequest().getRequestURI()); return "NOLOGIN";
        }

        WorkinfoDao workinfoDao = new WorkinfoDao();
        Storage.executor().alter(workinfoDao.updateWorkinfoStatusById(workInfoId, 2));

        //查询所辖员工
        IList<Record> workinfoList = Storage.executor().select(workinfoDao.getOutInfoBySuperId(userId));
        if(null != workinfoList && workinfoList.size() > 0) {
            List<Record> resultList = new ArrayList<Record>();
            for (Record subUser : workinfoList) {
                resultList.add(subUser);
            }
            ServletActionContext.getContext().put("workinfoList", resultList);
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

    public Integer getWorkInfoId() {
        return workInfoId;
    }

    public void setWorkInfoId(Integer workInfoId) {
        this.workInfoId = workInfoId;
    }
}
