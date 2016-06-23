package com.github.jmetzz.laboratory.concurrency.standalone.executors;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


/**
 * Created by Jean Metz.
 */
public class ReadWebPage {

    public static void main(final String[] args) {
        if (args.length != 1) {
            System.err.println("usage: java ReadWebPage url");
            return;
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<List<String>> callable;
        callable = new Callable<List<String>>() {
            /* creates a single-thread threadPoolExample and a callable
            that tries to open a connection to this URL, read its
            contents line by line, and save these lines in a list,
            which it returns. */

            @Override
            public List<String> call()
                    throws IOException {
                List<String> lines = new ArrayList<String>();
                URL url = new URL(args[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                InputStreamReader isr = new InputStreamReader(con.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                String line;
                while ((line = br.readLine()) != null)
                    lines.add(line);
                return lines;
            }
        };

        Future<List<String>> future = executor.submit(callable);

        try {
            /* throws TimeoutException if the thread doesn't finish in 5 seconds*/
            List<String> lines = future.get(5, TimeUnit.SECONDS);

            for (String line : lines)
                System.out.println(line);

        } catch (ExecutionException ee) {
            System.err.println("Callable through exception: " + ee.getMessage());
        } catch (InterruptedException eite) {
            System.err.println("URL not responding");
        } catch (TimeoutException eite) {
            System.err.println("URL not responding");
        }
        executor.shutdown();
    }
}
