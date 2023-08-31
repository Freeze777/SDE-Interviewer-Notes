package leetcode.medium.design;


import java.util.ArrayList;

/**
 *
 * <a href="https://leetcode.com/problems/design-browser-history/">1472. Design Browser History</a>
 *
 */
public class BrowserHistory {

    private final ArrayList<String> history = new ArrayList<>();
    private int currentIndex = 0;

    @Override
    public String toString() {
        return "BrowserHistory{" + "history=" + history + ", currentIndex=" + currentIndex + '}';
    }

    public BrowserHistory(String homepage) {
        history.add(homepage);
    }

    public void visit(String url) {
        currentIndex++;
        history.subList(currentIndex, history.size()).clear();
        history.add(url);
    }

    public String back(int steps) {
        currentIndex = Math.max(0, currentIndex - steps);
        return history.get(currentIndex);
    }

    public String forward(int steps) {
        currentIndex = Math.min(history.size() - 1, currentIndex + steps);
        return history.get(currentIndex);
    }

    public static void main(String[] args) {
        BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
        browserHistory.visit("google.com");       // You are in "leetcode.com". Visit "google.com"
        browserHistory.visit("facebook.com");     // You are in "google.com". Visit "facebook.com"
        browserHistory.visit("youtube.com");      // You are in "facebook.com". Visit "youtube.com"
        System.out.println(browserHistory);
        System.out.println(browserHistory.back(1));                    // You are in "youtube.com", move back to "facebook.com" return "facebook.com"
        System.out.println(browserHistory.back(1));                   // You are in "facebook.com", move back to "google.com" return "google.com"
        System.out.println(browserHistory.forward(1));                // You are in "google.com", move forward to "facebook.com" return "facebook.com"
        browserHistory.visit("linkedin.com");                           // You are in "facebook.com". Visit "linkedin.com"
        System.out.println(browserHistory);
        System.out.println(browserHistory.forward(2));                // You are in "linkedin.com", you cannot move forward any steps.
        System.out.println(browserHistory.back(2));                   // You are in "linkedin.com", move back two steps to "facebook.com" then to "google.com". return "google.com"
        System.out.println(browserHistory.back(7));                   // You are in "google.com", you can move back only one step to "leetcode.com". return "leetcode.com"
    }
}
