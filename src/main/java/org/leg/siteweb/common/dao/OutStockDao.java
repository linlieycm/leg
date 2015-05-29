package org.leg.siteweb.common.dao;

/**
 * Created by myj on 15/5/2.
 */
public class OutStockDao {
    public String getOutStockById(Integer id) {
        String result = "select * from OutStock where Status = 1 and ID = " + id;
        return result;
    }

    public String getSubOutStockById(Integer id) {
        String result = "select o.id as id, u.Name as userName, s.StoreName as storeName, o.ReportDate as reportDate, o.OutSKU as outSKU, o.Remark as remark, sale.ProductName as productName " +
                " from OutStock o, Userinfo u, Storeinfo s, Saleinfo sale" +
                " where o.Status = 1 " +
                " and o.ID = " + id +
                " and o.StoreId = s.ID " +
                " and o.SalerId = u.ID " +
                " and o.OutSKU = sale.SKU ";
        return result;
    }

    //根据ID更新状态
    public String updateOutStatusById(Integer id, Integer status) {
        String result = "update OutStock set status = " + status + " where id = " + id;
        return result;
    }

    public String insertOutStock(Integer storeId,
                                 String reportDate,
                                 Integer salerId,
                                 String outSKU,
                                 String remark) {
        String result = " insert into OutStock (" +
                " StoreId," +
                " ReportDate," +
                " SalerId," +
                " OutSKU," +
                " Remark," +
                " UpdateTime," +
                " Status) values (" +
                storeId + ", '" +
                reportDate + "', " +
                salerId + ", " +
                outSKU + ", '" +
                remark + "', " +
                "Now(), " +
                "1 " +
                ") ";
        return result;
    }

    //根据督导ID查询
    public String getOutStockBySuperId(Integer supId) {
        String result = "select b.productName as productName, c.Name as userName, a.ID as stockId " +
                " from OutStock a, Saleinfo b, Userinfo c " +
                " where a.SalerId = c.ID " +
                " and b.SKU = a.OutSKU " +
                " and a.status = 1 " +
                " and a.SalerId in ( select id from Userinfo where status = 1 and SuperiorId = " +
                supId + ")";
        return result;
    }


}
