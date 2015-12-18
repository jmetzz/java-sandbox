package com.github.jmetzz.functional.guava.splitAdmission;

import com.github.jmetzz.functional.guava.splitAdmission.pojo.SplitAdmission;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.apache.log4j.Logger;


import java.util.List;


/**
 * Created by exi853 on 18/12/2015.
 */
public class SplitAdmissionCommand {

    Logger logger = Logger.getLogger(SplitAdmissionCommand.class.getName());

    private static final String WILDCARD = "%";
    public static final String DISTRIBUTOR = "BS";

    private SplitAdmissionProvider provider;
    private SplitAdmissionQuery query;


    public SplitAdmissionCommand(SplitAdmissionQuery query, SplitAdmissionProvider provider) {
        this.query = query;
        this.provider = provider;
    }

    public Optional<SplitAdmission> execute() {
        List<SplitAdmission> splitAdmissions = provider.getSplitAdmissions();

        for (SplitAdmission splitAdmission : splitAdmissions) {
            logger.debug(splitAdmission);

            if (Predicates.and(HAS_ORIGIN_LIKE, HAS_DESTINATION_LIKE, HAS_DISTRIBUTOR, HAS_CONFIG_PROFILE, HAS_TARIFF_GROUP).apply(splitAdmission)) {
                logger.debug("Split admission match: " + splitAdmission);
                return Optional.of(splitAdmission);
            }
        }
        logger.debug("No split admission found");
        return Optional.absent();
    }

    private Predicate<SplitAdmission> HAS_ORIGIN_LIKE = new Predicate<SplitAdmission>() {
        @Override
        public boolean apply(SplitAdmission s) {
            String originLike = query.getSplitAdmissionSpec().getSegmentOrigin();
            boolean result = Matcher.matchesWildCard(s.getOriginLike(), originLike, WILDCARD);

            logger.debug("Pattern: " + originLike + "; input: " + s.getOriginLike() + "; result " + result);
            return result;
        }
    };

    private Predicate<SplitAdmission> HAS_DESTINATION_LIKE = new Predicate<SplitAdmission>() {
        @Override
        public boolean apply(SplitAdmission s) {
            String destinationLike = query.getSplitAdmissionSpec().getSegmentDestination();
            boolean result = Matcher.matchesWildCard(s.getDestinationLike(), destinationLike, WILDCARD);
            logger.debug("Pattern: " + destinationLike + "; input: " + s.getDestinationLike() + "; result " + result);

            return result;
        }
    };

    private Predicate<SplitAdmission> HAS_DISTRIBUTOR = new Predicate<SplitAdmission>() {
        @Override
        public boolean apply(SplitAdmission s) {
            boolean result = Matcher.matchesWildCard(s.getDistributor(), DISTRIBUTOR, WILDCARD);
            logger.debug("Pattern: " + DISTRIBUTOR + "; input: " + s.getDistributor() + "; result " + result);


            return result;
        }
    };

    private Predicate<SplitAdmission> HAS_CONFIG_PROFILE = new Predicate<SplitAdmission>() {
        @Override
        public boolean apply(SplitAdmission s) {
            String configProfile = query.getSplitAdmissionSpec().getConfigProfile();
            boolean result = Matcher.matchesWildCard(s.getConfigProfile(), configProfile, WILDCARD);
            logger.debug("Pattern: " + configProfile + "; input: " + s.getConfigProfile() + "; result " + result);

            return result;
        }
    };

    private Predicate<SplitAdmission> HAS_TARIFF_GROUP = new Predicate<SplitAdmission>() {
        @Override
        public boolean apply(SplitAdmission s) {
            String tariffGroup = query.getSplitAdmissionSpec().getTariffGroup();
            boolean result = Matcher.matchesWildCard(s.getTariffGroup(), tariffGroup, WILDCARD);
            logger.debug("Pattern: " + tariffGroup + "; input: " + s.getTariffGroup() + "; result " + result);

            return result;
        }
    };


}
