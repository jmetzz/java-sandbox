package com.github.jmetzz.booking.code;


import com.github.jmetzz.genericMethod.pojo.Code;

import java.util.SortedSet;

public interface CodeResolver {

    CodeKey mapCode(CodeKey key, CodeSchema schema);

    SortedSet<Code> getCodes(CodeKey key);

    Code getFirstCode(CodeKey key);

    boolean same(CodeKey key1, CodeKey key2);

}
