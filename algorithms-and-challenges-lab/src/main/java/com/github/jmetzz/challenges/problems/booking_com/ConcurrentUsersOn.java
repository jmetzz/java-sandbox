package com.github.jmetzz.challenges.problems.booking_com;

import java.util.*;

/**
 * Given a log file, of the below format, please find the max amount of concurrent users on the website:
 * <p>
 * logitem: {
 * user: %user_name%,
 * login: %login_epoch%,
 * logout: %logout_epoch%
 * }
 * <p>
 * Example:
 * <p>
 * logfile: [{
 * user: A,
 * login: 1,
 * logout: 3
 * }, {
 * user: B,
 * login: 2,
 * logout: 3
 * }, {
 * user: C,
 * login: 5,
 * logout: 7
 * }, {
 * user: D,
 * login: 4,
 * logout: 5
 * }]
 * <p>
 * Answer: 2
 */
public class ConcurrentUsersOn {


    public static void main(String[] args) {

        List<LogItem> logs = new ArrayList<>();
        logs.add(new LogItem("A", 1, 3));
        logs.add(new LogItem("B", 2, 3));
        logs.add(new LogItem("C", 5, 7));
        logs.add(new LogItem("D", 4, 5));
        Map<Integer, Integer> load = new HashMap<>();

        for (LogItem log : logs) {
            for (int i = log.loginEpoch; i <= log.logoutEpoch; i++) {
                if (!load.containsKey(i))
                    load.put(i, 0);
                load.put(i, load.get(i) + 1);
            }
        }
        Optional<Integer> max = load.values().stream().sorted().max(Integer::compareTo);
        System.out.println(max);
    }

    private static class LogItem {
        final String user;
        final int loginEpoch;
        final int logoutEpoch;

        private LogItem(String user, int loginEpoch, int logoutEpoch) {
            this.user = user;
            this.loginEpoch = loginEpoch;
            this.logoutEpoch = logoutEpoch;
        }
    }

}
