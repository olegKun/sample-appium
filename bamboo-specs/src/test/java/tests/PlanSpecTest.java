package java.tests;

import com.atlassian.bamboo.specs.api.builders.plan.Plan;
import com.atlassian.bamboo.specs.api.exceptions.PropertiesValidationException;
import com.atlassian.bamboo.specs.api.util.EntityPropertiesBuilders;
import org.testng.annotations.Test;

import tutorial.oleh.PlanSpec;


public class PlanSpecTest {
    @Test
    public void checkYourPlanOffline() throws PropertiesValidationException {
        Plan plan = new PlanSpec().createPlan();

        EntityPropertiesBuilders.build(plan);
    }
}
