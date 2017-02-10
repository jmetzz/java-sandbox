package com.github.jmetzz.laboratory.mockito.extension.verification_mode;

import org.mockito.exceptions.Reporter;
import org.mockito.exceptions.verification.VerificationInOrderFailure;
import org.mockito.internal.debugging.LocationImpl;
import org.mockito.internal.invocation.InvocationMarker;
import org.mockito.internal.invocation.InvocationMatcher;
import org.mockito.internal.invocation.InvocationsFinder;
import org.mockito.internal.verification.api.VerificationData;
import org.mockito.invocation.Invocation;
import org.mockito.verification.VerificationMode;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jean on 6/02/2017.
 */
public class First implements VerificationMode {

    private final InvocationsFinder finder = new InvocationsFinder();
    private final InvocationMarker marker = new InvocationMarker();
    private final Reporter reporter = new Reporter();

    public static VerificationMode first() {
        return new First();
    }

    @Override
    public void verify(VerificationData data) {
        List<Invocation> invocations = data.getAllInvocations();
        InvocationMatcher matcher = data.getWanted();
        List<Invocation> chunk = finder.findInvocations(invocations, matcher);
        if (invocations.size() == 0 || chunk.size() == 0) {
            reporter.wantedButNotInvoked(matcher);
        } else if (!sameInvocation(invocations.get(0), chunk.get(0))) {
            reportNotFirst(chunk.get(0), invocations.get(0));
        }
        marker.markVerified(chunk.get(0), matcher);
    }

    private boolean sameInvocation(Invocation left, Invocation right) {
        if (left == right) {
            return true;
        }
        return left.getMock().equals(right.getMock()) && left.getMethod().equals(right.getMethod()) && Arrays.equals(left.getArguments(), right.getArguments());
    }

    private void reportNotFirst(Invocation wanted, Invocation unwanted) {
        StringBuilder message = new StringBuilder();
        message.append("\\nWanted first:\\n").append(wanted).append("\\n").append(new LocationImpl());
        message.append("\\nInstead got:\\n").append(unwanted).append("\\n").append(unwanted.getLocation()).append("\\n");
        throw new VerificationInOrderFailure(message.toString());
    }

}
