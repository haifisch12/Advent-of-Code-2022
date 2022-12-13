import java.util.ArrayList;
import java.util.List;

public class PacketPair {
    private final Object left;
    private Object right;

    public PacketPair(Object left) {
        this.left = left;
    }

    public void setRight(Object right) {
        this.right = right;
    }

    public boolean isRightOrder() {
        return compare(left, right).getBooleanValue();
    }

    public static CompareResult compare(Object left, Object right) {
        if (left instanceof Double && right instanceof Double) {
            int intLeft    = ((Double)left).intValue();
            int intRight   = ((Double)right).intValue();

            if (intLeft < intRight)
                return CompareResult.INORDER;
            else if (intLeft == intRight)
                return CompareResult.EQUAL;
            else
                return CompareResult.NOTINORDER;
        }
        else if (left instanceof List<?> leftList && right instanceof List<?> rightList) {

            if (leftList.size() == 0 && rightList.size() > 0)
                return CompareResult.INORDER;

            for (int i = 0; i < leftList.size(); i++) {
                if (i > rightList.size() - 1) return CompareResult.NOTINORDER;

                CompareResult result = compare(leftList.get(i), rightList.get(i));

                if (result.equals(CompareResult.NOTINORDER))
                    return CompareResult.NOTINORDER;


                if (result.getBooleanValue() || (i == leftList.size() - 1 && rightList.size() > leftList.size()))
                    return CompareResult.INORDER;
                }

            return CompareResult.EQUAL;
        }
        else {
            if (left instanceof Double) {
                return compare(new ArrayList<>(){{add(left);}}, right);
            }
            else {
                return compare(left, new ArrayList<>(){{add(right);}});
            }
        }
    }

    public Object getLeft() {
        return left;
    }

    public Object getRight() {
        return right;
    }
}
