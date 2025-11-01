import java.util.*;

class Solution {
    public int numUniqueEmails(String[] emails) {
        Set<String> uniqueEmails = new HashSet<>();

        for (String email : emails) {
            String[] parts = email.split("@");
            String local = parts[0];
            String domain = parts[1];

            // Remove everything after '+'
            if (local.contains("+")) {
                local = local.substring(0, local.indexOf("+"));
            }

            // Remove all '.'
            local = local.replace(".", "");

            // Combine cleaned local part with domain
            String normalizedEmail = local + "@" + domain;

            uniqueEmails.add(normalizedEmail);
        }

        return uniqueEmails.size();
    }
}
