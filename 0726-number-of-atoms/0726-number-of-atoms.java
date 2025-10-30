import java.util.*;

class Solution {
    public String countOfAtoms(String formula) {
        Deque<Map<String, Integer>> stack = new ArrayDeque<>();
        stack.push(new HashMap<>());
        int n = formula.length();
        int i = 0;

        while (i < n) {
            char c = formula.charAt(i);

            if (c == '(') {
                stack.push(new HashMap<>());
                i++;
            } else if (c == ')') {
                i++;
                int start = i;
                while (i < n && Character.isDigit(formula.charAt(i))) {
                    i++;
                }
                int multiplier = start < i ? Integer.parseInt(formula.substring(start, i)) : 1;

                Map<String, Integer> popped = stack.pop();
                for (Map.Entry<String, Integer> entry : popped.entrySet()) {
                    stack.peek().put(entry.getKey(),
                        stack.peek().getOrDefault(entry.getKey(), 0) + entry.getValue() * multiplier);
                }

            } else {
                // Parse element name
                int start = i++;
                while (i < n && Character.isLowerCase(formula.charAt(i))) {
                    i++;
                }
                String atom = formula.substring(start, i);

                // Parse count (if any)
                start = i;
                while (i < n && Character.isDigit(formula.charAt(i))) {
                    i++;
                }
                int count = start < i ? Integer.parseInt(formula.substring(start, i)) : 1;

                stack.peek().put(atom, stack.peek().getOrDefault(atom, 0) + count);
            }
        }

        // Build final string
        Map<String, Integer> finalMap = stack.pop();
        TreeMap<String, Integer> sorted = new TreeMap<>(finalMap);

        StringBuilder sb = new StringBuilder();
        for (String atom : sorted.keySet()) {
            sb.append(atom);
            int cnt = sorted.get(atom);
            if (cnt > 1) sb.append(cnt);
        }

        return sb.toString();
    }

    // For quick testing
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.countOfAtoms("H2O"));           // H2O
        System.out.println(s.countOfAtoms("Mg(OH)2"));       // H2MgO2
        System.out.println(s.countOfAtoms("K4(ON(SO3)2)2")); // K4N2O14S4
    }
}
