package org.integrallis.drools.flows;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class FlowExample {

	public static final void main(String[] args) {
	    KieSession knowledgeSession = null;
	    try {
	        // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
		    KieContainer kContainer = ks.getKieClasspathContainer();
		    knowledgeSession = kContainer.newKieSession("ksession-rules");

			// 4 - create and assert some facts
			SomeOtherObject trigger = new SomeOtherObject("foo");
			
			knowledgeSession.insert(trigger);
			
			// 5 - start a new process instance
			knowledgeSession.startProcess("org.integrallis.drools.flows.sample");
			
			// 6 - fire the rules
			knowledgeSession.fireAllRules();
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			knowledgeSession.dispose();
        }
	}
}