package org.leg.library.type.core;

/**
 * 连接接口
 */
public interface ILink<O, D> {
    /**
     * 获取起点
     *
     * @return  起点对象
     */
    public O getOrigin();

    /**
     * 设置起点
     *
     * @param origin 起点对象
     */
    public void setOrigin(O origin);

    /**
     * 获取终点
     *
     * @return  终点对象
     */
    public D getDestination();

    /**
     * 设置终点
     *
     * @param destination 终点对象
     */
    public void setDestination(D destination);

    /**
     * 设置连接
     *
     * @param origin 起点对象
     * @param destination 终点对象
     */
    public void setLink(O origin, D destination);
}
