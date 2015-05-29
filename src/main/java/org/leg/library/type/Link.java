package org.leg.library.type;

import org.leg.library.type.core.ILink;

/**
 * 连接类
 */
public class Link<O, D> implements ILink<O, D>, Cloneable {
    /**
     * 起点对象
     */
    protected O origin = null;
    /**
     * 终点对象
     */
    protected D destination = null;


    /**
     * 构造函数
     */
    public Link() { }
    public Link(O origin, D destination) {
        setLink(origin, destination);
    }

    /**
     * 获取起点
     *
     * @return 起点对象
     */
    @Override
    public O getOrigin() {
        return origin;
    }

    /**
     * 设置起点
     *
     * @param origin 起点对象
     */
    @Override
    public void setOrigin(O origin) {
        this.origin = origin;
    }

    /**
     * 获取终点
     *
     * @return 终点对象
     */
    @Override
    public D getDestination() {
        return destination;
    }

    /**
     * 设置终点
     *
     * @param destination 终点对象
     */
    @Override
    public void setDestination(D destination) {
        this.destination = destination;
    }

    /**
     * 设置连接
     *
     * @param origin      起点对象
     * @param destination 终点对象
     */
    @Override
    public void setLink(O origin, D destination) {
        this.origin = origin;
        this.destination = destination;
    }
}
