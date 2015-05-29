package org.leg.siteweb.page.steer;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.leg.library.type.Record;
import org.leg.library.type.core.IList;
import org.leg.siteweb.common.Storage;
import org.leg.siteweb.common.User;
import org.leg.siteweb.common.dao.EvaluationComonDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by myj on 15/5/7.
 */
public class SteerMAssessmentAction extends ActionSupport {
    private static final long serialVersionUID = 5165050377715752795L;

    /**
     * 日志对象
     */
    protected static Logger logger = Logger.getLogger(SteerMInfoAdjustAction.class);

    private Integer subUserId;

    /**
     * 执行请求动作
     */
    public String execute() throws Exception {

        int userId = User.getUserId();
        if(0 == userId) {
            ActionContext.getContext().put("redirect", ServletActionContext.getRequest().getRequestURI()); return "NOLOGIN";
        }

        EvaluationComonDao evaluationComonDao = new EvaluationComonDao();

        //获取所有评价题
        IList<Record> evaluationComonList = Storage.executor().select(evaluationComonDao.getEvaluationComonAll());
        if(null != evaluationComonList && evaluationComonList.size() > 0) {
            List<Record> resultList = new ArrayList<Record>();
            for (Record evaluationComon : evaluationComonList) {
                resultList.add(evaluationComon);
            }
            ServletActionContext.getContext().put("evaluationComonList", resultList);
        }


        ServletActionContext.getContext().put("userId", userId);
        return "SUCCESS";
    }


}
