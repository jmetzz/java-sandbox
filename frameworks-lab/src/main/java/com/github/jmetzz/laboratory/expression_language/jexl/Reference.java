package com.github.jmetzz.laboratory.expression_language.jexl;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Reference implements HasReference {
    protected final String referenceId;

    protected Reference(String refId) {
        Preconditions.checkArgument(refId != null, "The referenceId may not be null");
        referenceId = refId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    @Override
    public String getReference() {
        return referenceId;
    }

    protected static <T extends Reference> T of(Class<T> referenceClass, String reference) {
        if (StringUtils.isEmpty(reference)) {
            return null;
        }

        Constructor<T> constructor;
        try {
            constructor = referenceClass.getConstructor(String.class);
            return constructor.newInstance(reference);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static String getReferenceId(Reference reference) {
        if (reference == null) {
            return null;
        }
        return reference.getReferenceId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(referenceId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Reference)) {
            return false;
        }
        Reference other = (Reference) obj;
        return Objects.equal(referenceId, other.referenceId);
    }

    @Override
    public String toString() {
        return String.format("<%s>", referenceId);
    }

}
