package org.leg.siteweb.common.dao;

/**
 * Created by myj on 15/5/2.
 */
public class SaleinfoDao {

    public String getSaleInfoById(Integer giftId) {
        String result = "select * from Saleinfo where Status = 1 and ID = " + giftId;
        return result;
    }

    public String getSaleInfoByDate(String beginDate, String endDate, String storeCity) {
        String result = "select * from Saleinfo where Status = 1 " +
                " and str_to_date(CreateTime, '%Y-%m-%d') >= " + beginDate +
                " and str_to_date(CreateTime, '%Y-%m-%d') <= " + endDate +
                " and StoreCity = " + storeCity;
        return result;
    }

    public String getSaleInfoAll() {
        String result = "select * from Saleinfo where Status = 1 ";
        return result;
    }

    public String getSDistinctSKU() {
        String result = "select distinct SKU as SKU, ProductName as ProductName from Saleinfo where Status = 1 ";
        return result;
    }

    public String delSaleInfoById(Integer saleId) {
        String result = "update Saleinfo set status = 0 where ID = " + saleId;
        return result;
    }

    public String updateSaleInfo(Integer saleId, String sku, String saleName) {
        String result = "update Saleinfo " +
                " set ProductName = '" + saleName + "', " +
                "  SKU = '" + sku + "' " +
                " where ID = " + saleId;
        return result;
    }

    public String insertSale(String saleName, String sku) {
        String result = "insert into Saleinfo(ProductName, SKU, UpdateTime, Status) values ( '" + saleName +"', '" + sku +"', Now(), 1)";
        return result;
    }

}
