package com.github.jmetzz.laboratory.concurrency.standalone._99_demo.webcrawler;


import com.google.common.collect.Sets;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelWebCrawler implements LinkHandler {

    private final Collection<String> visitedLinks = Sets.newConcurrentHashSet(); //guava method

    private String url;

    private ForkJoinPool mainPool;

    public ParallelWebCrawler(String startingURL, int maxThreads) {
        this.url = startingURL;
        mainPool = new ForkJoinPool(maxThreads);
    }

    @Override
    public void queueLink(String link) throws Exception {
        throw new UnsupportedOperationException("Parallel crawler does'n support quereLink method.");
    }

    @Override
    public int size() {
        return visitedLinks.size();
    }

    @Override
    public void addVisited(String s) {
        visitedLinks.add(s);
    }

    @Override
    public boolean visited(String s) {
        return visitedLinks.contains(s);
    }

    private void startCrawling() {
        mainPool.invoke(new LinkFinderAction(this.url, this));
    }

    public static void main(String[] args) throws Exception {
        new ParallelWebCrawler("http://www.javaworld.com", 64).startCrawling();
    }


    private static class LinkFinderAction extends RecursiveAction {

        private static final long t0 = System.nanoTime();

        private String url;
        private LinkHandler cr;

        public LinkFinderAction(String url, LinkHandler cr) {
            this.url = url;
            this.cr = cr;
        }

        @Override
        protected void compute() {
            if (!cr.visited(url)) {
                try {
                    List<RecursiveAction> actions = new ArrayList<>();
                    URL uriLink = new URL(url);
                    Parser parser = new Parser(uriLink.openConnection());
                    NodeList list = parser.extractAllNodesThatMatch(new NodeClassFilter(LinkTag.class));

                    for (int i = 0; i < list.size(); i++) {
                        LinkTag extracted = (LinkTag) list.elementAt(i);
                        if (!extracted.extractLink().isEmpty() && !cr.visited(extracted.extractLink())) {
                            actions.add(new LinkFinderAction(extracted.extractLink(), cr));
                        }
                    }
                    cr.addVisited(url);

                    if (cr.size() == 1500) {
                        System.out.println("Time for visit 1500 distinct links= " + (System.nanoTime() - t0));
                    }

                    //invoke recursively
                    invokeAll(actions);
                } catch (Exception e) {
                    //ignore 404, unknown protocol or other server errors
                }
            }
        }
    }
}
