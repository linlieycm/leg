package org.leg.library.xml.core;

import org.leg.library.type.core.IMapping;
import org.leg.library.type.core.ISet;

/**
 * XML节点集接口
 */
public interface IXMLNodeSet extends ISet<IXMLNode>, IMapping<String, ISet<IXMLNode>> { }
