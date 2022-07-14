package splitwise;

/**
 * @author naveen.chauhan on 04/07/22
 */
public interface ISplitWiseProcessor {
    void show();

    void show(String userId);

    void expense(String[] commands);

}
