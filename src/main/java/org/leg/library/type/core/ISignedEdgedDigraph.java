package org.leg.library.type.core;

/**
 * 带标记有边有向图接口
 */
public interface ISignedEdgedDigraph<K, T extends ISignedEdgedDigraph<K, T, L>, L extends ILink<T, T>> extends ITable<K, L> { }
