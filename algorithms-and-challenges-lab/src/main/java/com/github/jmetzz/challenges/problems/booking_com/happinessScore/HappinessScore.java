package com.github.jmetzz.challenges.problems.booking_com.happinessScore;

import java.util.List;
import java.util.Set;

/**
 * Created by Jean Metz.
 */
public interface HappinessScore {

    Set<Integer> satisfactionScores(int n, List<Integer> localtionScore);

    Set<Integer> primeSatisfactionScores(int n, List<Integer> localtionScore);

}
