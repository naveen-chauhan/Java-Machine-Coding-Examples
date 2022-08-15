package truecaller.models;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * @author naveen.chauhan on 23/07/22
 */

@Getter
public class PremiumInfo {
    private final String planName;
    private static final List<String> ALLOWED_PLANS = Arrays.asList("FREEMIUM", "PREMIUM_PLAN_A");

    public PremiumInfo(String planName) {
        this.planName = planName;
    }

    public static PremiumInfo upgrade() {
        return new PremiumInfo("PREMIUM_PLAN_A");
    }
}
